package neil.demo.mar26;

import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.common.util.concurrent.AtomicDouble;
import com.hazelcast.aggregation.Aggregators;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.core.IMap;

/**
 * <p>Calculate the standard deviation.
 * </p>
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	private HazelcastInstance hazelcastInstance;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("------------------------------------------------");
		System.out.println("START -- Standard Deviation calculation -- START");
		System.out.println("------------------------------------------------");
		
		IMap<Integer, Personne> iMap = this.hazelcastInstance.getMap(Data.MAP_NAME);

		// Client-side, bad - networking, race conditions, values() might overflow
		AtomicInteger sum = new AtomicInteger();
		AtomicInteger count = new AtomicInteger();
		
		iMap.keySet()
		.parallelStream()
		.map(key -> iMap.get(key))
		.forEach(personne -> {
			sum.addAndGet(personne.getAge());
			count.incrementAndGet();
		});
		
		final double averageAge_1 = (double) sum.get() / count.get();
		
		// Server-side, faster and less typing
		double averageAge_2 = 
			iMap.aggregate(Aggregators.integerAvg("age"));
		
		// Reporting
		System.out.println("");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Step 1 : Sum of ages is               : " + sum.get());
		System.out.println("Step 1 : Number of persons is         : " + count.get());
		System.out.println("Step 1 : Average age (client-side) is : " + averageAge_1);
		System.out.println("Step 1 : Average age (server-side) is : " + averageAge_2);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		// Client-side
		AtomicDouble diffsSquared_1 = new AtomicDouble();

		iMap.keySet()
		.parallelStream()
		.map(key -> iMap.get(key))
		.forEach(personne -> {
			double diff = averageAge_1 - personne.getAge();
			diffsSquared_1.addAndGet(diff * diff);
		});

		double averageDiffsSquares_1 = diffsSquared_1.get() / count.get();
		
		double standardDeviation_1 = Math.sqrt(averageDiffsSquares_1);

		// Server-side
		DiffSquareSumCallable diffSquareSumCallable = new DiffSquareSumCallable(averageAge_1); 
		IExecutorService iExecutorService = this.hazelcastInstance.getExecutorService("default");
		AtomicDouble diffsSquared_2 = new AtomicDouble();
		
		Map<?, Future<Double>> results = iExecutorService.submitToAllMembers(diffSquareSumCallable);
		
		results.values()
		.forEach(result -> {
			try {
				diffsSquared_2.addAndGet(result.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		double averageDiffsSquares_2 = diffsSquared_2.get() / count.get();
		
		double standardDeviation_2 = Math.sqrt(averageDiffsSquares_2);
		
		// Reporting
		System.out.println("");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Step 2 : Square of differences is            : " + diffsSquared_1);
		System.out.println("Step 2 : Average square of differences is    : " + averageDiffsSquares_1);
		System.out.println("Step 2 : Standard deviation (client-side) is : " + standardDeviation_1);
		System.out.println("Step 2 : Standard deviation (server-side) is : " + standardDeviation_2);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		System.out.println("");
		System.out.println("------------------------------------------------");
		System.out.println(" END  -- Standard Deviation calculation --  END ");
		System.out.println("------------------------------------------------");
		
		this.hazelcastInstance.shutdown();
	}

}

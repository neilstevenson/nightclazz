package neil.demo.mar26;

import java.io.Serializable;
import java.util.concurrent.Callable;

import com.google.common.util.concurrent.AtomicDouble;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.core.IMap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("serial")
public class DiffSquareSumCallable implements Callable<Double>, HazelcastInstanceAware, Serializable {
	
	private double averageAge;
	private HazelcastInstance hazelcastInstance;
	
	public DiffSquareSumCallable(double arg0) {
		this.averageAge = arg0;
	}

	@Override
	public void setHazelcastInstance(HazelcastInstance arg0) {
		this.hazelcastInstance = arg0;
	}

	@Override
	public Double call() throws Exception {
		IMap<Integer, Personne> iMap = this.hazelcastInstance.getMap(Data.MAP_NAME);
		
		AtomicDouble diffsSquared = new AtomicDouble();

		// "localKeySet()" means just this JVM
		iMap
		.localKeySet()
		.stream()
		.filter(key -> { log.info("Key '{}'", key); return true; })
		.map(key -> iMap.get(key))
		.forEach(personne -> {
			double diff = this.averageAge - personne.getAge();
			diffsSquared.addAndGet(diff * diff);
		});
		
		return diffsSquared.get();
	}

}

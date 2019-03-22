package neil.demo.mar26;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.SqlPredicate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApplicationInitializer implements CommandLineRunner {
	
	@Autowired
	private HazelcastInstance hazelcastInstance;
	
	@Override
	public void run(String... args) throws Exception {
		log.info("~~~ START ~~~");

		IMap<Integer, Personne> personneMap = this.hazelcastInstance.getMap(Data.MAP_NAME);

		// Clients can listen too!
		personneMap.addEntryListener(new MyLoggingListener(), true);

		Set<Integer> keys = new TreeSet<>(personneMap.keySet());
		keys.forEach(key -> System.out.println(personneMap.get(key)));

		log.info("~~~ MORE  ~~~");
		
		// There are not 15 records in the database
		for (int key=1 ; key <= 15 ; key++) {
			long start = System.currentTimeMillis();
			Personne value = personneMap.get(key);
			long elapsed = System.currentTimeMillis() - start;
			System.out.println("get(" + key + ") => " + value + " in " + elapsed + "ms.");
		}

		int count = 0;
		log.info("~ AND MORE {} ~~", ++count);
		
		SqlPredicate sqlPredicate = new SqlPredicate("birthCCYY > 1900");
		
		System.out.println(sqlPredicate);
		
		Collection<Personne> results = personneMap.values(sqlPredicate);
		
		for (Personne personne : results) {
			System.out.println("  " + personne);
		}

		System.out.println("[" + results.size() + " row" + (results.size()==1 ? "]": "s]"));

		log.info("~ AND MORE {} ~~", ++count);

		TimeUnit.SECONDS.sleep(45);
		System.out.println("Keys -> " + new TreeSet<>(personneMap.keySet()));

		log.info("~ AND MORE {} ~~", ++count);

		TimeUnit.SECONDS.sleep(45);
		System.out.println("Keys -> " + new TreeSet<>(personneMap.keySet()));

		log.info("~~~  END  ~~~");
	}
	
}

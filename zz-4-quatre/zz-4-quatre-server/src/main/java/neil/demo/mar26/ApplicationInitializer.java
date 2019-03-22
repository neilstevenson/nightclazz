package neil.demo.mar26;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApplicationInitializer implements CommandLineRunner {
	
	@Autowired
	private HazelcastInstance hazelcastInstance;
	
	@Override
	public void run(String... args) throws Exception {
		log.info("~~~ START ~~~");

		IMap<?, ?> personneMap = this.hazelcastInstance.getMap(Data.MAP_NAME);
		
		System.out.println("'" + personneMap.getName() + "', size==" + personneMap.size());

		log.info("~~~  END  ~~~");
	}
	
}

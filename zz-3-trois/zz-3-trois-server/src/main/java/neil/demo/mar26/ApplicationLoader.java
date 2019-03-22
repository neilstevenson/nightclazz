package neil.demo.mar26;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApplicationLoader implements CommandLineRunner {
	
	@Autowired
	private HazelcastInstance hazelcastInstance;
	
	@Override
	public void run(String... args) throws Exception {
		log.info("~~~ START ~~~");
		
		// Use as standard java map
		java.util.Map<Integer, Personne> map = this.hazelcastInstance.getMap(Data.MAP_NAME);
		
		if (map.isEmpty()) {
			
			for (int i = 0 ; i < Data.TEST_DATA.length; i++) {
				String[] data = Data.TEST_DATA[i];
				
				Personne personne = new Personne();
				personne.setPreNom(data[0]);
				personne.setNomDeFamilie(data[1]);
				personne.setAge(Integer.parseInt(data[2]));
				
				map.put(i, personne);
			}
			
		} else {
			log.info("Skip loading '{}', size {}", Data.MAP_NAME, map.size());
		}

		log.info("~~~  END  ~~~");
	}
	
}

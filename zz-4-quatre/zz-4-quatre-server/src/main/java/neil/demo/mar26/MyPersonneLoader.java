package neil.demo.mar26;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.hazelcast.core.MapLoader;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyPersonneLoader implements ApplicationContextAware, MapLoader<Integer, Personne> {

	private static PersonneRepository personneRepository;
	
	@Override
	public Personne load(Integer key) {
		log.info("load({})", key);
		
		try {
			TimeUnit.SECONDS.sleep(1);
			return personneRepository.findById(key).get();
			
		} catch (Exception ignored) {
			return null;
		}
	}

	@Override
	public Map<Integer, Personne> loadAll(Collection<Integer> keys) {
        log.trace("loadAll({})", keys);
        
        Map<Integer, Personne> result = new HashMap<>();
        
        for (Integer key : keys) {
        	Personne value = this.load(key);
        	if (value != null) {
        		result.put(key, value);
        	}
        }
        
        return result;
	}

	@Override
	public Iterable<Integer> loadAllKeys() {
		log.info("loadAllKeys()");

		//TODO: Change this line to trigger pre-loading.
		return Collections.emptyList();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		personneRepository = applicationContext.getBean(PersonneRepository.class);
	}
}

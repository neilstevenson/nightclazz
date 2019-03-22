package neil.demo.mnar26;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;
import neil.demo.mar26.PersonneRepository;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration(exclude=HazelcastAutoConfiguration.class)
@EnableJpaRepositories(basePackages = {"neil.demo.mar26"})
@EntityScan(basePackages = {"neil.demo.mar26"})
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
@SpringBootTest(classes={Object.class})
public class PersonneRepositoryTest {

	@Autowired
	private PersonneRepository personneRepository;
	
    @Test
    public void findAll() {
            final AtomicInteger count = new AtomicInteger();
            
            this.personneRepository.findAll()
            .forEach(record -> {
            	assertThat("Record " + count.get(), record, notNullValue());
            	count.incrementAndGet();
            	log.info(record.toString());
            });
            
            assertThat(count.get(), greaterThan(0));
    }	
	
}

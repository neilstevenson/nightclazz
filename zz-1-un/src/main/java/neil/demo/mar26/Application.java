package neil.demo.mar26;

import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;

public class Application {
	
	public static void main(String[] args) throws Exception {
		
		Config config = new Config();
		
		config.getNetworkConfig().setPort(6000);
		config.getGroupConfig().setName("un");
		
		/* Alternatives... YAML is 3.12
		Config config = new ClasspathXmlConfig("hazelcast.xml");
		Config config = new ClasspathYamlConfig("hazelcast.yml");
		*/
		
		Hazelcast.newHazelcastInstance(config);
	}

}

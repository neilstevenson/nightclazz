package neil.demo.mar26;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;

public class Application {
	
	public static void main(String[] args) {
		
		Config config = new Config();
		
		config.getNetworkConfig().setPort(6000);
		config.getGroupConfig().setName("un");
		
		Hazelcast.newHazelcastInstance(config);
	}

}

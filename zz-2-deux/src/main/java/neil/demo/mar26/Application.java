package neil.demo.mar26;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableCaching
public class Application {
	
	public static void main(String[] args) {
		
		ApplicationContext applicationContext =
				SpringApplication.run(Application.class, args);
		
		Fibonacci fibonacci = applicationContext.getBean(Fibonacci.class);
		
		long input = 27;
		//long input = 28;
		//long input = 5000;
		
		long start = System.currentTimeMillis();
		System.out.println("START, INPUT==" + input);
		
		long output = fibonacci.calculate(input);
		
		long elapsed = System.currentTimeMillis() - start;
		System.out.println("END, OUTPUT==" + output + ", ELAPSED==" + elapsed + "ms");
	}

}

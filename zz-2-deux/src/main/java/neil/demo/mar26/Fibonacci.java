package neil.demo.mar26;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Fibonacci {

	@Autowired
	private Fibonacci fibonacci;
	
	public long calculate(long input) {
		log.info("calculate({}) START", input);
		
		long result;
		
		if (input <= 2) {
			result = 1;
		} else {
			result =
				this.fibonacci.calculate(input - 1)
				+ this.fibonacci.calculate(input - 2);
		}
		
		log.info("calculate({}) END -> {}", input, result);
		return result;
	}
}

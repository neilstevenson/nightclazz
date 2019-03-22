package neil.demo.mar26;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class Personne implements Serializable {

	private String preNom;
	private String nomDeFamilie;
	private int age;
	
}

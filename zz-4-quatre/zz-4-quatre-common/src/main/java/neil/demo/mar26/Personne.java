package neil.demo.mar26;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
@Entity
@Table(name="personne_t")
@SuppressWarnings("serial")
public class Personne implements Serializable {

	@Id
	private int id;
	@Column(name="pre_nom")
	private String preNom;
	@Column(name="nom_de_familie")
	private String nomDeFamilie;
	@Column(name="birth_dd")
	private int birthDD;
	@Column(name="birth_mm")
	private int birthMM;
	@Column(name="birth_ccyy")
	private int birthCCYY;
	@Column
	private Date creation;

}

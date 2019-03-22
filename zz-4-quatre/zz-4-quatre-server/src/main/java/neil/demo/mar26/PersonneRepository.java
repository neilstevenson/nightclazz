package neil.demo.mar26;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {

	// Doesn't have to be all primary keys
	@Query("SELECT p.id FROM Personne p WHERE p.id != 6")
    public Collection<Integer> findIds();
    
}

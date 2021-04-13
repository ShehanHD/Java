package best.wecode.csv_db.comuni;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComuniRepository extends JpaRepository<Comuni, Long> {
}

package francescoDattola.viaggiAziendali.repositories;

import francescoDattola.viaggiAziendali.entities.Dipendenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DipendentiRepository extends JpaRepository<Dipendenti, UUID> {
    boolean existsByEmail(String email);
}

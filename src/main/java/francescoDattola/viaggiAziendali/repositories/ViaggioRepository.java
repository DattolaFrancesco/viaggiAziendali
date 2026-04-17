package francescoDattola.viaggiAziendali.repositories;

import francescoDattola.viaggiAziendali.entities.Viaggi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggi, UUID> {
}

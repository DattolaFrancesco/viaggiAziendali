package francescoDattola.viaggiAziendali.repositories;

import francescoDattola.viaggiAziendali.entities.Dipendenti;
import francescoDattola.viaggiAziendali.entities.Prenotazioni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazioni, UUID> {
    boolean existsByViaggi_DipendentiAndViaggi_Date(Dipendenti dipendenti, LocalDate date);
}

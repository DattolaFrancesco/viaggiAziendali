package francescoDattola.viaggiAziendali.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
public class Prenotazioni {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(name="trips_id")
    private Viaggi viaggi;
    private String note;
    public Prenotazioni(String note, Viaggi viaggi) {
        this.note = note;
        this.viaggi = viaggi;
    }
}

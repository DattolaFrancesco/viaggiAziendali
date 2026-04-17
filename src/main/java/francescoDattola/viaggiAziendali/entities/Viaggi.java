package francescoDattola.viaggiAziendali.entities;

import francescoDattola.viaggiAziendali.enums.States;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "trips")
@Getter
@Setter
@NoArgsConstructor
public class Viaggi {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private States states;
    @Column(nullable = false)
    private String destination;
    @ManyToOne
    @JoinColumn(name = "employees_id")
    private Dipendenti dipendenti;

    public Viaggi(LocalDate date, String destination, Dipendenti dipendenti, States states) {
        this.date = date;
        this.destination = destination;
        this.dipendenti = dipendenti;
        this.states = states;
    }
}

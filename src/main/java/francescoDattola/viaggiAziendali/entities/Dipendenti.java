package francescoDattola.viaggiAziendali.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Dipendenti {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String avatar;

    public Dipendenti(String email, String name, String surname, String username) {
        this.avatar = "avatar.cose/200/200";
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.username = username;
    }
}

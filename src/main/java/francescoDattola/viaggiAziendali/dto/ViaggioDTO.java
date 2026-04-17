package francescoDattola.viaggiAziendali.dto;

import francescoDattola.viaggiAziendali.entities.Dipendenti;
import francescoDattola.viaggiAziendali.enums.States;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record ViaggioDTO(
        @NotNull(message = "date can't be blank")
        LocalDate date,
        @NotNull(message = "states can't be blank")
        States states,
        @NotBlank(message = "destination can't be blank")
        String destination,
        @NotNull(message = "employees can't be blank")
        UUID dipendenti,
        String note)
{
}

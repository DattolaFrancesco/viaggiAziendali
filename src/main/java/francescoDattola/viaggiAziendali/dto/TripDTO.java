package francescoDattola.viaggiAziendali.dto;

import francescoDattola.viaggiAziendali.enums.States;
import jakarta.validation.constraints.NotNull;

public record TripDTO(
        @NotNull(message = "states can't be blank")
        States states
) {
}

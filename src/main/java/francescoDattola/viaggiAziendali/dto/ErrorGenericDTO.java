package francescoDattola.viaggiAziendali.dto;

import java.time.LocalDate;

public record ErrorGenericDTO(String message, LocalDate timeStamp) {
}

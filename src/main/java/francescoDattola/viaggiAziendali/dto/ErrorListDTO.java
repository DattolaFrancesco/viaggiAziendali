package francescoDattola.viaggiAziendali.dto;

import java.time.LocalDate;
import java.util.List;

public record ErrorListDTO(String message, LocalDate timeStamp, List<String> errors) {
}

package francescoDattola.viaggiAziendali.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super(id + " don't exist");
    }
}

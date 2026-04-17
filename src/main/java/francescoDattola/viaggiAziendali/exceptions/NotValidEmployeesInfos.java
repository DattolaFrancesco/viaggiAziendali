package francescoDattola.viaggiAziendali.exceptions;

import java.util.List;

public class NotValidEmployeesInfos extends RuntimeException {
    private List<String> errors;
    public NotValidEmployeesInfos(List<String> errors) {
        super("errors in the validations checks");
        this.errors=errors;
    }
    public List<String> getErrors(){
        return this.errors;
    }
}

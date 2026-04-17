package francescoDattola.viaggiAziendali.exceptions;

import francescoDattola.viaggiAziendali.dto.ErrorGenericDTO;
import francescoDattola.viaggiAziendali.dto.ErrorListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(NotValidEmployeesInfos.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorListDTO badEmployeesRequest(NotValidEmployeesInfos ex){
        return new ErrorListDTO(ex.getMessage(), LocalDate.now(),ex.getErrors());
    }
    @ExceptionHandler(NotAvailable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorGenericDTO badEmployeesRequest(NotAvailable ex){
        return new ErrorGenericDTO(ex.getMessage(), LocalDate.now());
    }
    @ExceptionHandler(NotAvailableDate.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorGenericDTO badDateRequest(NotAvailableDate ex){
        return new ErrorGenericDTO(ex.getMessage(), LocalDate.now());
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorGenericDTO badEmployeesIdRequest(NotFoundException ex){
        return new ErrorGenericDTO(ex.getMessage(), LocalDate.now());
    }
    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 500
    public ErrorGenericDTO badDate(DateTimeParseException ex) {
        ex.printStackTrace();
        return new ErrorGenericDTO("date is not valid", LocalDate.now());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 500
    public ErrorGenericDTO badEnum(HttpMessageNotReadableException ex) {
            String message = "Formato JSON non valido";
        if (ex.getMessage().contains("LocalDate")) {
            message = "Formato data non valido, usa: YYYY-MM-DD";
        } if (ex.getMessage().contains("States")) {
            message = "Valore states non valido, usa: INPROGRAMMA o COMPLETATO" ;
        }  if (ex.getMessage().contains("UUID")) {
            message = "Formato UUID non valido";
        }
        return new ErrorGenericDTO(message, LocalDate.now());
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ErrorGenericDTO handleGenericEx(Exception ex) {
        ex.printStackTrace();
        return new ErrorGenericDTO("we currently have an internal error", LocalDate.now());
    }
}

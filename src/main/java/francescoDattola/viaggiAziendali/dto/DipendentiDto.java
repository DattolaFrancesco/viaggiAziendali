package francescoDattola.viaggiAziendali.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DipendentiDto(
        @NotBlank(message = "username can't be blank")
        @Size(min = 3, max = 20, message = "username must be between 3 and 20 characters")
        String username,

        @NotBlank(message = "name can't be blank")
        String name,

        @NotBlank(message = "surname can't be blank")
        String surname,

        @NotBlank(message = "email can't be blank")
        @Email(message = "email is not valid")
        String email
) {}

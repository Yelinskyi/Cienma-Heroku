package project.cinema.spring.dto.request;

import lombok.Data;
import project.cinema.spring.lib.FieldsValueMatch;
import project.cinema.spring.lib.ValidEmail;

import javax.validation.constraints.Size;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
@Data
public class UserRequestDto {
    @ValidEmail
    private String email;
    @Size(min = 8, max = 40)
    private String password;
    private String repeatPassword;
    private String role;
}

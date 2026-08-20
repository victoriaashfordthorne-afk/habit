package MbemX.example.Habit.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {

    @Schema(description = "Unique key identifier", example = "1")
    private Long id;

    @Schema(description = "Google account identifier", example = "23450123")
    private String googleId;

    @NotBlank(message = "name is required")
    @Schema(description = "User's name", example = "Abwenek")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "Invalid email format")
    @Schema(description = "User's email address", example = "sidoline@gmail.com")
    private String email;
}
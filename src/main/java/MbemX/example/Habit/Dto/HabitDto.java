package MbemX.example.Habit.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitDto {
    @Schema(description = "Unique key identified",example = "1")
    private Long id;
    @Schema(description = "name is required here",example = "Bright")
    @Column(nullable = false)
    @NotBlank(message = "name is required")
    private String name;
    @Schema(description = "description is required and need",example = "eat every morning before 12h")
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @Schema(description = "user mus be actives")
    private boolean active;
    @Column(nullable = false)
    @Schema(description = "userid is required",example = "1")
    private Long userId;
}

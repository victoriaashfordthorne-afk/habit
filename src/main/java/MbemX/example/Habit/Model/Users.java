package MbemX.example.Habit.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Schema(description = "Unique identified",example = "1")
    private Long id;
    @NotBlank(message = "googleId is required for identification")
    @Column(nullable = false, unique = true)
    @Schema(description = "user googleId",example = "23450123")
    private  String googleId;
    @Column(nullable = false, unique = true)
    @Schema(description = "name is required",example = "abwenek")
    @NotBlank(message = "name is required for clarification")
    private  String name;
    @Column(nullable = false, unique = true)
    @Email(message = "email is required")
    @NotBlank(message = "example jonn@gmail.com")
    @Schema(description = "email is required",example = "sidoline@gamil.com")
    private String email;
    @Column(nullable = false)
    @Schema(description = "date of creation", example = "2026-07-05T10:30:00")
    private LocalDateTime createAt;


    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Habit> habits = new ArrayList<>();

    @PrePersist
    protected  void  onCreateAt(){
        createAt = LocalDateTime.now();
    }
}

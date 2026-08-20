package MbemX.example.Habit.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "habit")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identified key",example = "1")
    private Long id;
    @Schema(description = "name is required",example = "Abwenek Sidoline")
    @Column(nullable = false)
    @NotBlank(message = "name is required")
    private String name;
    @Schema(description = "description required",example = "drink 2L of water every 20minute")
    @Column(length = 500)
    private String description;
    @Schema(description = "date of the habit register" ,example = "20-12-2025")
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Schema(description = "sidoline isActives",example = "read book every 10 second")
    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_id", nullable = false)
    private Users user;

    @OneToMany(
            mappedBy = "habit",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<HabitCompletion> completions = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

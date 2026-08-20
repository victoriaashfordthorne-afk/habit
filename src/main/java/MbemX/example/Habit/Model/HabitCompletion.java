package MbemX.example.Habit.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "habit_completions",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_habit_completion_date",
                        columnNames = {"habit_id", "completion_date"}
                )
        }
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HabitCompletion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identified",example = "1")
    private Long id;
    @Column(name = "completion_date", nullable = false)
    @Schema(description = "date of completion of work",example = "20-07-2026")
    private LocalDate completionDate;
    @Column(nullable = false)
    @Schema(description = "date create for the user",example = "10-20-204-25")
    private LocalDateTime createdAt;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "habit_id", nullable = false)
    private Habit habit;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}

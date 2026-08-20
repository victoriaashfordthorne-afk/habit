package MbemX.example.Habit.Repository;

import MbemX.example.Habit.Model.HabitCompletion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HabitCompletionRepository
        extends JpaRepository<HabitCompletion, Long> {

    boolean existsByHabitIdAndCompletionDate(
            Long habitId,
            LocalDate completionDate
    );

    List<HabitCompletion> findByHabitId(
            Long habitId
    );

    Optional<HabitCompletion> findByHabitIdAndCompletionDate(
            Long habitId,
            LocalDate completionDate
    );
}

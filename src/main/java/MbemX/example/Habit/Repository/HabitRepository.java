package MbemX.example.Habit.Repository;

import MbemX.example.Habit.Model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitRepository extends JpaRepository<Habit,Long> {
    List<Habit>findByUserId(Long id);
    List<Habit>findByUserIdAndActiveTrue(Long userId);
}

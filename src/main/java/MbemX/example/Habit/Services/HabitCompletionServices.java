package MbemX.example.Habit.Services;

import MbemX.example.Habit.Model.Habit;
import MbemX.example.Habit.Model.HabitCompletion;
import MbemX.example.Habit.Repository.HabitCompletionRepository;
import MbemX.example.Habit.Repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HabitCompletionServices {
    private final HabitCompletionRepository habitCompletionRepository;
    private final HabitRepository habitRepository;
    public HabitCompletionServices(HabitCompletionRepository habitCompletionRepository,HabitRepository habitRepository){
        this.habitCompletionRepository = habitCompletionRepository;
        this.habitRepository = habitRepository;
    }

public HabitCompletion  completeHabit(Long habitId, LocalDate completionDate){
    Habit habit = habitRepository.findById(habitId)
            .orElseThrow(()->new RuntimeException("Habit not found"));

    boolean alreadyCompleted = habitCompletionRepository.existsByHabitIdAndCompletionDate(
            habitId,
            completionDate
    );
   if (alreadyCompleted){
       throw  new RuntimeException("Habit is  already completed");
   }
   HabitCompletion habitCompletion = new HabitCompletion();
    habitCompletion.setCompletionDate(completionDate);
    habitCompletion.setHabit(habit);
    return habitCompletionRepository.save(habitCompletion);
}

public List<HabitCompletion>findCompletionByHabit(Long habitId){
        if (habitRepository.existsById(habitId)){
            throw new RuntimeException("Habit not found");
        }
        return habitCompletionRepository.findByHabitId(habitId);
}
public HabitCompletion findByHabitIdAndCompletionDate(Long habitId, LocalDate completionDate){
        return habitCompletionRepository.findByHabitIdAndCompletionDate(habitId,completionDate)
                .orElseThrow(()->new RuntimeException("No completion found on this date"));
}

public void deleteCompletion(Long completionId){
        HabitCompletion habitCompletion = habitCompletionRepository.findById(completionId)
                .orElseThrow(()->new RuntimeException("Completion not found"));
        habitCompletionRepository.delete(habitCompletion);
}
}

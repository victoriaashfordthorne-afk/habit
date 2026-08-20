package MbemX.example.Habit.Mapper;

import MbemX.example.Habit.Dto.HabitDto;
import MbemX.example.Habit.Model.Habit;
import MbemX.example.Habit.Model.Users;
import org.springframework.stereotype.Component;

@Component
public class HabitMapper {

    public Habit toEntity(HabitDto dto , Users users){
        Habit habit = new Habit();

        habit.setId(dto.getId());
        habit.setName(dto.getName());
         habit.setDescription(dto.getDescription());
          habit.setActive(dto.isActive());
          habit.setUser(users);
          return habit;
    }

    public HabitDto toDto(Habit habit){
        HabitDto dto = new HabitDto();

        dto.setId(habit.getId());
        dto.setDescription(habit.getDescription());
        dto.setName(habit.getName());
        dto.setActive(habit.isActive());

     if (habit.getUser() != null){
         dto.setUserId(habit.getUser().getId());
     }
 return dto;
    }
}

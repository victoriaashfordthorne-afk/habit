package MbemX.example.Habit.Services;

import MbemX.example.Habit.Dto.HabitDto;
import MbemX.example.Habit.Mapper.HabitMapper;
import MbemX.example.Habit.Model.Habit;
import MbemX.example.Habit.Model.Users;
import MbemX.example.Habit.Repository.HabitRepository;
import MbemX.example.Habit.Repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitServices {

    private final HabitRepository habitRepository;
    private final HabitMapper habitMapper;
    private final UsersRepository usersRepository;

    public HabitServices(HabitRepository habitRepository,HabitMapper habitMapper,UsersRepository usersRepository){
        this.habitRepository = habitRepository;
        this.habitMapper = habitMapper;
        this.usersRepository = usersRepository;
    }

    public List<HabitDto> findAllHabit(){
        return habitRepository.findAll()
                .stream()
                .map(habitMapper::toDto)
                .toList();
    }

    public HabitDto findById(Long id){
        Habit habit = habitRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Habit not found"));
        return habitMapper.toDto(habit);
    }

    public HabitDto createHabit(HabitDto dto){
        Users users = usersRepository.findById(dto.getUserId())
                .orElseThrow(()->new RuntimeException("User not found"));

        Habit habit = habitMapper.toEntity(dto,users);
        Habit saveHabit = habitRepository.save(habit);
        return habitMapper.toDto(habit);
    }
    public List<HabitDto>findByUserId(Long id){
         return habitRepository.findByUserId(id)
                 .stream()
                 .map(habitMapper::toDto)
                 .toList();
    }

    public HabitDto UpdateHabit(Long id,HabitDto dto) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found"));


        habit.setName(dto.getName());
        habit.setDescription(dto.getDescription());
        habit.setActive(dto.isActive());

        if (habit.getUser() .getId().equals(dto.getUserId())){
       Users users = usersRepository.findById(dto.getUserId())
               .orElseThrow(()->new RuntimeException("User not found"));
       habit.setUser(users);
        }

      Habit saveHabit =  habitRepository.save(habit);
        return habitMapper.toDto(habit);
    }

    public void deleteHabit(Long id){
        Habit habit = habitRepository.findById(id)
                .orElseThrow(()->new RuntimeException("user not found"));

        habitRepository.delete(habit);
    }
}

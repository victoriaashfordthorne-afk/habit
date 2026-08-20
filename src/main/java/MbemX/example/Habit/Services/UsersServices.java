package MbemX.example.Habit.Services;

import MbemX.example.Habit.Dto.UsersDto;
import MbemX.example.Habit.Mapper.UsersMapper;
import MbemX.example.Habit.Model.Users;
import MbemX.example.Habit.Repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServices {
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    public UsersServices(UsersRepository usersRepository,UsersMapper usersMapper){
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    public List<UsersDto> findAllUsers(){
        return usersRepository.findAll()
                .stream()
                .map(usersMapper::toDto)
                .toList();
    }

    public UsersDto findById(Long id){
        Users users = usersRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found"));

        return usersMapper.toDto(users);
    }

    public UsersDto createUser(UsersDto usersDto) {
        Users users = usersMapper.toEntity(usersDto);

        Users savedUser = usersRepository.save(users);

        return usersMapper.toDto(savedUser);
    }

    public UsersDto UpdateUser(Long id,UsersDto usersDto){
        Users users = usersRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found"));

         users.setName(usersDto.getName());
         users.setEmail(usersDto.getEmail());
         users.setGoogleId(usersDto.getGoogleId());

         Users UpdateUsers = usersRepository.save(users);
         return usersMapper.toDto(UpdateUsers);

    }

    public void  deleteUsers(Long id){
         Users users = usersRepository.findById(id)
                 .orElseThrow(()->new RuntimeException("User not Found"));

         usersRepository.delete(users);
    }
}

package MbemX.example.Habit.Mapper;

import MbemX.example.Habit.Dto.UsersDto;
import MbemX.example.Habit.Model.Users;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {

    public Users toEntity(UsersDto dto){
        Users users = new Users();

        users.setId(dto.getId());
        users.setName(dto.getName());
          users.setGoogleId(dto.getGoogleId());
          users.setEmail(dto.getEmail());

          return users;
    }

    public UsersDto toDto(Users users){
        UsersDto dto = new UsersDto();

        dto.setId(users.getId());
        dto.setName(users.getName());
        dto.setGoogleId(users.getGoogleId());
        dto.setEmail(users.getEmail());

        return dto;
    }
}

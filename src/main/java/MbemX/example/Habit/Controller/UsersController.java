package MbemX.example.Habit.Controller;

import MbemX.example.Habit.Dto.UsersDto;
import MbemX.example.Habit.Services.UsersServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
@Tag(name ="Managing user Api",
description = "Users Api Application for Login")
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersServices usersServices;
    public UsersController(UsersServices usersServices){
        this.usersServices =usersServices;
    }
    @Operation(
            summary = "Get all user",
            description = "All users successfully received "
    )
    @ApiResponse(
            responseCode = "202",
            description = "All user get successfully"
    )
    @GetMapping("/all")
    public ResponseEntity<List<UsersDto>> findAll(){
        List<UsersDto> user = usersServices.findAllUsers();
        return ResponseEntity.ok(user);

    }

    @Operation(
            summary = "Get user by Id",
            description = "All user get with id successfully received"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "202",
                    description = "Received Id successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsersDto>findById(@PathVariable Long id){
        UsersDto user = usersServices.findById(id);
        return ResponseEntity.ok(user);

    }
    @Operation(
            summary = "create user with habitCompletion",
            description = "User with HabitCompletion received successfully"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "UserHabit created successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "UserHabit not found"
            )
    })
    @PostMapping("/add")
    public ResponseEntity<UsersDto> createUsers( @Valid @RequestBody UsersDto dto){
        UsersDto user = usersServices.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Operation(
            summary = "Update all user habit",
            description = "User Habit updated successfully"
    )
 @ApiResponses({
         @ApiResponse(
                 responseCode = "200",
                 description = "Update successfully"
         ),
         @ApiResponse(
                 responseCode = "400",
                 description = "User not found"
         )
 })
    @PutMapping("/{id}")
    public ResponseEntity<UsersDto> updateUsers(@PathVariable Long id, @Valid  @RequestBody UsersDto dto){
        UsersDto user = usersServices.UpdateUser(id, dto);
        return ResponseEntity.ok(user);
    }
@Operation(
        summary = "Delete all user habit",
        description = "user habit deleted successfully"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "203",
                description = "habit delete successfully"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "User not found"

        )
}

)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteUser(@PathVariable Long id){
        usersServices.deleteUsers(id);
        return ResponseEntity.noContent().build();
    }

}

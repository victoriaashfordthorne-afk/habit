package MbemX.example.Habit.Controller;

import MbemX.example.Habit.Dto.HabitDto;
import MbemX.example.Habit.Services.HabitServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Managing Habit api",
description = "Api for all Habit  user")
@RestController
@RequestMapping("habit")
public class HabitController {
    private final HabitServices habitServices;
    public HabitController(HabitServices habitServices){
        this.habitServices = habitServices;
    }
    @Operation(
            summary = "Get all habit user",
            description = "All habit received successfully"
    )
    @ApiResponse(
            responseCode = "202",
            description = "Habit received successfully"
    )
    @GetMapping("/all")
    public ResponseEntity<List<HabitDto>>findAllHabit(){
        List<HabitDto> habit = habitServices.findAllHabit();
        return ResponseEntity.ok(habit);
    }
    @Operation(
            summary = "Get all habit with Id",
            description = "All habit with id received successfully"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Habit received successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Habit not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<HabitDto> findById(@PathVariable Long id){
        HabitDto habit = habitServices.findById(id);
        return ResponseEntity.ok(habit);
    }

    @Operation(
            summary = "Habit created with easy successfully",
            description = "Habit recorded successfully"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Habit created successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Habit not found"
            )
    })
    @PostMapping("add")
    public ResponseEntity<HabitDto> createHabit( @Valid @RequestBody HabitDto dto){
        HabitDto habit = habitServices.createHabit(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(habit);
    }

    @Operation(
            summary = "update user received successfully",
            description = "All update are on date "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "203",
                    description = "Update received successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Habit not found"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<HabitDto> UpdateHabit(@PathVariable Long id, @Valid @RequestBody HabitDto dto){
        HabitDto habit = habitServices.UpdateHabit(id, dto);
        return ResponseEntity.ok(habit);
    }
    @Operation(
            summary = "All habit deleted successfully",
            description = "Habit deleted if not found"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "202",
                    description = "Habit deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Habit not found"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long id){
        habitServices.deleteHabit(id);
        return ResponseEntity.noContent().build();
    }
}

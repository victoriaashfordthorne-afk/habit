package MbemX.example.Habit.Controller;

import MbemX.example.Habit.Model.HabitCompletion;
import MbemX.example.Habit.Services.HabitCompletionServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@Tag(name = "Managing Api habitCompletion",
description = "Api for received habitCompletion")
@RestController
@RequestMapping("habitCompletion")
public class HabitCompletionController {
    private final HabitCompletionServices habitCompletionServices;
    public HabitCompletionController(HabitCompletionServices habitCompletionServices){
        this.habitCompletionServices = habitCompletionServices;
    }
    @Operation(
            summary = "Get all habitCompletion",
            description = "All habitCompletion received successfully"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HabitCompletion received successfully"
    )
    @GetMapping("/habit/{habitId}")
    public ResponseEntity<List<HabitCompletion>>findCompletionByHabit(@PathVariable Long habitId){
        return ResponseEntity.ok(habitCompletionServices.findCompletionByHabit(habitId));
    }
@Operation(
        summary = "Get all habitCompletion with Id",
        description = "All habitCompletion should be received successfully"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "All habitCompletion with id received successfully"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "HabitCompletion not found"
        )
})
    @GetMapping("/habit/{habitId}/date")
    public ResponseEntity<HabitCompletion>findCompletionByDate(@PathVariable Long habitId, @RequestParam LocalDate completionDate){
        return ResponseEntity.ok(habitCompletionServices.findByHabitIdAndCompletionDate(habitId,completionDate));
    }

    @Operation(
            summary = "create a habit if user does not exist",
            description = "habitCompletion create successfully"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HabitCompletion created successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "habitCompletion not found"
            )
    })
    @PostMapping("/habit/{habitId}")
    public ResponseEntity<HabitCompletion>completeHabit(@PathVariable Long habitId,@RequestParam LocalDate completionDate){
        HabitCompletion completion = habitCompletionServices.completeHabit(habitId,completionDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(completion);
    }
    @Operation(
            summary = "delete habitCompletion",
            description = "All habitCompletion should be deleted"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "203",
                    description = "habitCompletion deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "habitCompletion not found"
            )
    })
    @DeleteMapping("/habit/{habitId}")

    public ResponseEntity<Void> deleteCompletion(@PathVariable Long completionId){
        habitCompletionServices.deleteCompletion(completionId);
        return ResponseEntity.noContent().build();
    }
}

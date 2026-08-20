package MbemX.example.Habit.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = " Manging home api",description = "Api for hosting all application")
@RestController
public class HomeController {
    @Operation(
            summary = "user home hosted successfully",
            description = "All user,habit and habitCompletion are hosted life"
    )
    @ApiResponse(
            responseCode = "200",
            description = "hosted successfully"
    )
    @GetMapping("/")
    public String home() {
        return "Habit Tracker API is running";
    }
}

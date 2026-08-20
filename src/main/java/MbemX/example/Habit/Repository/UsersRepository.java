package MbemX.example.Habit.Repository;

import MbemX.example.Habit.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository  extends JpaRepository<Users,Long> {

    Optional<Users>findByGoogleId(String googleId);
    Optional<Users>findByEmail(String email);
}

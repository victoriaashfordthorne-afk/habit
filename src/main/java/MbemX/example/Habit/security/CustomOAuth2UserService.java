package MbemX.example.Habit.security;

import MbemX.example.Habit.Model.Users;
import MbemX.example.Habit.Repository.UsersRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.service.registry.ImportHttpServices;

import java.util.Collections;


@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService
        implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UsersRepository usersRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)
            throws OAuth2AuthenticationException {

        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

        OAuth2User oauth2User = delegate.loadUser(userRequest);

        String googleId = oauth2User.getAttribute("sub");
        String name = oauth2User.getAttribute("name");
        String email = oauth2User.getAttribute("email");

        Users users = usersRepository.findByGoogleId(googleId)
                .orElseGet(() -> {

                    Users newUser = new Users();

                    newUser.setGoogleId(googleId);
                    newUser.setName(name);
                    newUser.setEmail(email);

                    return usersRepository.save(newUser);
                });

        return new DefaultOAuth2User(
                Collections.singleton(
                        new SimpleGrantedAuthority("ROLE_USER")
                ),
                oauth2User.getAttributes(),
                "email"
        );
    }
}


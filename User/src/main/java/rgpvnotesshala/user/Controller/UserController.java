package rgpvnotesshala.user.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rgpvnotesshala.user.DTO.LoginDTO;
import rgpvnotesshala.user.DTO.SignupDTO;
import rgpvnotesshala.user.Model.User;
import rgpvnotesshala.user.Repository.UserRepository;

@RestController
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping ("/signup")
    ResponseEntity<String> signup(@RequestBody SignupDTO userDetails) {
        User newUser = new User();
        newUser.setName(userDetails.getName());
        newUser.setEmail(userDetails.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        newUser.setRole(userDetails.getRole());
        userRepository.save(newUser);

        return ResponseEntity.ok("singup");

    }
    @PostMapping("/auth/login")
    ResponseEntity<String> login(@RequestBody LoginDTO userDetails) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDetails.getEmail(), userDetails.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("Login successful");

    }




    }


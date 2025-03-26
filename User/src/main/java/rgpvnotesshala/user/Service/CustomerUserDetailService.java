package rgpvnotesshala.user.Service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rgpvnotesshala.user.Model.User;
import rgpvnotesshala.user.Repository.UserRepository;

import java.util.Collections;
@Service
public class CustomerUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomerUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        System.out.println("loadUserByUsername: " + email);
        User user = userRepository.findByEmail(email);
        System.out.print(user.getRole());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),  // This should be a hashed password
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }


}

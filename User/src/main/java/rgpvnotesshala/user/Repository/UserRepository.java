package rgpvnotesshala.user.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rgpvnotesshala.user.Model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

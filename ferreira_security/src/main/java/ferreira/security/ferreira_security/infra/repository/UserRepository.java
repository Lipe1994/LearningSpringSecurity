package ferreira.security.ferreira_security.infra.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ferreira.security.ferreira_security.core.entity.User;


public interface UserRepository  extends CrudRepository<User, String>{

    Optional<User> findByUsername(String username);
}

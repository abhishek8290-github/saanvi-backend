package io.saanvi.saanvibackend.user.model.repository;
import io.saanvi.saanvibackend.core.base.BaseRepository;
import io.saanvi.saanvibackend.user.model.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends BaseRepository<User, String> {
    User findByEmail(String email);

    boolean existsByEmail(String email);

    User findByFirstName(String firstName);

}




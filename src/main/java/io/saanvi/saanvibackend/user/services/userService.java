package io.saanvi.saanvibackend.user.services;


import io.saanvi.saanvibackend.core.base.BaseService;
import io.saanvi.saanvibackend.core.exception.common.InvalidParamsProvidedException;
import io.saanvi.saanvibackend.core.exception.common.UserAlreadyExistsException;
import io.saanvi.saanvibackend.core.exception.common.UserNotFoundException;
import io.saanvi.saanvibackend.shared.utils.hashUtil;
import io.saanvi.saanvibackend.user.model.User;
import io.saanvi.saanvibackend.user.repository.UserRepository;
import org.springframework.stereotype.Service;
//import org.apache.commons.validator.routines.EmailValidator;

import java.util.Optional;


@Service
public class userService extends BaseService<User, String> {

    public final UserRepository userRepository;

    public userService(UserRepository userRepository) {
       this.userRepository = userRepository;
    }

    @Override
    protected UserRepository getRepository() {
        return userRepository;
    }

    public Optional<User> findByEmail(String email) throws Exception {

        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public User createUser(User user) throws Exception {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User Already Exists");
        }
        // TODO : EMAIL VERIFICATION !
        String hashedPassword = hashUtil.getHashedPasswordWithSaltAndAlgorithm(user.getPassword());
        user.setPassword(hashedPassword);
        save(user);
        return user;

    }
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findUserById(String Id) throws Exception {
        Optional<User> user = userRepository.findById(Id);

        if (user.isEmpty())
            throw new UserNotFoundException("User Not Found");

        return user.get();

    }


}

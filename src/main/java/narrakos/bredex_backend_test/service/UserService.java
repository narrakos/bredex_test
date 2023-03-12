package narrakos.bredex_backend_test.service;

import narrakos.bredex_backend_test.controller.requestobject.SignupRequest;
import narrakos.bredex_backend_test.entity.User;
import narrakos.bredex_backend_test.repository.UserRepository;
import narrakos.bredex_backend_test.util.ObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(SignupRequest request) {
        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        ObjectValidator.validateObject(user);

        return userRepository.save(user);
    }
}

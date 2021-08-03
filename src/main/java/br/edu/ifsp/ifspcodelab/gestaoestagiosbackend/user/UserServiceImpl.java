package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(String email, String password) {
        if(userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException(email);
        }
        return userRepository.save(new User(email, passwordEncoder.encode(password)));
    }
}

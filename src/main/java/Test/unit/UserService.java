package Test.unit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    @Transactional
    public UserResponse signUp(SignUpRequest signUpRequest){
        User user = new User(signUpRequest.getName(), signUpRequest.getPassword());

        User save = userRepository.save(user);
        return new UserResponse(save.getName(), save.getPassword());
    }

    @Transactional
    public List<UserResponse> loadAll(){
        List<User> users = userRepository.findAll();

        List<UserResponse> collect = users.stream()
                                          .map(m -> new UserResponse(m.getName(), m.getPassword()))
                                          .collect(Collectors.toList());

        return collect;
    }
}
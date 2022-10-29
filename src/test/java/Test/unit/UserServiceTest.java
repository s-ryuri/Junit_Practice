package Test.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원 가입 성공")
    void signUpTest_Ok(){

        //given
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        SignUpRequest signUpRequest = signUpRequest();
        String encryptedPassword = encoder.encode(signUpRequest.getPassword());

        doReturn(new User(signUpRequest.getName(), encryptedPassword))
            .when(userRepository)
            .save(any(User.class));

        //when

        UserResponse userResponse = userService.signUp(signUpRequest);

        //then
        assertThat(userResponse.getName()).isEqualTo(signUpRequest.getName());
        assertThat(encoder.matches(signUpRequest.getPassword(),userResponse.getPassword())).isTrue();

        //verify
        verify(userRepository,times(1)).save(any(User.class));
        verify(passwordEncoder,times(1)).encode(any(String.class));
    }

    @Test
    @DisplayName("사용자 목록 조회")
    void loadAllUser(){
        //given
        doReturn(userList()).when(userRepository)
            .findAll();

        List<UserResponse> userResponses = userService.loadAll();

        assertThat(userResponses.size()).isEqualTo(5);
    }

    private SignUpRequest signUpRequest(){
        return new SignUpRequest("아이디1","비밀번호1");
    }

    private List<User> userList() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            userList.add(new User("test@test.test", "test"));
        }
        return userList;
    }

}
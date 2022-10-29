package Test.unit;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @DisplayName("회원 가입 성공")
    void signUpSuccess() throws Exception{
        //given
        SignUpRequest signUpRequest = signUpRequest();
        UserResponse userResponse = userResponse();


        doReturn(userResponse).when(userService)
            .signUp(any(SignUpRequest.class));

        //when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/users")
                                  .contentType(MediaType.APPLICATION_JSON)
                                  .content(new Gson().toJson(signUpRequest))
        );

        //then
        resultActions.andExpect(status().isCreated())
            .andExpect(jsonPath("name",userResponse.getName()).exists())
            .andExpect(jsonPath("password",userResponse.getPassword()).exists());
    }

    private SignUpRequest signUpRequest(){
        return new SignUpRequest("아이디1","비밀번호1");
    }

    private UserResponse userResponse(){
        return new UserResponse("아이디1","비밀번호1");
    }

    @Test
    @DisplayName("사용자 목록 조회")
    void getAllUsers_Ok() throws Exception {
        //given
        doReturn(userList()).when(userService)
            .loadAll();

        //when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/users")
        );

        //then
        MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();

        UserListResponse userListResponse = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), UserListResponse.class);

        assertThat(userListResponse.getUserResponses().size()).isEqualTo(5);
    }

    private List<UserResponse> userList() {

        List<UserResponse> userResponses = new ArrayList<>();

        for(int i = 0;i<5;i++) {
            userResponses.add(new UserResponse("아이디" + i,"비밀번호" + i));
        }

        return userResponses;
    }

}
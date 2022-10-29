package Test.unit;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<UserResponse> signUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(userService.signUp(signUpRequest));
    }

    @GetMapping("")
    public ResponseEntity<UserListResponse> loadAll(){
        List<UserResponse> userResponses = userService.loadAll();
        return ResponseEntity.ok(new UserListResponse(userResponses));
    }
}

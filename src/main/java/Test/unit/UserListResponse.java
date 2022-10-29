package Test.unit;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class UserListResponse {

    private List<UserResponse> userResponses = new ArrayList<>();

    public UserListResponse(List<UserResponse> userResponses) {
        this.userResponses = userResponses;
    }
}

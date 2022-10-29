package Test.unit;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponse {

    private String name;

    private String password;

    public UserResponse(String name, String password) {
        this.name = name;
        this.password = password;
    }
}

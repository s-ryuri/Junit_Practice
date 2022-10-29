package Test.unit;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    private String name;

    private String password;

    public SignUpRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }
}

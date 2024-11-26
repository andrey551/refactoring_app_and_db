package Raw;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RawAccount {
    private final String username;
    private final String password;
    private final Long id;


    @Override
    public String toString() {
        return "RawAccount{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

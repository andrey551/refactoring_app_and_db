package Raw;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RawLocationReq {
    private String type;
    private String target;

    @Override
    public String toString() {
        return "RawLocationReq{" +
                "type='" + type + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}

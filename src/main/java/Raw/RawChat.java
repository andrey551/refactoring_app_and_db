package Raw;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class RawChat {
    private String from;
    private String to;
    List<RawMessage> messages;
}

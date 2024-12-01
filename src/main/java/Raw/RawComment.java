package Raw;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RawComment {

    private String name;
    private String avatar;
    private String content;
    private Timestamp time;
    private Long rate;
    @Id
    private Long id;

    public RawComment(String name,
                      String avatar,
                      String content,
                      Timestamp time,
                      Long rate) {
        this.avatar = avatar;
        this.name = name;
        this.content = content;
        this.rate = rate;
        this.time = time;
    }
}

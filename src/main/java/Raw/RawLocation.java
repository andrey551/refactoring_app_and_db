package Raw;

import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RawLocation {
    private String name;
    private String address;

    private String avatar;
    private Float longitude;
    private Float latitude;

    private Time open;
    private Time close;

    private Float rating;
    private Long passengers;

    private String type;

    @Override
    public String toString() {
        return "RawLocation{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", open=" + open +
                ", close=" + close +
                ", rating=" + rating +
                ", passengers=" + passengers +
                ", type='" + type + '\'' +
                '}';
    }
}

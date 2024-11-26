package Raw;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RawRecord {
    private Long weight;
    private Long height;
    private Long heart_beat;
    private Long blood_pressure;
    private Long cholesterol;
}

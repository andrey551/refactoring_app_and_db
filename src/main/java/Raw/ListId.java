package Raw;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ListId {
    List<Long> listId;

    public void addId(Long id) {
        this.listId.add(id);
    }

    @Override
    public String toString() {
        String ret = "(";
        for(Long i : listId) {
            ret +=  i.toString() + ", ";
        }

        ret += ")";

        return ret;
    }
}

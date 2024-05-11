package sp.praq.models;

import lombok.*;
import java.io.Serializable;
import java.time.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonId implements Serializable {
    private Group group_id;

    private LocalDateTime class_datetime;

    private Integer room_number;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        LessonId other = (LessonId) obj;

        if (group_id == null) {
            if (other.group_id != null) return false;
        } else if (!group_id.equals(other.group_id)) return false;

        if (class_datetime == null) {
            if (other.class_datetime != null) return false;
        } else if (!class_datetime.equals(other.class_datetime)) return false;

        if (room_number == null) {
            if (other.room_number != null) return false;
        } else if (!room_number.equals(other.room_number)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int res = 1;

        res = prime * res + ((group_id == null) ? 0 : group_id.hashCode());
        res = prime * res + ((class_datetime == null) ? 0 : class_datetime.hashCode());
        res = prime * res + ((room_number == null) ? 0 : room_number.hashCode());

        return res;
    }

    @Override
    public String toString() {
        return "LessonId [group=" + group_id.toString() + ",class_datetime=" + class_datetime.toString() +
                ",room_number" + room_number.toString() + "]";
    }
}

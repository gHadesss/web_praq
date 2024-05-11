package sp.praq.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "classes")
@IdClass(LessonId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group_id;

    @Id
    @Column(name = "class_datetime", columnDefinition = "TIMESTAMP")
    private LocalDateTime class_datetime;

    @Id
    @Column(name = "room_number")
    private Integer room_number;

    @Column(name = "class_duration")
    @NonNull
    private Double class_duration;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Lesson other = (Lesson) obj;

        if (group_id == null) {
            if (other.group_id != null) return false;
        } else if (!group_id.equals(other.group_id)) return false;

        if (class_datetime == null) {
            if (other.class_datetime != null) return false;
        } else if (!class_datetime.equals(other.class_datetime)) return false;

        if (room_number == null) {
            if (other.room_number != null) return false;
        } else if (!room_number.equals(other.room_number)) return false;

        if (class_duration == null) {
            if (other.class_duration != null) return false;
        } else if (!class_duration.equals(other.class_duration)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int res = 1;

        res = prime * res + ((group_id == null) ? 0 : group_id.hashCode());
        res = prime * res + ((class_datetime == null) ? 0 : class_datetime.hashCode());
        res = prime * res + ((room_number == null) ? 0 : room_number.hashCode());
        res = prime * res + ((class_duration == null) ? 0 : class_duration.hashCode());

        return res;
    }

    @Override
    public String toString() {
        return "Lesson [group=" + group_id.toString() + ",class_datetime=" + class_datetime.toString() +
                ",room_number" + room_number.toString() + ",class_duration" + class_duration.toString() + "]";
    }
}

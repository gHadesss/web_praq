package sp.praq.models;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentGroupId implements Serializable {
    private Student student_id;

    private Group group_id;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        StudentGroupId other = (StudentGroupId) obj;

        if (group_id == null) {
            if (other.group_id != null) return false;
        } else if (!group_id.equals(other.group_id)) return false;

        if (student_id == null) {
            if (other.student_id != null) return false;
        } else if (!student_id.equals(other.student_id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int res = 1;

        res = prime * res + ((group_id == null) ? 0 : group_id.hashCode());
        res = prime * res + ((student_id == null) ? 0 : student_id.hashCode());

        return res;
    }

    @Override
    public String toString() {
        return "StudentGroupId [student=" + student_id.toString() + ",group=" + group_id.toString() + "]";
    }
}

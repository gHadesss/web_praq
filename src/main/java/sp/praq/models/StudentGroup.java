package sp.praq.models;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "group_of_students")
@IdClass(StudentGroupId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentGroup {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student_id;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group_id;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        StudentGroup other = (StudentGroup) obj;

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
        return "StudentGroup [student=" + student_id.toString() + ",group=" + group_id.toString() + "]";
    }
}

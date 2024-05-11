package sp.praq.models;

import lombok.*;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @NonNull
    private Course course_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tutor_id", referencedColumnName = "id")
    @NonNull
    private Tutor tutor_id;

    @OneToMany(mappedBy = "group_id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<StudentGroup> students_of_group = new ArrayList<StudentGroup>();

    @OneToMany(mappedBy = "group_id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @OneToMany(mappedBy = "group_id", fetch = FetchType.EAGER)
    private List<Lesson> classes_of_group = new ArrayList<Lesson>();

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Group other = (Group) obj;

        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;

        if (course_id == null) {
            if (other.course_id != null) return false;
        } else if (!course_id.equals(other.course_id)) return false;

        if (tutor_id == null) {
            if (other.tutor_id != null) return false;
        } else if (!tutor_id.equals(other.tutor_id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int res = 1;

        res = prime * res + ((id == null) ? 0 : id.hashCode());
        res = prime * res + ((tutor_id == null) ? 0 : tutor_id.hashCode());
        res = prime * res + ((course_id == null) ? 0 : course_id.hashCode());

        return res;
    }

    @Override
    public String toString() {
        return "Group [id=" + id.toString() + ",course=" + course_id.toString() + ",tutor=" + tutor_id.toString() + "]";
    }
}

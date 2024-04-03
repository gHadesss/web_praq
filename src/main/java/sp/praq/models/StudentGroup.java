package sp.praq.models;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "groups_of_students")
@IdClass(StudentGroupId.class)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StudentGroup {
    @Id
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // all?
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student_id;

    @Id
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // all?
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group_id;
}

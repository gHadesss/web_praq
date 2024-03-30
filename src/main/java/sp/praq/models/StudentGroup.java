package sp.praq.models;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "groups_of_students")
@IdClass(StudentGroupId.class)
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
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

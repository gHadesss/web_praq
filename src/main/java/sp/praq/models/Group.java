package sp.praq.models;

import lombok.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "groups")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Group implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tutor_id", referencedColumnName = "id")
    private Tutor tutor_id;

    @OneToMany(mappedBy = "groups_of_students", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentGroup> groups_of_students;

    // @OneToMany classes
}

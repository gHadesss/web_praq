package sp.praq.models;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Course implements CommonEntity<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "title")
    @NonNull
    private String title;

    @Column(nullable = false, name = "total_hours")
    @NonNull
    private double total_hours;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company_id;

    @Column(nullable = false, name = "description")
    @NonNull
    private String description;

    // @OneToMany(mappedBy = "courses", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Group> courses_group;
}

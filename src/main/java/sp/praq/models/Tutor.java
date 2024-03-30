package sp.praq.models;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "tutors")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Tutor implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company_id;

    @Column(nullable = false, name = "surname")
    @NonNull
    private String surname;

    @Column(nullable = false, name = "name")
    @NonNull
    private String name;

    @Column(nullable = false, name = "patronymic")
    @NonNull
    private String patronymic;

    @Column(nullable = false, name = "description")
    @NonNull
    private String description;

    @Column(nullable = false, name = "email")
    @NonNull
    private String email;

    @Column(nullable = false, name = "phone_number")
    @NonNull
    private String phone_number;

//     @OneToMany(mappedBy = "tutors", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//     private List<Group> tutors_group;
}

package sp.praq.models;

import lombok.*;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "tutors")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Tutor {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company_id;

    @Column(name = "surname")
    @NonNull
    private String surname;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "patronymic")
    @NonNull
    private String patronymic;

    @Column(name = "description")
    @NonNull
    private String description;

    @Column(name = "email")
    @NonNull
    private String email;

    @Column(name = "phone_number")
    @NonNull
    private String phone_number;

    @OneToMany(mappedBy = "tutor_id", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Group> tutors_group;
}

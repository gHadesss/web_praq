package sp.praq.models;

import lombok.*;
import javax.persistence.*;


@Entity
@Table(name = "students")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Student implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "surname")
    @NonNull
    private String surname;

    @Column(nullable = false, name = "name")
    @NonNull
    private String name;

    @Column(nullable = false, name = "patronymic")
    @NonNull
    private String patronymic;

    @Column(nullable = false, name = "email")
    @NonNull
    private String email;

    @Column(nullable = false, name = "phone_number")
    @NonNull
    private String phone_number;

    // OneToMany: students -> groups_of_students;
}

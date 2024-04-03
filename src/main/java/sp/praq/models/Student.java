package sp.praq.models;

import lombok.*;
import jakarta.persistence.*;
import java.util.*;


@Entity
@Table(name = "students")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "surname")
    @NonNull
    private String surname;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "patronymic")
    @NonNull
    private String patronymic;

    @Column(name = "email")
    @NonNull
    private String email;

    @Column(name = "phone_number")
    @NonNull
    private String phone_number;

    @OneToMany(mappedBy = "student_id", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentGroup> groups_of_student;
}

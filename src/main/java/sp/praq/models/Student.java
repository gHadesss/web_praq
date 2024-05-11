package sp.praq.models;

import lombok.*;
import jakarta.persistence.*;
import java.util.*;


@Entity
@Table(name = "students")
@Getter
@Setter
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


    @OneToMany(mappedBy = "student_id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<StudentGroup> groups_of_student = new ArrayList<StudentGroup>();

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Student other = (Student) obj;

        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;

        if (surname == null) {
            if (other.surname != null) return false;
        } else if (!surname.equals(other.surname)) return false;

        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;

        if (patronymic == null) {
            if (other.patronymic != null) return false;
        } else if (!patronymic.equals(other.patronymic)) return false;

        if (email == null) {
            if (other.email != null) return false;
        } else if (!email.equals(other.email)) return false;

        if (phone_number == null) {
            if (other.phone_number != null) return false;
        } else if (!phone_number.equals(other.phone_number)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int res = 1;

        res = prime * res + ((id == null) ? 0 : id.hashCode());
        res = prime * res + ((surname == null) ? 0 : surname.hashCode());
        res = prime * res + ((name == null) ? 0 : name.hashCode());
        res = prime * res + ((patronymic == null) ? 0 : patronymic.hashCode());
        res = prime * res + ((email == null) ? 0 : email.hashCode());
        res = prime * res + ((phone_number == null) ? 0 : phone_number.hashCode());

        return res;
    }

    @Override
    public String toString() {
        return "Student [id=" + id.toString() + ",surname=" + surname + ",name=" + name +
                ",patronymic=" + patronymic + ",email=" + email + ",phone_number=" + phone_number + "]";
    }
}

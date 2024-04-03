package sp.praq.models;

import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.*;


@Entity
@Table(name = "companies")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Company {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title")
    @NonNull
    private String title;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "address")
    @NonNull
    private Address address;

    @OneToMany(mappedBy = "company_id", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses_company;
}

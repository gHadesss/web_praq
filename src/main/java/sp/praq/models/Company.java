package sp.praq.models;

import lombok.*;
import javax.persistence.*;
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
public class Company implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "title")
    @NonNull
    private String title;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(nullable = false, name = "address")
    private Address address;

    @OneToMany(mappedBy = "courses", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Company> courses_company;
}

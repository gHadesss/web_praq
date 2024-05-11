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
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "company_id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Course> courses_company = new ArrayList<Course>();

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Company other = (Company) obj;

        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;

        if (title == null) {
            if (other.title != null) return false;
        } else if (!title.equals(other.title)) return false;

        if (address == null) {
            if (other.address != null) return false;
        } else if (!address.equals(other.address)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int res = 1;

        res = prime * res + ((id == null) ? 0 : id.hashCode());
        res = prime * res + ((title == null) ? 0 : title.hashCode());
        res = prime * res + ((address == null) ? 0 : address.hashCode());

        return res;
    }

    @Override
    public String toString() {
        return "Company [id=" + id.toString() + ",title=" + title + ",tutor=" + address.toString() + "]";
    }
}

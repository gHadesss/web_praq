package sp.praq.models;

import lombok.*;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title")
    @NonNull
    private String title;

    @Column(name = "total_hours")
    @NonNull
    private Double total_hours;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company_id;

    @Column(name = "description")
    @NonNull
    private String description;

    @OneToMany(mappedBy = "course_id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Group> courses_group = new ArrayList<Group>();

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Course other = (Course) obj;

        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;

        if (title == null) {
            if (other.title != null) return false;
        } else if (!title.equals(other.title)) return false;

        if (total_hours == null) {
            if (other.total_hours != null) return false;
        } else if (!total_hours.equals(other.total_hours)) return false;

        if (company_id == null) {
            if (other.company_id != null) return false;
        } else if (!company_id.equals(other.company_id)) return false;

        if (description == null) {
            if (other.description != null) return false;
        } else if (!description.equals(other.description)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int res = 1;

        res = prime * res + ((id == null) ? 0 : id.hashCode());
        res = prime * res + ((title == null) ? 0 : title.hashCode());
        res = prime * res + ((total_hours == null) ? 0 : total_hours.hashCode());
        res = prime * res + ((company_id == null) ? 0 : company_id.hashCode());
        res = prime * res + ((description == null) ? 0 : description.hashCode());

        return res;
    }

    @Override
    public String toString() {
        return "Course [id=" + id.toString() + ",title=" + title + ",total_hours=" + total_hours.toString() +
                ",company_id=" + company_id.toString() + ",description=" + description + "]";
    }
}

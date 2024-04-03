package sp.praq.models;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class StudentGroupId implements Serializable {
    private Student student_id;

    private Group group_id;
}

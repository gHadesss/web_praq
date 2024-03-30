package sp.praq.models;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class StudentGroupId implements Serializable {
    @NonNull
    private Student student_id;

    @NonNull
    private Group group_id;
}

package sp.praq.models;

import lombok.*;
import java.io.Serializable;
import java.time.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class LessonId implements Serializable {
    private Group group_id;

    private LocalDateTime class_datetime;

    private int room_number;
}

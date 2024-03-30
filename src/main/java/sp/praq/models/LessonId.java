package sp.praq.models;

import lombok.*;
import java.io.Serializable;
import java.time.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class LessonId implements Serializable {
    @NonNull
    private Group group_id;

    @NonNull
    private LocalDateTime class_datetime;

    @NonNull
    private int room_number;
}

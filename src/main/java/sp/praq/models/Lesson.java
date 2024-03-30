package sp.praq.models;

import javax.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "classes")
@IdClass(LessonId.class)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Lesson {
    @Id
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // all?
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group_id;

    @Id
    @Column(name = "class_datetime", columnDefinition = "TIMESTAMP")
    private LocalDateTime class_datetime;

    @Id
    @Column(name = "room_number")
    private int room_number;

    @Column(name = "class_duration")
    private double class_duration;
}

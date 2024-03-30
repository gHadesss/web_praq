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
public class Address implements Serializable {
    private String city;
    private String street;
    private String house;
}

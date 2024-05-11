package sp.praq.models;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address implements Serializable {
    private String city;
    private String street;
    private String house;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Address other = (Address) obj;

        if (city == null) {
            if (other.city != null) return false;
        } else if (!city.equals(other.city)) return false;

        if (street == null) {
            if (other.street != null) return false;
        } else if (!street.equals(other.street)) return false;

        if (house == null) {
            if (other.house != null) return false;
        } else if (!house.equals(other.house)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int res = 1;

        res = prime * res + ((city == null) ? 0 : city.hashCode());
        res = prime * res + ((street == null) ? 0 : street.hashCode());
        res = prime * res + ((house == null) ? 0 : house.hashCode());

        return res;
    }

    @Override
    public String toString() {
        return "Address [city=" + city + ",street=" + street + ",house=" + house + "]";
    }
}

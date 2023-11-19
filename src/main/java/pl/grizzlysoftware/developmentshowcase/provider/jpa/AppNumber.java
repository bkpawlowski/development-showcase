package pl.grizzlysoftware.developmentshowcase.provider.jpa;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
@Entity
@Setter(AccessLevel.PACKAGE)
@Getter
@Table(schema = "devshowcase", name = "T_NUMBER")
class AppNumber extends BaseEntity {

    @Column(name = "VALUE")
    private BigDecimal value;

    public AppNumber() {
    }

    public AppNumber(BigDecimal value) {
        this.value = value;
    }

    public static AppNumber of(double n) {
        return new AppNumber(valueOf(n));
    }
}

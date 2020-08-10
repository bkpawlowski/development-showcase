package pl.grizzlysoftware.developmentshowcase.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
@Data
@Entity
@Table(schema = "devshowcase", name = "T_NUMBER")
public class AppNumber {
    @Id
    @Column(name = "ID")
    private Long id;

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

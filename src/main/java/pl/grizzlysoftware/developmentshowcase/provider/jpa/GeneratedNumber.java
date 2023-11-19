package pl.grizzlysoftware.developmentshowcase.provider.jpa;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Collection;

@Getter
@Entity
@Table(schema = "devshowcase", name = "T_GENERATED_NUMBER")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class GeneratedNumber extends BaseEntity {

    @Column(name = "VALUE")
    private BigDecimal value;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "generatedNumber")
    private Collection<GeneratedNumberGenerationTime> generationTimes;

    public GeneratedNumber(BigDecimal value, Collection<GeneratedNumberGenerationTime> generationTimes) {
        this.value = value;
        this.generationTimes = generationTimes;
        this.generationTimes.forEach(e -> e.setGeneratedNumber(this));
    }

}

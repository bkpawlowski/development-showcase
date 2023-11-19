package pl.grizzlysoftware.developmentshowcase.provider.jpa;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(schema = "devshowcase", name = "T_GENERATED_NUMBER_GENERATION_TIME")
@Getter
@Setter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class GeneratedNumberGenerationTime extends BaseEntity {

    @Column(name = "EPOCH_TIME")
    private long epochTime;

    @Enumerated(STRING)
    @Column(name = "TYPE")
    private GenerationTimeType type;

    @ManyToOne
    private GeneratedNumber generatedNumber;

    public GeneratedNumberGenerationTime(GeneratedNumber generatedNumber, long epochTime, GenerationTimeType type) {
        this.generatedNumber = generatedNumber;
        this.epochTime = epochTime;
        this.type = type;
    }

    public GeneratedNumberGenerationTime(long epochTime, GenerationTimeType type) {
        this.epochTime = epochTime;
        this.type = type;
    }
}

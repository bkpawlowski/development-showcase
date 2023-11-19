package pl.grizzlysoftware.developmentshowcase.provider.jpa;

import pl.grizzlysoftware.developmentshowcase.domain.NumberProvider;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.util.Objects.requireNonNull;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
class JpaNumberProvider implements NumberProvider {
    private final AppNumberRepository numberRepository;

    public JpaNumberProvider(AppNumberRepository numberRepository) {
        this.numberRepository = requireNonNull(numberRepository);
    }

    @Override
    public BigDecimal next() {
        return numberRepository.findAll()
                .parallelStream()
                .findAny()
                .map(AppNumber::getValue)
                .orElse(ZERO);
    }
}

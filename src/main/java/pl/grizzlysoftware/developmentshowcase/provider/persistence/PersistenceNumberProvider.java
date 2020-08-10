package pl.grizzlysoftware.developmentshowcase.provider.persistence;

import pl.grizzlysoftware.developmentshowcase.businesslogic.provider.NumberProvider;
import pl.grizzlysoftware.developmentshowcase.repository.AppNumberRepository;

import java.math.BigDecimal;

import static java.math.BigDecimal.*;
import static java.util.Objects.requireNonNull;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
public class PersistenceNumberProvider implements NumberProvider {
    private AppNumberRepository numberRepository;

    public PersistenceNumberProvider(AppNumberRepository numberRepository) {
        this.numberRepository = requireNonNull(numberRepository);
    }

    @Override
    public BigDecimal getNumber() {
        return numberRepository.findAll()
                .stream()
                .findAny()
                .map(e -> e.getValue())
                .orElse(ZERO);
    }
}

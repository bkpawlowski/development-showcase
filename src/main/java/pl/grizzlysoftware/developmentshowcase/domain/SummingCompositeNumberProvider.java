package pl.grizzlysoftware.developmentshowcase.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
public class SummingCompositeNumberProvider implements NumberProvider {
    private final Collection<NumberProvider> numberProviders;

    public SummingCompositeNumberProvider(Collection<NumberProvider> numberProviders) {
        if (numberProviders == null || numberProviders.isEmpty()) {
            throw new IllegalArgumentException("Number providers cannot be neither null nor empty");
        }
        this.numberProviders = new ArrayList<>(numberProviders);
    }

    @Override
    public BigDecimal next() {
        return numberProviders.parallelStream()
                .map(NumberProvider::next)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

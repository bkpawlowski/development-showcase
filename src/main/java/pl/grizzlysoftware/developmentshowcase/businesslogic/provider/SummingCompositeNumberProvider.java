package pl.grizzlysoftware.developmentshowcase.businesslogic.provider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
public class SummingCompositeNumberProvider implements NumberProvider {
    private Collection<NumberProvider> numberProviders;

    public SummingCompositeNumberProvider(Collection<NumberProvider> numberProviders) {
        if (numberProviders == null || numberProviders.isEmpty()) {
            throw new IllegalArgumentException("Number providers cannot be neither null nor empty");
        }
        this.numberProviders = new ArrayList<>(numberProviders);
    }

    @Override
    public BigDecimal getNumber() {
        return numberProviders.stream()
                .map(e -> e.getNumber())
                .reduce(new BigDecimal(0), (e1, e2) -> e1.add(e2));
    }
}

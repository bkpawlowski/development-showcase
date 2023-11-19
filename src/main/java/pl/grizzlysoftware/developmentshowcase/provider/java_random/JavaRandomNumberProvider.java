package pl.grizzlysoftware.developmentshowcase.provider.java_random;

import pl.grizzlysoftware.developmentshowcase.domain.NumberProvider;

import java.math.BigDecimal;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
class JavaRandomNumberProvider implements NumberProvider {
    @Override
    public BigDecimal next() {
        return new BigDecimal(current().nextDouble());
    }
}

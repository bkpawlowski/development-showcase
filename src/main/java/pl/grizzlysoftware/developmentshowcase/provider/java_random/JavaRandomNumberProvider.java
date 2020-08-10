package pl.grizzlysoftware.developmentshowcase.provider.java_random;

import pl.grizzlysoftware.developmentshowcase.businesslogic.provider.NumberProvider;

import java.math.BigDecimal;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
public class JavaRandomNumberProvider implements NumberProvider {
    @Override
    public BigDecimal getNumber() {
        return new BigDecimal(current().nextDouble());
    }
}

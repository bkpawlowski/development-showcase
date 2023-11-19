package pl.grizzlysoftware.developmentshowcase.domain;

import java.math.BigDecimal;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
public interface NumberProvider {
    BigDecimal next();
}

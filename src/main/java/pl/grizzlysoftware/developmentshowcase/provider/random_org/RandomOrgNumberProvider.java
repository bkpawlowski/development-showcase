package pl.grizzlysoftware.developmentshowcase.provider.random_org;

import pl.grizzlysoftware.developmentshowcase.businesslogic.exception.GetNumberException;
import pl.grizzlysoftware.developmentshowcase.businesslogic.provider.NumberProvider;

import java.io.IOException;
import java.math.BigDecimal;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
public class RandomOrgNumberProvider implements NumberProvider {
    private RandomOrgService service;

    public RandomOrgNumberProvider(RandomOrgService service) {
        this.service = requireNonNull(service);
    }

    @Override
    public BigDecimal getNumber() {
        try {
            var result = service.randomInteger(1, 100_000_000).execute();
            if (!result.isSuccessful()) {
                throw new GetNumberException(format("Requesting service unsuccessful: %s", result.code()));
            }
            return new BigDecimal(result.body());
        } catch (IOException e) {
            throw new GetNumberException("Unable to get number", e);
        }
    }
}

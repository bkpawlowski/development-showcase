package pl.grizzlysoftware.developmentshowcase.web.mapper;

import pl.grizzlysoftware.developmentshowcase.web.dto.NumberDto;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
public class BigDecimalToNumberDtoMapper implements Function<BigDecimal, NumberDto> {
    @Override
    public NumberDto apply(BigDecimal input) {
        if (input == null) {
            throw new IllegalArgumentException("input cannot be null");
        }
        var output = new NumberDto();
        output.value = input;
        return output;
    }
}

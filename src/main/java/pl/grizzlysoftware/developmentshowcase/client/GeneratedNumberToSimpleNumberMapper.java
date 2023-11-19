package pl.grizzlysoftware.developmentshowcase.client;

import pl.grizzlysoftware.developmentshowcase.domain.GeneratedNumber;

import java.util.function.Function;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
class GeneratedNumberToSimpleNumberMapper implements Function<GeneratedNumber, SimpleNumber> {
    @Override
    public SimpleNumber apply(GeneratedNumber input) {
        if (input == null) {
            throw new IllegalArgumentException("input cannot be null");
        }
        var output = new SimpleNumber();
        output.value = input.getValue();
        return output;
    }
}

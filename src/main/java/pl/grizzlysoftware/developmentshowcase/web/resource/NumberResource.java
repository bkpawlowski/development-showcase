package pl.grizzlysoftware.developmentshowcase.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.grizzlysoftware.developmentshowcase.businesslogic.provider.NumberProvider;
import pl.grizzlysoftware.developmentshowcase.web.dto.NumberDto;
import pl.grizzlysoftware.developmentshowcase.web.mapper.BigDecimalToNumberDtoMapper;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
@RestController
@RequestMapping("/number/v1")
public class NumberResource {

    private NumberProvider numberProvider;
    private BigDecimalToNumberDtoMapper toNumberDtoMapper;

    @Autowired
    public NumberResource(NumberProvider numberProvider) {
        this.numberProvider = numberProvider;
        this.toNumberDtoMapper = new BigDecimalToNumberDtoMapper();
    }

    @GetMapping("/random")
    public NumberDto getNumber() {
        var output = toNumberDtoMapper.apply(numberProvider.getNumber());
        return output;
    }
}

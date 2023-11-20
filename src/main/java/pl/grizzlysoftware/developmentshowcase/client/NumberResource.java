package pl.grizzlysoftware.developmentshowcase.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.grizzlysoftware.developmentshowcase.domain.GeneratedNumber;
import pl.grizzlysoftware.developmentshowcase.domain.GeneratedNumberFinder;
import pl.grizzlysoftware.developmentshowcase.domain.NumberGenerator;

import java.util.Collection;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
@RestController
@RequestMapping("/number/v1")
class NumberResource {

    private final NumberGenerator numberGenerator;
    private final GeneratedNumberFinder generatedNumberFinder;
    private final GeneratedNumberToSimpleNumberMapper toSimpleNumberMapper;

    public NumberResource(GeneratedNumberFinder generatedNumberFinder, NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
        this.generatedNumberFinder = generatedNumberFinder;
        this.toSimpleNumberMapper = new GeneratedNumberToSimpleNumberMapper();
    }

    @GetMapping("/random")
    SimpleNumber generateNumber() {
        return toSimpleNumberMapper.apply(numberGenerator.next());
    }

    @GetMapping("/generated")
    Collection<GeneratedNumber> findGeneratedNumbers() {
        return generatedNumberFinder.findAll();
    }

    @PostMapping("/batch/async")
    void generateBatch(@RequestParam(defaultValue = "5") int numbersToGenerate) {
        numberGenerator.generateBatch(numbersToGenerate);
    }

    @PostMapping("/cache/invalidate")
    void invalidateCache() {
        generatedNumberFinder.invalidateCache();
    }

}

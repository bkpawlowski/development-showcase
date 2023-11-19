package pl.grizzlysoftware.developmentshowcase.domain;

import lombok.RequiredArgsConstructor;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class GeneratedNumberFinder {

    private final Collection<GeneratedNumberProvider> generatedNumberProviders;

    public Collection<GeneratedNumber> findAll() {
        return generatedNumberProviders.parallelStream()
                .map(GeneratedNumberProvider::findAll)
                .flatMap(Collection::stream)
                .collect(toList());
    }
}

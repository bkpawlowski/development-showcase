package pl.grizzlysoftware.developmentshowcase.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
public class GeneratedNumberFinder {
    private static final String GENERATED_NUMBERS_CACHE_KEY = " pl.grizzlysoftware.developmentshowcase.domain.GENERATED_NUMBERS";

    private final Collection<GeneratedNumberProvider> generatedNumberProviders;

    @Transactional
    @Cacheable(GENERATED_NUMBERS_CACHE_KEY)
    public Collection<GeneratedNumber> findAll() {
        log.info("Finding generated numbers...");
        return generatedNumberProviders.parallelStream()
            .map(GeneratedNumberProvider::findAll)
            .flatMap(Collection::stream)
            .collect(toList());
    }

    @CacheEvict(GENERATED_NUMBERS_CACHE_KEY)
    public void invalidateCache() {
        //no-op
        log.info("Cache eviction triggered");
    }
}

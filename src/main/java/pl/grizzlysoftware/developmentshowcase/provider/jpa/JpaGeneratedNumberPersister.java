package pl.grizzlysoftware.developmentshowcase.provider.jpa;

import pl.grizzlysoftware.developmentshowcase.domain.GeneratedNumber;
import pl.grizzlysoftware.developmentshowcase.domain.GeneratedNumberPersister;

import java.util.List;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

class JpaGeneratedNumberPersister implements GeneratedNumberPersister {
    private final GeneratedNumberRepository generatedNumberRepository;
    private final DomainGeneratedNumberToJpaGeneratedNumberMapper toJpaGeneratedNumberMapper;

    public JpaGeneratedNumberPersister(GeneratedNumberRepository generatedNumberRepository) {
        this.generatedNumberRepository = requireNonNull(generatedNumberRepository);
        toJpaGeneratedNumberMapper = new DomainGeneratedNumberToJpaGeneratedNumberMapper();
    }

    @Override
    public void persist(GeneratedNumber in) {
        generatedNumberRepository.save(toJpaGeneratedNumberMapper.apply(in));
    }

    static class DomainGeneratedNumberToJpaGeneratedNumberMapper implements Function<GeneratedNumber,
            pl.grizzlysoftware.developmentshowcase.provider.jpa.GeneratedNumber> {

        @Override
        public pl.grizzlysoftware.developmentshowcase.provider.jpa.GeneratedNumber apply(GeneratedNumber generatedNumber) {
            final var generationTime = generatedNumber.getGenerationTime();
            final var startedAt = new GeneratedNumberGenerationTime(generationTime.getElapsedTime(), GenerationTimeType.STARTED_AT);
            final var finishedAt = new GeneratedNumberGenerationTime(generationTime.getElapsedTime(), GenerationTimeType.FINISHED_AT);
            final var elapsedTime = new GeneratedNumberGenerationTime(generationTime.getElapsedTime(), GenerationTimeType.ELAPSED_TIME);

            return new pl.grizzlysoftware.developmentshowcase.provider.jpa.GeneratedNumber(generatedNumber.getValue(), List.of(startedAt, finishedAt, elapsedTime));
        }
    }
}

package pl.grizzlysoftware.developmentshowcase.provider.jpa;

import pl.grizzlysoftware.developmentshowcase.domain.GeneratedNumber;
import pl.grizzlysoftware.developmentshowcase.domain.GeneratedNumberProvider;
import pl.grizzlysoftware.developmentshowcase.domain.GenerationTime;

import java.util.Collection;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

class JpaGeneratedNumberProvider implements GeneratedNumberProvider {

    private final GeneratedNumberRepository generatedNumberRepository;

    private final JpaGeneratedNumberToDomainGeneratedNumberMapper toDomainGeneratedNumberMapper;

    JpaGeneratedNumberProvider(GeneratedNumberRepository generatedNumberRepository) {
        this.generatedNumberRepository = requireNonNull(generatedNumberRepository);
        toDomainGeneratedNumberMapper = new JpaGeneratedNumberToDomainGeneratedNumberMapper();
    }


    @Override
    public Collection<GeneratedNumber> findAll() {
        return generatedNumberRepository.findAll()
                .stream()
                .map(toDomainGeneratedNumberMapper)
                .collect(toList());
    }

    static class JpaGeneratedNumberToDomainGeneratedNumberMapper implements Function<pl.grizzlysoftware.developmentshowcase.provider.jpa.GeneratedNumber,
            GeneratedNumber> {

        @Override
        public GeneratedNumber apply(pl.grizzlysoftware.developmentshowcase.provider.jpa.GeneratedNumber in) {
            return GeneratedNumber.builder()
                    .value(in.getValue())
                    .generationTime(mapGenerationTime(in.getGenerationTimes()))
                    .build();
        }

        private GenerationTime mapGenerationTime(Collection<GeneratedNumberGenerationTime> in) {

            final var startedAt = in.stream().filter(e -> GenerationTimeType.STARTED_AT == e.getType())
                    .findFirst()
                    .map(GeneratedNumberGenerationTime::getEpochTime)
                    .orElse(-1L);
            final var finishedAt = in.stream().filter(e -> GenerationTimeType.FINISHED_AT == e.getType())
                    .findFirst()
                    .map(GeneratedNumberGenerationTime::getEpochTime)
                    .orElse(-1L);
            //this could be actually calculated from these above
            final var elapsedTime = in.stream().filter(e -> GenerationTimeType.ELAPSED_TIME == e.getType())
                    .findFirst()
                    .map(GeneratedNumberGenerationTime::getEpochTime)
                    .orElse(-1L);

            return new GenerationTime(
                    startedAt,
                    finishedAt,
                    elapsedTime
            );
        }
    }
}

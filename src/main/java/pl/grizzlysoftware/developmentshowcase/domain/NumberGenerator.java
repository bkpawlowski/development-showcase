package pl.grizzlysoftware.developmentshowcase.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class NumberGenerator {
    private final SummingCompositeNumberProvider numberProvider;
    private final GeneratedNumberPersister generatedNumberPersister;

    @Transactional
    public GeneratedNumber next() {
        final var startedAt = System.nanoTime();
        final var nextNumber = numberProvider.next();
        final var finishedAt = System.nanoTime();
        final var elapsedTime = finishedAt - startedAt;



        //this could be asynchronous
        final var generationTime = new GenerationTime(startedAt, finishedAt, elapsedTime);
        final var generatedNumber = new GeneratedNumber(nextNumber, generationTime);
        generatedNumberPersister.persist(generatedNumber);

        return generatedNumber;
    }
}

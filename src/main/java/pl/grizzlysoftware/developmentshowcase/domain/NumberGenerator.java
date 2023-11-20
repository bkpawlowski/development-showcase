package pl.grizzlysoftware.developmentshowcase.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.lang.System.nanoTime;

@Slf4j
@RequiredArgsConstructor
public class NumberGenerator {
    private final SummingCompositeNumberProvider numberProvider;
    private final GeneratedNumberPersister generatedNumberPersister;

    @Retryable(maxAttempts = 6)
    @Transactional
    public GeneratedNumber next() {
        return nextInternal(numberProvider::next);
    }

    @Async
    @Transactional
    public void generateBatch(int numbers) {
        IntStream.range(0, numbers)
            .forEach(e -> {
                if (e > 3) {
                    throw new RuntimeException("This exception was meant to be thrown, sorry for that.");
                }
                nextInternal(() -> BigDecimal.valueOf(nanoTime()));
            });
    }

    private GeneratedNumber nextInternal(Supplier<BigDecimal> supplier) {
        log.info("Attempting to generate random number");
        final var startedAt = nanoTime();
        final var nextNumber = supplier.get();
        final var finishedAt = nanoTime();
        final var elapsedTime = finishedAt - startedAt;

        //this could be asynchronous
        final var generationTime = new GenerationTime(startedAt, finishedAt, elapsedTime);
        final var generatedNumber = new GeneratedNumber(nextNumber, generationTime);
        generatedNumberPersister.persist(generatedNumber);

        log.info("Successfully generated random number in '{}' nanoseconds", elapsedTime);
        return generatedNumber;
    }
}

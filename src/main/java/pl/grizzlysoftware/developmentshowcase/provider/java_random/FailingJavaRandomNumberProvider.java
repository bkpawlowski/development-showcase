package pl.grizzlysoftware.developmentshowcase.provider.java_random;

import pl.grizzlysoftware.developmentshowcase.domain.NumberProvider;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.concurrent.ThreadLocalRandom.current;

public class FailingJavaRandomNumberProvider implements NumberProvider {
    private final AtomicLong counter;

    public FailingJavaRandomNumberProvider() {
        counter = new AtomicLong(0);
    }

    @Override
    public BigDecimal next() {
        if (counter.getAndIncrement() % 2 == 0) {
            throw new RuntimeException("Well, that was supposed to happen, sorry for that.");
        }
        return BigDecimal.valueOf(current().nextDouble());
    }
}

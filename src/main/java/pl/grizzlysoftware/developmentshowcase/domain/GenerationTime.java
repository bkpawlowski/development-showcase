package pl.grizzlysoftware.developmentshowcase.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenerationTime {
    private long startedAt;
    private long finishedAt;
    private long elapsedTime;
}
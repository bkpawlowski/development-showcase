package pl.grizzlysoftware.developmentshowcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.grizzlysoftware.developmentshowcase.entity.AppNumber;

/**
 * Created by Bartosz Pawłowski on 10/08/2020.
 */
public interface AppNumberRepository extends JpaRepository<AppNumber, Long> {
}

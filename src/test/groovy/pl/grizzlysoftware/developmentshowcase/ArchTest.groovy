package pl.grizzlysoftware.developmentshowcase

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.library.Architectures
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Specification

import static java.util.stream.Collectors.joining

class ArchTest extends Specification {
    public static final Logger log = LoggerFactory.getLogger(ArchTest)

    public static final JavaClasses APPLICATION_CLASSES = new ClassFileImporter()
        .withImportOption(new ImportOption.DoNotIncludeTests())
        .importPackagesOf(ApplicationStarter)

    def "layered architecture test"() {
        given:
            def fixture = Architectures.layeredArchitecture()
                .layer("client").definedBy("..client..")
                .layer("domain").definedBy("..domain..")
                .layer("provider").definedBy("..provider..")
                .whereLayer("client").mayOnlyBeAccessedByLayers("client")
                .whereLayer("domain").mayOnlyBeAccessedByLayers("client", "provider")
                .whereLayer("provider").mayOnlyBeAccessedByLayers("provider")
        when:
            def result = fixture.evaluate(APPLICATION_CLASSES)
        then:
            log.error("Arch violations:\n- {}", result.getFailureReport()
                .getDetails().stream().collect(joining(",\n - ")))
            !result.hasViolation()
    }

}

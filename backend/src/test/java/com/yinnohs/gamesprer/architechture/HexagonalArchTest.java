package com.yinnohs.gamesprer.architechture;

import org.junit.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.yinnohs.gamesprer")
public class HexagonalArchTest {
    private final String BASE_PACKAGE = "com.yinnohs.gamesprer";
    private final String JAVA_PACKAGES = "java..";
    private final String DOMAIN_PACKAGES = "..domain..";
    private final String APPLICATION_PACKAGES =  "..application..";
    private final String LOMBOK = "lombok..";
    private final boolean ALLOW_EMPTY_RULES = true;
    private  JavaClasses importedClasses = new ClassFileImporter().importPackages(BASE_PACKAGE);

     @Test
    public void ensure_domain_classes_only_depends_on_java_std_library_and_lombok(){
        ArchRule domainRule = noClasses().that()
                .resideInAnyPackage(DOMAIN_PACKAGES)
                .should()
                .dependOnClassesThat()
                .resideOutsideOfPackages(DOMAIN_PACKAGES, JAVA_PACKAGES, LOMBOK);

        domainRule.allowEmptyShould(ALLOW_EMPTY_RULES).check(importedClasses);
    }

    @Test
    public void ensure_application_classes_only_depends_on_java_std_library_lombok_domain_and_user_domain(){
        ArchRule domainRule = noClasses().that()
                .resideInAnyPackage(APPLICATION_PACKAGES)
                .should()
                .dependOnClassesThat()
                .resideOutsideOfPackages(DOMAIN_PACKAGES, JAVA_PACKAGES, LOMBOK, APPLICATION_PACKAGES);

        domainRule.allowEmptyShould(ALLOW_EMPTY_RULES).check(importedClasses);
    }
}

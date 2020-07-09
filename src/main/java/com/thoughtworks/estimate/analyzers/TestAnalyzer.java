package com.thoughtworks.estimate.analyzers;

import com.thoughtworks.estimate.GitRepo;
import com.thoughtworks.estimate.dto.IReport;
import com.thoughtworks.estimate.dto.test.TestReport;
import com.thoughtworks.estimate.utils.CommandLineUtils;
import java.io.File;

public class TestAnalyzer implements IAnalyzer {

  @Override
  public IReport analysis(GitRepo repo) {
    runJacoco(repo.getFile());
    return TestReport.builder().build();
  }

  public void runJacoco(File path) {
    CommandLineUtils.runCommand()
//    if [[ -f "./mvnw" ]]; then
//        ./mvnw clean test
//    cp -R target/site/jacoco/ ../jacoco
//    elif [[ -f "./gradlew" ]]; then
//        ./gradlew clean test jacocoTestReport
//    cp -R build/reports/jacoco/test ../jacoco
//    elif [[ -f "pom.xml" ]]; then
//    mvn clean test
//    cp -R target/site/jacoco/ ../jacoco
//    elif [[ -f "build.gradle" ]]; then
//    gradle clean test jacocoTestReport
//    cp -R build/reports/jacoco/test ../jacoco
//        fi
  }
}

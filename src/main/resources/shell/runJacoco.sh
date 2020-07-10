#!/bin/bash

function main {
    if [[ -f "./mvnw" ]]; then
        ./mvnw clean test
        cp target/site/jacoco/jacoco.csv ./jacoco.csv
    elif [[ -f "./gradlew" ]]; then
        ./gradlew clean test jacocoTestReport
        cp build/reports/jacoco/test/jacocoTestReport.csv ./jacoco.csv
    elif [[ -f "pom.xml" ]]; then
        mvn clean test
        cp target/site/jacoco/jacoco.csv ./jacoco.csv
    elif [[ -f "build.gradle" ]]; then
        gradle clean test jacocoTestReport
        cp build/reports/jacoco/test/jacocoTestReport.csv ./jacoco.csv
    fi
}

main
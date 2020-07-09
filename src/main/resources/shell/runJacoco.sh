#!/bin/bash

function executeJacoco {
    if [[ -f "./mvnw" ]]; then
        ./mvnw clean test
        cp -R target/site/jacoco/ ./
    elif [[ -f "./gradlew" ]]; then
        ./gradlew clean test jacocoTestReport
        cp -R build/reports/jacoco/test ./
    elif [[ -f "pom.xml" ]]; then
        mvn clean test
        cp -R target/site/jacoco/ ./
    elif [[ -f "build.gradle" ]]; then
        gradle clean test jacocoTestReport
        cp -R build/reports/jacoco/test ./
    fi
}

function main {
    readCurrentWorkspacePath
    executeJacoco
}

main
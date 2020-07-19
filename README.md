# auto_estimate
## How to use

1. git clone https://github.com/Angboo/auto_estimate.git
2. use intellij idea to start auto_estimate
3. input all trainees' repo to "src/main/resources/trainees"
4. run main function in intellij idea

## Dependencies
Detected code must be java language
Detected code must config JaCoCo plugin
- build.gradle
```groovy
plugins {
    id 'jacoco'
}

jacocoTestReport {
    reports {
        csv.enabled true
    }
}

test {
    useJUnitPlatform()
}
```
pom.xml
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.jacoco</groupId>
            <artifactId>jacoco-maven-plugin</artifactId>
            <version>0.8.2</version>
            <executions>
                <execution>
                    <goals>
                        <goal>prepare-agent</goal>
                    </goals>
                </execution>
                <!-- attached to Maven test phase -->
                <execution>
                    <id>report</id>
                    <phase>test</phase>
                    <goals>
                        <goal>report</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```
## TODO

1. automatically select different configurations based on the quiz
2. run program on the terminal
3. completion test
4. business naming judgment algorithm improved
5. test coverage judgment algorithm improved
6. git commit judgment algorithm improved


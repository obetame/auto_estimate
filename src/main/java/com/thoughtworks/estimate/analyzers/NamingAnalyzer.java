package com.thoughtworks.estimate.analyzers;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.google.common.collect.Lists;
import com.google.common.io.MoreFiles;
import com.thoughtworks.estimate.GitRepo;
import com.thoughtworks.estimate.configuration.CommonConfiguration;
import com.thoughtworks.estimate.configuration.NamingConfiguration;
import com.thoughtworks.estimate.dto.IReport;
import com.thoughtworks.estimate.dto.naming.NamingReport;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NamingAnalyzer implements IAnalyzer {

  public static final String JAVA_EXTENSION = "java";

  @Override
  public IReport analysis(GitRepo repo) {
    final String path = Paths.get(repo.getPath(), CommonConfiguration.getProjectSourcePath())
        .toString();

    final List<String> variableNames = this.traverseJavaFiles(path, getAllVariableNamesConverter());
    final List<String> methodNames = this.traverseJavaFiles(path, getAllMethodNamesConverter());
    final List<String> typeNames = this.traverseJavaFiles(path, getAllTypeNamesConverter());

    return NamingReport.builder()
        .typeScore(getScoreByNaming(NamingConfiguration.getTypeNames(), typeNames))
        .methodScore(getScoreByNaming(NamingConfiguration.getMethodNames(), methodNames))
        .variableScore(getScoreByNaming(NamingConfiguration.getVariableNames(), variableNames))
        .build();
  }

  private Double getScoreByNaming(List<String> testCase, List<String> names) {
    return testCase.stream()
        .map(item -> names.stream().anyMatch(variable -> variable.contains(item)))
        .filter(Boolean::booleanValue)
        .count() * NamingConfiguration.getOneItemScore();
  }

  private Function<Stream<Path>, List<String>> getAllMethodNamesConverter() {
    return javaFilesStream -> javaFilesStream
        .flatMap(this::getAllTypesStream)
        .flatMap(this::getAllMembersStream)
        .flatMap(this::getAllMethodDeclarationStream)
        .map(MethodDeclaration::getNameAsString)
        .collect(Collectors.toList());
  }

  private Function<Stream<Path>, List<String>> getAllVariableNamesConverter() {
    return javaFilesStream -> javaFilesStream
        .flatMap(this::getAllTypesStream)
        .flatMap(this::getAllMembersStream)
        .flatMap(this::getAllVariableDeclaratorStream)
        .map(VariableDeclarator::getNameAsString)
        .collect(Collectors.toList());
  }

  private Function<Stream<Path>, List<String>> getAllTypeNamesConverter() {
    return javaFilesStream -> javaFilesStream
        .flatMap(this::getAllTypesStream)
        .map(TypeDeclaration::getNameAsString)
        .collect(Collectors.toList());
  }

  private <T> List<T> traverseJavaFiles(String path, Function<Stream<Path>, List<T>> handler) {
    try (Stream<Path> javaFilesStream = getJavaFilesStreamByPath(path)) {
      return handler.apply(javaFilesStream);
    } catch (IOException e) {
      log.info(e.getMessage());
    }
    return Lists.newArrayList();
  }

  private Stream<MethodDeclaration> getAllMethodDeclarationStream(BodyDeclaration<?> item) {
    return item.findAll(MethodDeclaration.class).stream();
  }

  private Stream<Path> getJavaFilesStreamByPath(String path) throws IOException {
    return Files.walk(Paths.get(path))
        .filter(Files::isRegularFile)
        .filter(this::isJavaFile);
  }

  private Stream<VariableDeclarator> getAllVariableDeclaratorStream(BodyDeclaration<?> item) {
    return item.findAll(VariableDeclarator.class).stream();
  }

  private Stream<BodyDeclaration<?>> getAllMembersStream(TypeDeclaration<?> typeDeclaration) {
    return typeDeclaration.getMembers().stream();
  }

  private boolean isJavaFile(Path item) {
    return MoreFiles.getFileExtension(item).equals(JAVA_EXTENSION);
  }

  @SneakyThrows
  private Stream<TypeDeclaration<?>> getAllTypesStream(Path pathItem) {
    JavaParser javaParser = new JavaParser();
    return javaParser.parse(pathItem).getResult()
        .orElseGet(CompilationUnit::new)
        .getTypes()
        .stream();
  }

}

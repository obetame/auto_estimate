package com.thoughtworks.estimate;

import com.thoughtworks.estimate.analyzers.IAnalyzer;
import com.thoughtworks.estimate.dto.EstimateResult;
import com.thoughtworks.estimate.dto.Trainee;
import com.thoughtworks.estimate.utils.InterfaceUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Estimater {

  private final List<IAnalyzer> analyzers;

  public Estimater() {
    this.analyzers = InterfaceUtils.getAllImplementationInstance(IAnalyzer.class);
  }

  public EstimateResult estimateByTrainee(Trainee trainee) {
    final GitRepo repo = new GitRepo(trainee.getRepo());
    final double score = analyzers.stream()
        .mapToDouble(iAnalyzer -> iAnalyzer.analysis(repo).getScore())
        .sum();
    repo.delete();
    return EstimateResult.builder().name(trainee.getName()).score(score).build();
  }

  public List<EstimateResult> batchEstimate(Trainee[] trainees) {
    return Arrays.stream(trainees).map(this::estimateByTrainee).collect(Collectors.toList());
  }
}

package com.thoughtworks.estimate;

import com.thoughtworks.estimate.analyzers.IAnalyzer;
import com.thoughtworks.estimate.dto.Trainee;
import com.thoughtworks.estimate.dto.summary.IndividualReport;
import com.thoughtworks.estimate.dto.summary.ModuleReport;
import com.thoughtworks.estimate.utils.InterfaceUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Estimater {

  private final List<IAnalyzer> analyzers;

  public Estimater() {
    this.analyzers = InterfaceUtils.getAllImplementationInstance(IAnalyzer.class);
  }

  public IndividualReport estimateByTrainee(Trainee trainee) {
    try {
      final GitRepo repo = new GitRepo(trainee.getRepo());
      final List<ModuleReport> moduleReports = analyzers.parallelStream()
          .map(iAnalyzer -> iAnalyzer.analysis(repo))
          .map(iReport -> ModuleReport.builder()
              .violationItems(iReport.getViolationItems())
              .module(iReport.getModuleName())
              .score(iReport.getScore())
              .build()
          ).collect(Collectors.toList());

      repo.delete();
      return IndividualReport.builder()
          .name(trainee.getName())
          .moduleReports(moduleReports)
          .score(moduleReports.stream().mapToDouble(ModuleReport::getScore).sum())
          .build();
    } catch (Throwable throwable) {
      return IndividualReport.builder()
          .name(trainee.getName())
          .hasException(true)
          .build();
    }
  }

  public List<IndividualReport> batchEstimate(Trainee[] trainees) {
    return Arrays.stream(trainees).map(this::estimateByTrainee).collect(Collectors.toList());
  }
}

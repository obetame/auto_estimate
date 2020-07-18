package com.thoughtworks.estimate.analyzers;

import com.thoughtworks.estimate.GitRepo;
import com.thoughtworks.estimate.configuration.CommonConfiguration;
import com.thoughtworks.estimate.dto.test.TestCoverageReport;
import com.thoughtworks.estimate.dto.test.TestCoverageReportItem;
import com.thoughtworks.estimate.utils.CommandLineUtils;
import com.thoughtworks.estimate.utils.CsvUtils;
import com.thoughtworks.estimate.utils.FileUtils;
import java.nio.file.Paths;

public class TestCoverageAnalyzer implements IAnalyzer {

  @Override
  public TestCoverageReport analysis(GitRepo repo) {
    final String shellPath = Paths
        .get(CommonConfiguration.getSrcMainResourcesPath(), "shell/runJacoco.sh")
        .toAbsolutePath()
        .toString();
    CommandLineUtils.runCommand(String.format("bash %s", shellPath), repo.getFile());

    final String csv = FileUtils.readFile(Paths.get(repo.getPath(), "jacoco.csv").toString());

    return TestCoverageReport.builder()
        .items(CsvUtils.read(csv, TestCoverageReportItem.class))
        .build();
  }

}

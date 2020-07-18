package com.thoughtworks.estimate.analyzers;

import com.thoughtworks.estimate.GitRepo;
import com.thoughtworks.estimate.configuration.CommonConfiguration;
import com.thoughtworks.estimate.configuration.PMDConfiguration;
import com.thoughtworks.estimate.dto.pmd.PMDReport;
import com.thoughtworks.estimate.utils.FileUtils;
import com.thoughtworks.estimate.utils.JsonUtils;
import java.nio.file.Paths;
import net.sourceforge.pmd.PMD;

public class PMDAnalyzer implements IAnalyzer {

  @Override
  public PMDReport analysis(GitRepo repo) {
    PMD.doPMD(getConfig(repo.getPath()));
    return this.getReport(repo.getPath());
  }

  public net.sourceforge.pmd.PMDConfiguration getConfig(String path) {
    net.sourceforge.pmd.PMDConfiguration configuration = new net.sourceforge.pmd.PMDConfiguration();
    configuration
        .setInputPaths(Paths.get(path, CommonConfiguration.getProjectSourcePath()).toString());
    configuration.setRuleSets(PMDConfiguration.getPmdRulesetsXmlPath());
    configuration.setReportFormat("json");
    configuration.setReportFile(getReportPath(path));
    return configuration;
  }

  private String getReportPath(String path) {
    return Paths.get(path, PMDConfiguration.getJsonReportFileName()).toString();
  }

  private PMDReport getReport(String path) {

    return JsonUtils.read(FileUtils.readFile(this.getReportPath(path)), PMDReport.class);
  }

}

package com.thoughtworks.estimate.analyzers;

import com.thoughtworks.estimate.GitRepo;
import com.thoughtworks.estimate.config.PMDConfig;
import com.thoughtworks.estimate.dto.pmd.PMDReport;
import com.thoughtworks.estimate.utils.FileUtils;
import com.thoughtworks.estimate.utils.JsonUtils;
import java.nio.file.Paths;
import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.PMDConfiguration;

public class PMDAnalyzer implements IAnalyzer {

  @Override
  public PMDReport analysis(GitRepo repo) {
    PMD.doPMD(getConfig(repo.getPath()));
    return this.getReport(repo.getPath());
  }

  public PMDConfiguration getConfig(String path) {
    PMDConfiguration configuration = new PMDConfiguration();
    configuration.setInputPaths(Paths.get(path,"/src").toString());
    configuration.setRuleSets("src/main/resources/pmd-rulesets.xml");
    configuration.setReportFormat("json");
    configuration.setReportFile(getReportPath(path));
    return configuration;
  }

  private String getReportPath(String path) {
    return Paths.get(path, PMDConfig.getJsonReportFileName()).toString();
  }

  private PMDReport getReport(String path) {

    return JsonUtils.read(FileUtils.readJsonFile(this.getReportPath(path)), PMDReport.class);
  }

}

package com.thoughtworks.estimate.analyzers;

import com.thoughtworks.estimate.GitRepo;
import com.thoughtworks.estimate.dto.IReport;

public interface IAnalyzer {

  IReport analysis(GitRepo repo);

}

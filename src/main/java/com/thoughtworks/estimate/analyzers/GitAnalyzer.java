package com.thoughtworks.estimate.analyzers;

import com.thoughtworks.estimate.GitRepo;
import com.thoughtworks.estimate.dto.git.GitReport;
import com.thoughtworks.estimate.dto.git.GitReportItem;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class GitAnalyzer implements IAnalyzer {

  @Override
  @lombok.SneakyThrows
  public GitReport analysis(GitRepo repo) {
    final List<GitReportItem> gitReportItems = StreamSupport
        .stream(repo.getGit().log().call().spliterator(), false)
        .map(GitReportItem::from)
        .collect(Collectors.toList());
    return new GitReport(gitReportItems);
  }

}

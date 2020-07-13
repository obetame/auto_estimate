package com.thoughtworks.estimate.analyzers;

import com.thoughtworks.estimate.GitRepo;
import com.thoughtworks.estimate.configuration.GitConfiguration;
import com.thoughtworks.estimate.dto.git.GitReport;
import com.thoughtworks.estimate.dto.git.GitReportItem;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.eclipse.jgit.revwalk.RevCommit;

public class GitAnalyzer implements IAnalyzer {

  @Override
  @lombok.SneakyThrows
  public GitReport analysis(GitRepo repo) {
    final List<RevCommit> commitList = StreamSupport
        .stream(repo.getGit().log().call().spliterator(), false)
        .collect(Collectors.toList());

    final List<GitReportItem> gitReportItems = commitList.stream()
        .filter(revCommit -> revCommit.getFullMessage().length() < GitConfiguration
            .getCommitMessageMinimumLength())
        .map(GitReportItem::from)
        .collect(Collectors.toList());

    return GitReport.builder()
        .items(gitReportItems)
        .commitQuantity(commitList.size())
        .build();
  }

}

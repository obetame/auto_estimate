package com.thoughtworks.estimate.dto.naming;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NamingConfig {
 private List<String> typeNames;
 private List<String> methodNames;
 private List<String> variableNames;
}

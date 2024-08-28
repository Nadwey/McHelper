package pl.nadwey.problem;

import lombok.Getter;
import pl.nadwey.problem.problems.AmbiguousPluginNameProblem;

import java.util.ArrayList;
import java.util.List;

public class ProblemManager {
    @Getter
    private final List<Problem> problems = new ArrayList<>();

    public ProblemManager() {
        problems.add(new AmbiguousPluginNameProblem());
    }
}

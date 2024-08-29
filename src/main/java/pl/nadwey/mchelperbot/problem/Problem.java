package pl.nadwey.mchelperbot.problem;

import lombok.Getter;

public abstract class Problem {
    @Getter
    private final String name;

    protected Problem(String name) {
        this.name = name;
    }

    public abstract Result check(String log);

    public interface Result {
        String getMessage();
    }
}

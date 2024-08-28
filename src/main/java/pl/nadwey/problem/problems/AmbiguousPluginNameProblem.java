package pl.nadwey.problem.problems;

import com.github.mustachejava.Mustache;
import lombok.AllArgsConstructor;
import pl.nadwey.McHelper;
import pl.nadwey.problem.Problem;

import java.io.StringWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AmbiguousPluginNameProblem extends Problem {

    public static final Pattern pattern = Pattern.compile("Ambiguous plugin name `([a-zA-Z0-9_\\-]+)' for files `([\\p{L}\\p{N}_\\-/. ()]+)' and `([\\p{L}\\p{N}_\\-/. ()]+)'");

    public AmbiguousPluginNameProblem() {
        super("Te same pluginy");
    }

    @Override
    public Result check(String log) {
        Matcher matcher = pattern.matcher(log);

        Map<String, Set<String>> plugins = new HashMap<>();

        while (matcher.find()) {
            String name = matcher.group(1);

            if (!plugins.containsKey(name)) {
                plugins.put(name, new HashSet<>());
            }

            plugins.get(name).add(matcher.group(2));
            plugins.get(name).add(matcher.group(3));
        }

        return new Result(plugins);
    }

    @AllArgsConstructor
    public static class Result implements Problem.Result {
        public final static String TEMPLATE_NAME = "ambiguous-plugin-name";

        private Map<String, Set<String>> plugins = new HashMap<>();

        @Override
        public String getMessage() {
            StringWriter writer = new StringWriter();
            Mustache m = McHelper.getInstance().getTemplateManager().getTemplate(TEMPLATE_NAME);

            Map<String, Object> context = new HashMap<>();

            context.put("plugins", plugins.entrySet().stream().map(
                    entry -> Map.of(
                            "name", entry.getKey(),
                            "paths", entry.getValue()
                    )
            ).toList());

            m.execute(writer, context);
            return writer.toString();
        }
    }
}

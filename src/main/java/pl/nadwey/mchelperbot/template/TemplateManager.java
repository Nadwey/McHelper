package pl.nadwey.mchelperbot.template;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Manages Mustache templates<br />
 * <br />
 * TODO: Copy all the templates on the start for easier editing
 */
public class TemplateManager {
    private final MustacheFactory mf = new DefaultMustacheFactory();

    @SneakyThrows
    public Mustache getTemplate(String templateName) {
        File localFile = Paths.get("templates", templateName + ".mustache").toFile();

        if (!localFile.exists())
            copyResource("/templates/" + templateName + ".mustache", localFile);

        // Mustache.java should cache templates, so we don't need to worry about doing it ourselves
        return mf.compile(new FileReader(localFile), templateName);
    }

    private void copyResource(String resource, File dest) throws IOException {
        InputStream src = this.getClass().getResourceAsStream(resource);

        if (src == null)
            throw new FileNotFoundException(resource);

        dest.getParentFile().mkdirs();
        Files.copy(src, dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}

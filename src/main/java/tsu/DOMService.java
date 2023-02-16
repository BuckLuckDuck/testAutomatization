package tsu;

import com.github.kklisura.cdt.launch.ChromeLauncher;
import com.github.kklisura.cdt.protocol.commands.Runtime;
import com.github.kklisura.cdt.protocol.types.runtime.Evaluate;
import com.google.gson.*;

import com.github.kklisura.cdt.protocol.commands.Page;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.types.ChromeTab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DOMService implements AutoCloseable {
    final ChromeLauncher chromeLauncher;
    final ChromeService chromeService;
    final ChromeTab tab;
    final ChromeDevToolsService devToolsService;
    final Page page;
    final Runtime runtime;
    final Gson gson = new Gson();

    public DOMService() {
        this.chromeLauncher = new ChromeLauncher();
        this.chromeService = chromeLauncher.launch(false);
        this.tab = this.chromeService.createTab();
        this.devToolsService = this.chromeService.createDevToolsService(tab);
        this.page = devToolsService.getPage();
        this.runtime = this.devToolsService.getRuntime();
    }

    public Elements getPageElements() {
        final String script = concatLines(readResource("/page.js"));
        final Evaluate evaluation = runtime.evaluate(script);
        return gson.fromJson(evaluation.getResult().getValue().toString(), Elements.class);
    }

    static String concatLines(String[] lines) {
        final StringBuilder b = new StringBuilder();
        for (String l : lines)
            b.append(l).append(" ");
        return new String(b);
    }

    String[] readResource(String resName) {
        try {
            final List<String> res = new ArrayList<>();
            try (final BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.
                    requireNonNull(getClass().getResourceAsStream(resName)), StandardCharsets.UTF_8))) {
                String line = reader.readLine();
                while (line != null) {
                    res.add(line);
                    line = reader.readLine();
                }
            }
            return res.toArray(new String[res.size()]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateTo(String url) throws InterruptedException {
        this.page.navigate(url);
        Thread.sleep(3000);
    }

    @Override
    public void close() throws Exception {
        this.devToolsService.close();
        this.chromeService.closeTab(tab);
    }
}

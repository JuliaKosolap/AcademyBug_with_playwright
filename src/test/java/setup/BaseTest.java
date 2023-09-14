package setup;

import com.microsoft.playwright.*;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Paths;
import java.util.List;

public class BaseTest {

    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    BrowserContext context2;
    public Page page;
    private String baseUrl = "https://academybugs.com/find-bugs/";


    @BeforeAll
    public static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                    .setArgs(List.of("--start-maximized")));

        }

    @BeforeEach
    public void createContextAndPage() {
         context = browser.newContext(new Browser.NewContextOptions().
                 setViewportSize(null)
                .setRecordVideoDir(Paths.get("videos/"))
                        .setRecordVideoSize(640, 480));

         context.tracing().start(new Tracing.StartOptions()
                 .setScreenshots(true)
                 .setSnapshots(true)
                 .setSources(true));

        page = context.newPage();

        page.navigate(baseUrl);
    }

    @AfterEach
    public void tearDown() {
        //Save the trace archive after test is completed
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("traces/"+ getClass()+ "_trace.zip")));
        context.close();
        browser.close();

    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }
}

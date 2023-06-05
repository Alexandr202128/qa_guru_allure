import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class GitIssueTests {

    private static final String REPOSITORY = "eroshenkoam/allure-example";

    @BeforeEach
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.pageLoadStrategy = "none";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    public void checkIssue() {
        open("https://github.com/");
        $("[name=q]").setValue("eroshenkoam/allure-example");
        $("[name=q]").pressEnter();

        $(".menu-item [data-search-type=Issues]").click();
        $(Selectors.withText("71")).should(Condition.exist);
    }

    @Test
    public void checkIssueStepLambda() {
        step("Открываем главную страницу",
                () -> {open("https://github.com/");});

        step("Вводим в поисковую строку " + REPOSITORY,
                () -> {$("[name=q]").setValue(REPOSITORY);});
        step("Нажимаем enter",
                () -> {$("[name=q]").pressEnter();});

        step("Кликаем по полю Issues",
                () -> {$(".menu-item [data-search-type=Issues]").click();});
        step("Проверяем наличие Issues",
                () -> {$(Selectors.withText("71")).should(Condition.exist);});
    }



    @Test
    @DisplayName("Проверка Issues")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://github.com/allure-framework/allure-gradle")
    @Owner("Alexandr Solovyov")
    @Feature("Issue в репозитории")
    @Story("Создание Issues")
    public void checkIssueStep() {
        new MainPage()
                .openGit()
                .setText(REPOSITORY)
                .enter()
                .clickIssues()
                .checkIssues();
    }

}

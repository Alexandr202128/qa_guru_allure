import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    @Step("Открываем главную страницу")
    public MainPage openGit() {
        open("https://github.com/");
        return this;
    }

    @Step("Вводим в поисковую строку {text}")
    public MainPage setText(String text) {
        $("[name=q]").setValue("eroshenkoam/allure-example");
        return this;
    }

    @Step("Нажимаем enter")
    public MainPage enter() {
        $("[name=q]").pressEnter();
        return this;
    }

    @Step("Кликаем по полю Issues")
    public MainPage clickIssues() {
        $(".menu-item [data-search-type=Issues]").click();
        return this;
    }

    @Step("Проверяем наличие Issues")
    public MainPage checkIssues() {
        $(Selectors.withText("71")).should(Condition.exist);
        return this;
    }
}

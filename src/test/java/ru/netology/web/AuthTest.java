package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.cssSelector;

public class AuthTest {
    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequestIfUserIsActive() {
        Registration user = DataGenerator.generateNewActiveUser();
        SelenideElement form = $("[action]");
        form.$(cssSelector("[name=login]")).sendKeys(user.getLogin());
        form.$(cssSelector("[name=password]")).sendKeys(user.getPassword());
        form.$(cssSelector("[type=button]")).click();
        $(byText("Личный кабинет")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldNotSubmitRequestIfStatusIsBlocked() {
        Registration user = DataGenerator.generateNewBlockedUser();
        SelenideElement form = $("[action]");
        form.$(cssSelector("[name=login]")).sendKeys(user.getLogin());
        form.$(cssSelector("[name=password]")).sendKeys(user.getPassword());
        form.$(cssSelector("[type=button]")).click();
        $(byText("Ошибка")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldNotSubmitRequestIfLoginInvalid() {
        Registration user = DataGenerator.generateNewUserWithInvalidLogin();
        SelenideElement form = $("[action]");
        form.$(cssSelector("[name=login]")).sendKeys(user.getLogin());
        form.$(cssSelector("[name=password]")).sendKeys(user.getPassword());
        form.$(cssSelector("[type=button]")).click();
        $(byText("Ошибка")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldNotSubmitRequestIfPasswordInvalid() {
        Registration user = DataGenerator.generateNewUserWithInvalidPassword();
        SelenideElement form = $("[action]");
        form.$(cssSelector("[name=login]")).sendKeys(user.getLogin());
        form.$(cssSelector("[name=password]")).sendKeys(user.getPassword());
        form.$(cssSelector("[type=button]")).click();
        $(byText("Ошибка")).shouldBe(visible, Duration.ofSeconds(15));
    }
}

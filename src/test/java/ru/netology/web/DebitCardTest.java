package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DebitCardTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequest() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Василий Иванов-Петров");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__content").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSubmitRequestNoData() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$(".button__content").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSubmitRequestBadName() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Vasiliy");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__content").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSubmitRequestNoName() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__content").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSubmitRequestBadTel() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Василий Иванов-Петров");
        form.$("[data-test-id=phone] input").setValue("879270000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__content").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldSubmitRequestNoTel() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Василий Иванов-Петров");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__content").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSubmitRequestNoCheckbox() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Василий Иванов-Петров");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$(".button__content").click();
        $(".input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}

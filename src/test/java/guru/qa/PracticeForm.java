package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeForm {

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void registrationFormTest() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        $("#firstName").setValue("Ivan");
        $("#lastName").setValue("Ivanov");
        $("#userEmail").setValue("ivanov@gmail.com");
        $("[for=gender-radio-1]").click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption(11);
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__day--008").click();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("[for=hobbies-checkbox-3]").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/img.png"));
        $("#currentAddress").setValue("Some address");
        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Noida")).click();
        $("#submit").click();

        //Check table
        $(".table-responsive")
                .shouldHave(text("Ivan Ivanov"),
                        text("ivanov@gmail.com"),
                        text("Male"),
                        text("1234567890"),
                        text("08 December,1995"),
                        text("Maths"),
                        text("Music"),
                        text("img.png"),
                        text("Some address"),
                        text("NCR Noida"));
    }
}

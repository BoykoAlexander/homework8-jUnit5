package yandex.boyko;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

public class ParameterizedWebTest {

  @ValueSource(strings = {"Ипотека", "Кредит"})
  @ParameterizedTest(name = "Тестирование общего алгоритма с тестовыми данными: {0} ")
  void searchByTwoParameters(String TestData) {
    Selenide.open("https://www.sberbank.ru/ru/person");
    Selenide.$(".kitt-header").$(".kitt-header-search").click();
    Selenide.$(".kitt-header-search__search").setValue(TestData).pressEnter();
    Selenide.$(".l-page__col").shouldHave(Condition.text(TestData));
  }

  @CsvSource(value = {
          "Ипотека, Льготная ипотека с господдержкой — СберБанк",
          "Кредит, Ипотека на вторичное жилье — СберБанк"
  })

  @ParameterizedTest(name = "Тестирование общего алгоритма с тестовыми данными: {0} ")
  void searchByCsvSource(String TestData, String expectedResult) {
    Selenide.open("https://www.sberbank.ru/ru/person");
    Selenide.$(".kitt-header").$(".kitt-header-search").click();
    Selenide.$(".kitt-header-search__search").setValue(TestData).pressEnter();
    Selenide.$(".l-page__col").shouldHave(Condition.text(expectedResult));
  }

  static Stream<Arguments> commandSearchTestData() {
    return Stream.of(
            Arguments.of("Ипотека", "Льготная ипотека с господдержкой — СберБанк"),
            Arguments.of("Кредит", "Ипотека на вторичное жилье — СберБанк")
    );
  }

  @MethodSource("commandSearchTestData")
  @ParameterizedTest(name = "Тестирование общего алгоритма с тестовыми данными: {0} ")
  void commanSearch(String TestData, String expectedResult) {
    Selenide.open("https://www.sberbank.ru/ru/person");
    Selenide.$(".kitt-header").$(".kitt-header-search").click();
    Selenide.$(".kitt-header-search__search").setValue(TestData).pressEnter();
    Selenide.$(".l-page__col").shouldHave(Condition.text(expectedResult));
  }

}

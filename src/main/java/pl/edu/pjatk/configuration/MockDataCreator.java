package pl.edu.pjatk.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.pjatk.domain.tasks.Task;
import pl.edu.pjatk.domain.tasks.TaskRepository;

import java.time.LocalDate;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@ConditionalOnExpression("{'create', 'create-drop', 'create-only', 'drop', 'truncate'}.contains('${spring.jpa.hibernate.ddl-auto}')")
class MockDataCreator {

    private final TaskRepository taskRepository;

    @Bean
    CommandLineRunner createMockData() {
        return args -> {
            taskRepository.saveAll(List.of(
                            Task.builder()
                                    .name("Zrobić zakupy spożywcze")
                                    .description("Kupić mleko, jajka, chleb, pomidory i kawę ziarnistą.")
                                    .done(false)
                                    .build(),
                            Task.builder()
                                    .name("Nauczyć się Vaadina")
                                    .description("Przerobić oficjalny tutorial z dokumentacji Vaadin Flow.")
                                    .done(true)
                                    .build(),
                            Task.builder()
                                    .name("Skonfigurować bazę H2")
                                    .description("Przenieść plik bazy danych poza katalog resources i zaktualizować application.properties.")
                                    .done(true)
                                    .build(),
                            Task.builder()
                                    .name("Zapłacić rachunki")
                                    .description("Opłacić prąd, internet i czynsz za bieżący miesiąc.")
                                    .done(false)
                                    .build(),
                            Task.builder()
                                    .name("Zrobić przegląd samochodu")
                                    .description("Umówić się do mechanika na wymianę oleju i filtrów.")
                                    .dueDate(LocalDate.now().plusDays(10))
                                    .done(false)
                                    .build(),
                            Task.builder()
                                    .name("Napisać testy jednostkowe")
                                    .description("Pokryć testami w JUnit klasę TaskServiceImpl.")
                                    .dueDate(LocalDate.now().plusDays(1))
                                    .done(false)
                                    .build(),
                            Task.builder()
                                    .name("Posprzątać mieszkanie")
                                    .description("Odkurzyć, umyć podłogi i zetrzeć kurze.")
                                    .dueDate(LocalDate.now())
                                    .done(false)
                                    .build(),
                            Task.builder()
                                    .name("Umówić wizytę u dentysty")
                                    .description("Zadzwonić do kliniki i umówić się na przegląd.")
                                    .dueDate(LocalDate.now().minusDays(2))
                                    .done(true)
                                    .build(),
                            Task.builder()
                                    .name("Przygotować raport")
                                    .description("Zebrać dane z ostatniego miesiąca i wysłać podsumowanie do szefa.")
                                    .dueDate(LocalDate.now().plusDays(3))
                                    .done(false)
                                    .build(),
                            Task.builder()
                                    .name("Zrobić trening")
                                    .description("Bieganie 5km w parku.")
                                    .dueDate(LocalDate.now())
                                    .done(true)
                                    .build(),
                            Task.builder()
                                    .name("Odpisać na zaległe maile")
                                    .description("Wyczyścić skrzynkę odbiorczą do zera (Inbox Zero).")
                                    .dueDate(LocalDate.now().minusDays(3))
                                    .done(false)
                                    .build(),
                            Task.builder()
                                    .name("Zaktualizować CV")
                                    .description("Dodać nowy projekt z Vaadinem do portfolio na GitHubie.")
                                    .dueDate(LocalDate.now().plusDays(7))
                                    .done(false)
                                    .build(),
                            Task.builder()
                                    .name("Przeczytać książkę")
                                    .description("Skończyć czytać 'Czysty Kod' Roberta C. Martina.")
                                    .dueDate(LocalDate.now().plusDays(14))
                                    .done(false)
                                    .build(),
                            Task.builder()
                                    .name("Kupić prezent")
                                    .description("Zamówić prezent urodzinowy dla brata na Allegro.")
                                    .dueDate(LocalDate.now().plusDays(4))
                                    .done(false)
                                    .build(),
                            Task.builder()
                                    .name("Podlać kwiaty")
                                    .description("Szczególnie pamiętać o fikusie w salonie.")
                                    .dueDate(LocalDate.now())
                                    .done(true)
                                    .build(),
                            Task.builder()
                                    .name("Zaimplementować Security")
                                    .description("Dodać Spring Security z prostym logowaniem in-memory do aplikacji.")
                                    .dueDate(LocalDate.now().plusDays(8))
                                    .done(false)
                                    .build(),
                            Task.builder()
                                    .name("Zaplanować urlop")
                                    .description("Przejrzeć oferty lotów i hoteli na wakacje w sierpniu.")
                                    .dueDate(LocalDate.now().plusDays(20))
                                    .done(false)
                                    .build(),
                            Task.builder()
                                    .name("Wyprowadzić psa")
                                    .description("Długi spacer po lesie.")
                                    .dueDate(LocalDate.now())
                                    .done(true)
                                    .build(),
                            Task.builder()
                                    .name("Zrobić backup danych")
                                    .description("Skopiować najważniejsze projekty i dokumenty na dysk zewnętrzny.")
                                    .dueDate(LocalDate.now().minusDays(5))
                                    .done(true)
                                    .build(),
                            Task.builder()
                                    .name("Przygotować obiad")
                                    .description("Zrobić spaghetti bolognese według przepisu z YouTube.")
                                    .dueDate(LocalDate.now())
                                    .done(false)
                                    .build(),
                    Task.builder()
                            .name("Zrobić zakupy spożywcze")
                            .description("Kupić mleko, jajka, chleb, pomidory i kawę ziarnistą.")
                            .dueDate(LocalDate.now())
                            .done(false)
                            .build(),
                    Task.builder()
                            .name("Nauczyć się Vaadina")
                            .description("Przerobić oficjalny tutorial z dokumentacji Vaadin Flow.")
                            .dueDate(LocalDate.now().plusDays(2))
                            .done(true)
                            .build(),
                    Task.builder()
                            .name("Skonfigurować bazę H2")
                            .description("Przenieść plik bazy danych poza katalog resources i zaktualizować application.properties.")
                            .dueDate(LocalDate.now().minusDays(1))
                            .done(true)
                            .build(),
                    Task.builder()
                            .name("Zapłacić rachunki")
                            .description("Opłacić prąd, internet i czynsz za bieżący miesiąc.")
                            .dueDate(LocalDate.now().plusDays(5))
                            .done(false)
                            .build(),
                    Task.builder()
                            .name("Zrobić przegląd samochodu")
                            .description("Umówić się do mechanika na wymianę oleju i filtrów.")
                            .dueDate(LocalDate.now().plusDays(10))
                            .done(false)
                            .build(),
                    Task.builder()
                            .name("Napisać testy jednostkowe")
                            .description("Pokryć testami w JUnit klasę TaskServiceImpl.")
                            .dueDate(LocalDate.now().plusDays(1))
                            .done(false)
                            .build(),
                    Task.builder()
                            .name("Posprzątać mieszkanie")
                            .description("Odkurzyć, umyć podłogi i zetrzeć kurze.")
                            .dueDate(LocalDate.now())
                            .done(false)
                            .build(),
                    Task.builder()
                            .name("Umówić wizytę u dentysty")
                            .description("Zadzwonić do kliniki i umówić się na przegląd.")
                            .dueDate(LocalDate.now().minusDays(2))
                            .done(true)
                            .build(),
                    Task.builder()
                            .name("Przygotować raport")
                            .description("Zebrać dane z ostatniego miesiąca i wysłać podsumowanie do szefa.")
                            .dueDate(LocalDate.now().plusDays(3))
                            .done(false)
                            .build(),
                    Task.builder()
                            .name("Zrobić trening")
                            .description("Bieganie 5km w parku.")
                            .dueDate(LocalDate.now())
                            .done(true)
                            .build(),
                    Task.builder()
                            .name("Odpisać na zaległe maile")
                            .description("Wyczyścić skrzynkę odbiorczą do zera (Inbox Zero).")
                            .dueDate(LocalDate.now().minusDays(3))
                            .done(false)
                            .build(),
                    Task.builder()
                            .name("Zaktualizować CV")
                            .description("Dodać nowy projekt z Vaadinem do portfolio na GitHubie.")
                            .dueDate(LocalDate.now().plusDays(7))
                            .done(false)
                            .build(),
                    Task.builder()
                            .name("Przeczytać książkę")
                            .description("Skończyć czytać 'Czysty Kod' Roberta C. Martina.")
                            .dueDate(LocalDate.now().plusDays(14))
                            .done(false)
                            .build(),
                    Task.builder()
                            .name("Kupić prezent")
                            .description("Zamówić prezent urodzinowy dla brata na Allegro.")
                            .dueDate(LocalDate.now().plusDays(4))
                            .done(false)
                            .build(),
                    Task.builder()
                            .name("Podlać kwiaty")
                            .description("Szczególnie pamiętać o fikusie w salonie.")
                            .dueDate(LocalDate.now())
                            .done(true)
                            .build(),
                    Task.builder()
                            .name("Zaimplementować Security")
                            .description("Dodać Spring Security z prostym logowaniem in-memory do aplikacji.")
                            .dueDate(LocalDate.now().plusDays(8))
                            .done(false)
                            .build(),
                    Task.builder()
                            .name("Zaplanować urlop")
                            .description("Przejrzeć oferty lotów i hoteli na wakacje w sierpniu.")
                            .dueDate(LocalDate.now().plusDays(20))
                            .done(false)
                            .build(),
                    Task.builder()
                            .name("Wyprowadzić psa")
                            .description("Długi spacer po lesie.")
                            .dueDate(LocalDate.now())
                            .done(true)
                            .build(),
                    Task.builder()
                            .name("Zrobić backup danych")
                            .description("Skopiować najważniejsze projekty i dokumenty na dysk zewnętrzny.")
                            .dueDate(LocalDate.now().minusDays(5))
                            .done(true)
                            .build(),
                    Task.builder()
                            .name("Przygotować obiad")
                            .description("Zrobić spaghetti bolognese według przepisu z YouTube.")
                            .dueDate(LocalDate.now())
                            .done(false)
                            .build()
                    )
            );
        };
    }
}

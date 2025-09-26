package com.example.harjoitustyo.view;

import com.example.harjoitustyo.model.Hobby;
import com.example.harjoitustyo.model.Person;
import com.example.harjoitustyo.repository.PersonRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.html.H2;
import org.springframework.security.core.context.SecurityContextHolder;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

import java.util.stream.Collectors;

@Route(value = "hobbies", layout = MainLayout.class)
@PageTitle("Harrastukset")
@RolesAllowed("ADMIN")
public class HobbiesView extends VerticalLayout implements BeforeEnterObserver {

    @Autowired
    public HobbiesView(PersonRepository personRepository) {
        Grid<Person> hobbyGrid = new Grid<>(Person.class, false);

        hobbyGrid.addColumn(Person::getFirstName).setHeader("Etunimi");
        hobbyGrid.addColumn(Person::getLastName).setHeader("Sukunimi");
        hobbyGrid.addColumn(person -> person.getHobbies() != null
                ? person.getHobbies().stream()
                .map(Hobby::getName)
                .collect(Collectors.joining(", "))
                : ""
        ).setHeader("Harrastukset");

        hobbyGrid.setItems(
                personRepository.findAll().stream()
                        .filter(p -> p.getHobbies() != null && !p.getHobbies().isEmpty())
                        .toList()
        );

        H2 title = new H2("Henkilöiden harrastukset");
        add(title, hobbyGrid);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            event.forwardTo(AccessDeniedView.class); // 🚀 ohjaa accessDenied-sivulle
        }
    }
}
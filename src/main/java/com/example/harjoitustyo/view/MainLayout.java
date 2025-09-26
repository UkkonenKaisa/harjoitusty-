package com.example.harjoitustyo.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout() {
        // Otsikko
        H1 title = new H1("Henkilötietosovellus");
        title.getStyle()
                .set("margin", "0")
                .set("text-align", "center");

        // Navigointi – lisätään kaikki linkit näkyviin
        RouterLink personsLink = new RouterLink("Henkilöt", PersonsView.class);
        RouterLink hobbiesLink = new RouterLink("Harrastukset", HobbiesView.class);
        RouterLink aboutLink = new RouterLink("Tietoa", AboutView.class);

        HorizontalLayout navBar = new HorizontalLayout(personsLink, hobbiesLink, aboutLink);
        navBar.getStyle()
                .set("gap", "20px")
                .set("justify-content", "center")
                .set("width", "100%");

        // Header-layout
        VerticalLayout headerLayout = new VerticalLayout(title, navBar);
        headerLayout.setSpacing(true);
        headerLayout.setPadding(true);
        headerLayout.setAlignItems(VerticalLayout.Alignment.CENTER);

        headerLayout.getStyle()
                .set("background", "#f5f5f5")
                .set("padding-top", "20px")
                .set("padding-bottom", "20px")
                .set("margin-bottom", "20px");

        addToNavbar(headerLayout);

        // Footer
        Footer footer = new Footer(new Span("© 2025 Oma Sovellus"));
        footer.getStyle().set("padding", "10px");
        footer.getStyle().set("text-align", "center");
        footer.getStyle().set("width", "100%");
        footer.getStyle().set("background", "#f5f5f5");
        footer.getStyle().set("position", "fixed");
        footer.getStyle().set("bottom", "0");
        footer.getStyle().set("left", "0");
        footer.getStyle().set("z-index", "1000");

        getElement().appendChild(footer.getElement());
    }
}

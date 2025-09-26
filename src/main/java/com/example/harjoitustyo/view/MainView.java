package com.example.harjoitustyo.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Etusivu")
@RolesAllowed({"USER", "ADMIN"})
public class MainView extends VerticalLayout {
    public MainView() {
        add(new H1("Tervetuloa sovellukseen!"));
    }
}

package com.example.harjoitustyo.view;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

import jakarta.annotation.security.RolesAllowed;

@Route(value = "about", layout = MainLayout.class)
@PageTitle("Tietoa")
@RolesAllowed({"USER", "ADMIN"})   // ← molemmat roolit
public class AboutView extends VerticalLayout {
    public AboutView() {
        add(new H2("Tietoa sovelluksesta"),
                new Paragraph("Tämä on harjoitustyö Vaadinilla ja Spring Bootilla. Sovellus hallinnoi henkilöitä, osoitteita, harrastuksia ja painomittauksia."));
    }
}

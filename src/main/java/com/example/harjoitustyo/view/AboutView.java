package com.example.harjoitustyo.view;

import com.vaadin.flow.component.UI;
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
        var locale = UI.getCurrent().getLocale();

        add(
                new H2(getTranslation("about.title", locale)),
                new Paragraph(getTranslation("about.description", locale))
        );
    }
}

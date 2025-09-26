package com.example.harjoitustyo.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
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

        // Push-demo
        Span label = new Span("Odotetaan päivitystä...");
        Button button = new Button("Käynnistä päivitys", e -> startBackgroundUpdate(label));
        button.getStyle().set("background-color", "yellow");

        add(label, button);
    }

    private void startBackgroundUpdate(Span label) {
        UI ui = UI.getCurrent();
        new Thread(() -> {
            try {
                Thread.sleep(5000); // Odotetaan 5 sekuntia
                ui.access(() -> label.setText("Tämä teksti päivittyi server pushilla!"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

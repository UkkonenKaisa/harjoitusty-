package com.example.harjoitustyo;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Theme(value = "harjoitustyo") // tämä kertoo että käytetään frontend/themes/harjoitustyo/styles.css
public class HarjoitustyoApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(HarjoitustyoApplication.class, args);
    }
}

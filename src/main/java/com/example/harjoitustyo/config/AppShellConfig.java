package com.example.harjoitustyo.config;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.shared.communication.PushMode;
import com.vaadin.flow.theme.Theme;
import org.springframework.context.annotation.Configuration;

@Configuration
@Push(PushMode.AUTOMATIC)   // 🔄 server push käytössä
@Theme(value = "harjoitustyo") // 🎨 teema asetetaan myös tänne
public class AppShellConfig implements AppShellConfigurator {
}

package com.example.harjoitustyo.config;

import com.vaadin.flow.i18n.I18NProvider;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.*;

@Component
public class CustomI18NProvider implements I18NProvider {

    public static final String BUNDLE_PREFIX = "messages";
    private final List<Locale> locales = Arrays.asList(Locale.ENGLISH, new Locale("fi"));

    @Override
    public List<Locale> getProvidedLocales() {
        return locales;
    }

    @Override
    public String getTranslation(String key, Locale locale, Object... params) {
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_PREFIX, locale);
        if (bundle.containsKey(key)) {
            String value = bundle.getString(key);
            if (params.length > 0) {
                return MessageFormat.format(value, params);
            }
            return value;
        }
        return key; // jos ei löydy, palautetaan avain
    }
}

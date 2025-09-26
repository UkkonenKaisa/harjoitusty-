package com.example.harjoitustyo.view;

import com.example.harjoitustyo.model.Person;
import com.example.harjoitustyo.model.WeightMeasurement;
import com.example.harjoitustyo.repository.PersonRepository;
import com.example.harjoitustyo.repository.WeightMeasurementRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.harjoitustyo.model.Address;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.example.harjoitustyo.model.Hobby;
import com.example.harjoitustyo.repository.HobbyRepository;
import com.vaadin.flow.router.PageTitle;
import jakarta.annotation.security.RolesAllowed;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Route(value = "persons", layout = MainLayout.class)
@PageTitle("Henkilöt")
@RolesAllowed({"USER", "ADMIN"})   // ← lisäys tähän
public class PersonsView extends VerticalLayout {

    private final PersonRepository personRepository;
    private final WeightMeasurementRepository weightMeasurementRepository;
    private final HobbyRepository hobbyRepository;

    private MultiSelectComboBox<Hobby> hobbyField =
            new MultiSelectComboBox<>("Harrastukset");

    private Grid<Person> personGrid = new Grid<>(Person.class);
    private Grid<WeightMeasurement> weightGrid = new Grid<>(WeightMeasurement.class);

    // Henkilö-lomake
    private TextField firstName = new TextField("Etunimi");
    private TextField lastName = new TextField("Sukunimi");
    private com.vaadin.flow.component.datepicker.DatePicker birthDate =
            new com.vaadin.flow.component.datepicker.DatePicker("Syntymäaika");
    private Button addPersonButton = new Button("Lisää henkilö");


    private Button editPersonButton = new Button("Muokkaa valittua");
    private Button deletePersonButton = new Button("Poista valittu");

    // Osoite-lomake
    private TextField streetField = new TextField("Katuosoite");
    private TextField cityField = new TextField("Kaupunki");
    private TextField postalCodeField = new TextField("Postinumero");

    // Painomittaus-lomake
    private NumberField weightValueField = new NumberField("Paino (kg)");
    private DateTimePicker weightTimestampField = new DateTimePicker("Aikaleima");
    private Button addWeightButton = new Button("Lisää paino");
    private Button editWeightButton = new Button("Muokkaa valittua");
    private Button deleteWeightButton = new Button("Poista valittu");

    // Suodattimet
    private TextField firstNameFilter = new TextField();
    private TextField lastNameFilter = new TextField();
    private TextField cityFilter = new TextField();
    private TextField hobbyFilter = new TextField();
    private com.vaadin.flow.component.datepicker.DatePicker birthDateFilter =
            new com.vaadin.flow.component.datepicker.DatePicker();

    private Person selectedPerson;
    private WeightMeasurement selectedWeight;

    @Autowired
    public PersonsView(PersonRepository personRepository,
                       WeightMeasurementRepository weightMeasurementRepository,
                       HobbyRepository hobbyRepository) {
        this.personRepository = personRepository;
        this.weightMeasurementRepository = weightMeasurementRepository;
        this.hobbyRepository = hobbyRepository;

        addPersonButton.addClassName("add-person");



        // Harrastukset MultiSelect kenttään
        hobbyField.setItems(hobbyRepository.findAll());
        hobbyField.setItemLabelGenerator(Hobby::getName);

        // Henkilöiden grid
        personGrid.setColumns("id", "firstName", "lastName", "birthDate");

        // Lisää osoitetiedot Gridin sarakkeiksi
        personGrid.addColumn(p -> p.getAddress() != null ? p.getAddress().getStreet() : "")
                .setHeader("Katuosoite");
        personGrid.addColumn(p -> p.getAddress() != null ? p.getAddress().getCity() : "")
                .setHeader("Kaupunki");
        personGrid.addColumn(p -> p.getAddress() != null ? p.getAddress().getPostalCode() : "")
                .setHeader("Postinumero");

        // Lisää harrastukset Gridin sarakkeeksi
        personGrid.addColumn(p -> p.getHobbies() != null
                        ? p.getHobbies().stream().map(Hobby::getName).collect(Collectors.joining(", "))
                        : "")
                .setHeader("Harrastukset");

        // Painomittausten grid
        weightGrid.setColumns("id", "value", "timestamp");

        // Valintalogiikat grideille
        personGrid.asSingleSelect().addValueChangeListener(event -> {
            selectedPerson = event.getValue();
            if (selectedPerson != null) {
                selectedPerson = personRepository.findByIdWithWeightMeasurements(selectedPerson.getId()).orElse(null);
                if (selectedPerson != null) {
                    weightGrid.setItems(selectedPerson.getWeightMeasurements());
                    firstName.setValue(selectedPerson.getFirstName());
                    lastName.setValue(selectedPerson.getLastName());
                    birthDate.setValue(selectedPerson.getBirthDate());

                    if (selectedPerson.getAddress() != null) {
                        streetField.setValue(selectedPerson.getAddress().getStreet());
                        cityField.setValue(selectedPerson.getAddress().getCity());
                        postalCodeField.setValue(selectedPerson.getAddress().getPostalCode());
                    } else {
                        streetField.clear();
                        cityField.clear();
                        postalCodeField.clear();
                    }

                    if (selectedPerson.getHobbies() != null) {
                        hobbyField.setValue(selectedPerson.getHobbies());
                    } else {
                        hobbyField.clear();
                    }
                }
            } else {
                weightGrid.setItems(Collections.emptyList());
                firstName.clear();
                lastName.clear();
                birthDate.clear();
                streetField.clear();
                cityField.clear();
                postalCodeField.clear();
                hobbyField.clear();
            }
        });

        weightGrid.asSingleSelect().addValueChangeListener(event -> {
            selectedWeight = event.getValue();
            if (selectedWeight != null) {
                weightValueField.setValue(selectedWeight.getValue());
                weightTimestampField.setValue(selectedWeight.getTimestamp());
            }
        });

        // CRUD-painikkeiden logiikka
        addPersonButton.addClickListener(e -> {
            Person person = new Person();
            person.setFirstName(firstName.getValue());
            person.setLastName(lastName.getValue());
            person.setBirthDate(birthDate.getValue());

            Address address = new Address();
            address.setStreet(streetField.getValue());
            address.setCity(cityField.getValue());
            address.setPostalCode(postalCodeField.getValue());
            person.setAddress(address);

            person.setHobbies(new HashSet<>(hobbyField.getValue()));

            personRepository.save(person);
            updateList();
        });

        editPersonButton.addClickListener(e -> {
            if (selectedPerson != null) {
                selectedPerson.setFirstName(firstName.getValue());
                selectedPerson.setLastName(lastName.getValue());
                selectedPerson.setBirthDate(birthDate.getValue());

                Address address = selectedPerson.getAddress();
                if (address == null) {
                    address = new Address();
                }
                address.setStreet(streetField.getValue());
                address.setCity(cityField.getValue());
                address.setPostalCode(postalCodeField.getValue());
                selectedPerson.setAddress(address);

                selectedPerson.setHobbies(new HashSet<>(hobbyField.getValue()));

                personRepository.save(selectedPerson);
                updateList();
            }
        });

        deletePersonButton.addClickListener(e -> {
            if (selectedPerson != null) {
                personRepository.delete(selectedPerson);
                selectedPerson = null;
                updateList();
            }
        });

        addWeightButton.addClickListener(e -> {
            if (selectedPerson != null) {
                WeightMeasurement wm = new WeightMeasurement();
                wm.setValue(weightValueField.getValue());
                wm.setTimestamp(weightTimestampField.getValue());
                wm.setPerson(selectedPerson);

                weightMeasurementRepository.save(wm);
                refreshWeightGrid();
            }
        });

        editWeightButton.addClickListener(e -> {
            if (selectedWeight != null) {
                selectedWeight.setValue(weightValueField.getValue());
                selectedWeight.setTimestamp(weightTimestampField.getValue());
                weightMeasurementRepository.save(selectedWeight);
                refreshWeightGrid();
            }
        });

        deleteWeightButton.addClickListener(e -> {
            if (selectedWeight != null) {
                weightMeasurementRepository.delete(selectedWeight);
                refreshWeightGrid();
            }
        });

        // Lomakkeet
        HorizontalLayout personForm = new HorizontalLayout(
                firstName, lastName, birthDate,
                streetField, cityField, postalCodeField,
                hobbyField,
                addPersonButton, editPersonButton, deletePersonButton
        );
        HorizontalLayout weightForm = new HorizontalLayout(
                weightValueField, weightTimestampField, addWeightButton, editWeightButton, deleteWeightButton
        );

        VerticalLayout weightSection = new VerticalLayout(weightForm, weightGrid);
        weightSection.setVisible(true);

        // 🔹 Suodattimet käyttöön
        firstNameFilter.addValueChangeListener(e -> applyFilters());
        lastNameFilter.addValueChangeListener(e -> applyFilters());
        cityFilter.addValueChangeListener(e -> applyFilters());
        hobbyFilter.addValueChangeListener(e -> applyFilters());
        birthDateFilter.addValueChangeListener(e -> applyFilters());

        // Suodattimien placeholderit
        firstNameFilter.setPlaceholder("Hae etunimellä");
        lastNameFilter.setPlaceholder("Hae sukunimellä");
        cityFilter.setPlaceholder("Hae kaupungilla");
        hobbyFilter.setPlaceholder("Hae harrastuksella");
        birthDateFilter.setPlaceholder("Syntynyt jälkeen");


        HorizontalLayout filters = new HorizontalLayout(
                firstNameFilter, lastNameFilter, cityFilter,
                hobbyFilter, birthDateFilter
        );
        filters.getStyle().set("background-color", "#f5f5f5");
        filters.getStyle().set("padding", "10px");
        filters.getStyle().set("border", "1px solid #ddd");
        filters.getStyle().set("border-radius", "8px");

        // Näkymän rakenne
        add(filters, personForm, personGrid, weightSection);

        updateList();
    }

    private void updateList() {
        personGrid.setItems(personRepository.findAll());
    }

    private void refreshWeightGrid() {
        if (selectedPerson != null) {
            selectedPerson = personRepository.findByIdWithWeightMeasurements(selectedPerson.getId()).orElse(null);
            if (selectedPerson != null) {
                weightGrid.setItems(selectedPerson.getWeightMeasurements());
            } else {
                weightGrid.setItems(Collections.emptyList());
            }
        }
    }

    private void applyFilters() {
        List<Person> persons = personRepository.findAll();

        if (!firstNameFilter.isEmpty()) {
            persons = persons.stream()
                    .filter(p -> p.getFirstName() != null &&
                            p.getFirstName().toLowerCase().startsWith(firstNameFilter.getValue().toLowerCase()))
                    .toList();
        }

        if (!lastNameFilter.isEmpty()) {
            persons = persons.stream()
                    .filter(p -> p.getLastName() != null &&
                            p.getLastName().toLowerCase().startsWith(lastNameFilter.getValue().toLowerCase()))
                    .toList();
        }

        if (!cityFilter.isEmpty()) {
            persons = persons.stream()
                    .filter(p -> p.getAddress() != null &&
                            p.getAddress().getCity() != null &&
                            p.getAddress().getCity().toLowerCase().startsWith(cityFilter.getValue().toLowerCase()))
                    .toList();
        }

        if (!hobbyFilter.isEmpty()) {
            persons = persons.stream()
                    .filter(p -> p.getHobbies() != null &&
                            p.getHobbies().stream()
                                    .anyMatch(h -> h.getName().toLowerCase().startsWith(hobbyFilter.getValue().toLowerCase())))
                    .toList();
        }

        if (birthDateFilter.getValue() != null) {
            persons = persons.stream()
                    .filter(p -> p.getBirthDate() != null &&
                            p.getBirthDate().isAfter(birthDateFilter.getValue()))
                    .toList();
        }

        personGrid.setItems(persons);
    }

}

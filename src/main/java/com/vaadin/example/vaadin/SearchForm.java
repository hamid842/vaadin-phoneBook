package com.vaadin.example.vaadin;

import com.helger.commons.state.ILeftRightIndicator;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.GeneratedVaadinTextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SearchForm extends FormLayout {

    TextField quickSearchValue = new TextField();
    DatePicker quickSearchBirthDate = new DatePicker();

    ComboBox quickSearchItem = new ComboBox();

    EmailField advEmail = new EmailField("Email");
    TextField advLastName = new TextField("Last Name");
    TextField advFirstName = new TextField("First Name");
    DatePicker advBirthDate = new DatePicker("Birth Date");

    private MainView mainView;
    private Customer customer = new Customer();
    private CustomerService customerService = CustomerService.getInstance();

    //TODO add clickListener to search button
    public SearchForm(MainView mainView) {
        this.mainView = mainView;
        //
        RadioButtonGroup<String> searchTypeSelection = new RadioButtonGroup<>();
        HorizontalLayout quickSearchArea = new HorizontalLayout();
        HorizontalLayout advancedSearchArea = new HorizontalLayout();
        //
        initQuickSearchArea(quickSearchArea);
        initAdvancedSearchArea(advancedSearchArea);
        //
        searchTypeSelection.setLabel("SEARCH MODE : ");
        searchTypeSelection.setItems("Quick Search", "Advanced Search");
        searchTypeSelection.addValueChangeListener(e -> {
            if ("Quick Search".equals(e.getValue())) {
                quickSearchArea.setVisible(true);
                advancedSearchArea.setVisible(false);
            } else {
                quickSearchArea.setVisible(false);
                advancedSearchArea.setVisible(true);
            }

        });
        //
        add(searchTypeSelection, quickSearchArea, advancedSearchArea);
    }

    private void initQuickSearchArea(HorizontalLayout quickSearchArea) {
        Button quickSearchButton = new Button("Quick search");
        //
        quickSearchButton.setIcon(VaadinIcon.SEARCH.create());
        quickSearchButton.addClickShortcut(Key.ENTER);
        quickSearchButton.addClickListener(event -> {
            mainView.quickSearchMethod(quickSearchValue.getValue());
        });
        //
        quickSearchItem.setItems(QuickSearchItem.values());
        quickSearchItem.setPlaceholder("Search Items ...");
        quickSearchBirthDate.setVisible(false);
        quickSearchItem.addValueChangeListener(e -> {
            if (QuickSearchItem.BirthDate.equals(e.getValue())) {
                quickSearchValue.setVisible(false);
                quickSearchBirthDate.setVisible(true);
            } else {
                quickSearchValue.setVisible(true);
                quickSearchBirthDate.setVisible(false);
            }
        });
        quickSearchValue.setPlaceholder("Filter By ...");
        quickSearchBirthDate.setPlaceholder("Filter By ...");

        quickSearchValue.setValueChangeMode(ValueChangeMode.ON_BLUR);
        //
        quickSearchArea.setSizeFull();
        quickSearchArea.setAlignItems(FlexComponent.Alignment.END);
        quickSearchArea.add(quickSearchItem, quickSearchValue, quickSearchBirthDate, quickSearchButton);
        quickSearchArea.setVisible(false);


    }

    private void initAdvancedSearchArea(HorizontalLayout advancedSearchArea) {
        Button advancedSearchButton = new Button("Advanced search");
        advancedSearchButton.setIcon(VaadinIcon.SEARCH.create());
        advancedSearchArea.addClickShortcut(Key.ENTER);

        advEmail.setClearButtonVisible(true);
        advEmail.setErrorMessage("Please enter a valid email address");
        advLastName.setClearButtonVisible(true);
        advFirstName.setClearButtonVisible(true);
        advBirthDate.setClearButtonVisible(true);
        //
        advancedSearchButton.addClickListener(e -> mainView.advancedSearchMethod());
        //
        advancedSearchArea.setSizeFull();
        advancedSearchArea.setAlignItems(FlexComponent.Alignment.END);
        advancedSearchArea.add(advFirstName, advLastName, advEmail, advBirthDate, advancedSearchButton);
        advancedSearchArea.setVisible(false);
    }

    public List<Customer> quickSearch(String filter) {
        List<Customer> arrayList = customerService.findAll();
        String currentSearchItem = quickSearchItem.getValue().toString();
        if (QuickSearchItem.FirstName.toString().equals(currentSearchItem)) {
            return arrayList.stream()
                    .filter(it -> it.getFirstName().equals(filter))
                    .collect(Collectors.toList());
        }
//        else if (QuickSearchItem.LastName.equals(currentSearchItem)) {
//
//            return arrayList.stream().filter(it -> it.equals(customer.getLastName()))
//                    .collect(Collectors.toList());
//
//        } else if (QuickSearchItem.BirthDate.equals(currentSearchItem)) {
//
//            return arrayList.stream().filter(it -> it.equals(customer.getBirthDate()))
//                    .collect(Collectors.toList());
//        }
        return customerService.findAll();
    }

    public List<Customer> advancedSearch(Customer customer) {
        return customerService.findAll();
    }
}
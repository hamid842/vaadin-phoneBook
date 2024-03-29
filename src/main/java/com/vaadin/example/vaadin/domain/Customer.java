package com.vaadin.example.vaadin.domain;

import com.vaadin.example.vaadin.enumeration.CustomerStatus;

import java.io.Serializable;
import java.time.LocalDate;


@SuppressWarnings("serial")
public class Customer implements Serializable, Cloneable {

    private Long id;

    private String firstName ;

    private String lastName ;

    private PhoneNumber phoneNumber;

    private Email email ;

    private LocalDate birthDate;

    private CustomerStatus status;





    public Customer() {
    }

    public Customer(Long id, String firstName, String lastName, LocalDate birthDate,
                    CustomerStatus status, PhoneNumber phoneNumber, Email email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public boolean isPersisted() {
        return id != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.id == null) {
            return false;
        }

        if (obj instanceof Customer && obj.getClass().equals(getClass())) {
            return this.id.equals(((Customer) obj).id);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (id == null ? 0 : id.hashCode());
        return hash;
    }

    @Override
    public Customer clone() throws CloneNotSupportedException {
        return (Customer) super.clone();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public static void setPhoneNumber(Customer customer, String s) {
    }
}
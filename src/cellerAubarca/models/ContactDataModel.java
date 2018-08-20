package cellerAubarca.models;

import javafx.beans.property.SimpleStringProperty;

public class ContactDataModel {

    private final SimpleStringProperty objectId;
    private final SimpleStringProperty name;
    private final SimpleStringProperty surname;
    private final SimpleStringProperty dni_nif;
    private final SimpleStringProperty telephone;
    private final SimpleStringProperty cp;
    private final SimpleStringProperty town;
    private final SimpleStringProperty address;
    private final SimpleStringProperty email;
    private final SimpleStringProperty accountNumber;

    public ContactDataModel(String objectId, String name, String surname, String dni_nif, String telephone, String cp, String town, String email, String address, String accountNumber){
        this.objectId = new SimpleStringProperty(objectId);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.dni_nif = new SimpleStringProperty(dni_nif);
        this.telephone = new SimpleStringProperty(telephone);
        this.cp = new SimpleStringProperty(cp);
        this.town = new SimpleStringProperty(town);
        this.address = new SimpleStringProperty(address);
        this.email = new SimpleStringProperty(email);
        this.accountNumber = new SimpleStringProperty(accountNumber);

    }

    public String getObjectId() {
        return objectId.get();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getDni_nif() {
        return dni_nif.get();
    }

    public void setDniNif(String dniNif) {
        this.dni_nif.set(dniNif);
    }

    public String getTelephone() {
        return telephone.get();
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

    public String getCp() {
        return cp.get();
    }

    public void setCp(String cp) {
        this.cp.set(cp);
    }

    public String getTown() {
        return town.get();
    }

    public void setTown(String town) {
        this.town.set(town);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getAccountNumber() {
        return accountNumber.get();
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber.set(accountNumber);
    }
}

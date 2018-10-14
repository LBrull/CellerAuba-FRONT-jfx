package cellerAubarca.models;

public abstract class Contact {

    private String objectId;
    private String name;
    private String surname;
    private String dni_nif;
    private String telephone;
    private String cp;
    private String town;
    private String address;
    private String email;
    private String accountNumber;

    public Contact(){}

    public Contact(String objectId, String name, String surname, String dni_nif, String telephone, String cp, String town, String email, String address, String accountNumber) {
        this.objectId = objectId;
        this.name = name;
        this.surname = surname;
        this.dni_nif = dni_nif;
        this.telephone = telephone;
        this.cp = cp;
        this.town = town;
        this.address = address;
        this.email = email;
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCP(String cp) {
        this.cp = cp;
    }

    public String getCp() {
        return cp;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getTown() {
        return town;
    }

    public String getDni_nif() {
        return dni_nif;
    }

    public void setDni_nif(String dni_nif) {
        this.dni_nif = dni_nif;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getFullName() {
        return this.name + " " + this.surname;
    }
}

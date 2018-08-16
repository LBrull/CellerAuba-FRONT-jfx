package cellerAubarca.models;

public class Provider extends Contact {

    public Provider() {
        super();
    }

    public Provider (String objectId, String name, String surname, String dni_nif, String telephone, String cp, String town, String email, String address, String accountNumber) {
        super (objectId, name, surname, dni_nif, telephone, cp, town, email, address, accountNumber);
    }
}

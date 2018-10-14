package cellerAubarca.models;

import javafx.beans.property.SimpleStringProperty;

import java.util.Map;

public class AlbaraDataModel {

    private final SimpleStringProperty objectId;
    private final SimpleStringProperty provider;
    private final SimpleStringProperty client;
    private final SimpleStringProperty date;
    private final SimpleStringProperty type;
    private Map<Product, Double> products;


    public AlbaraDataModel(SimpleStringProperty objectId, SimpleStringProperty provider, SimpleStringProperty client, SimpleStringProperty date, SimpleStringProperty type, Map<Product, Double> products) {
        this.objectId = objectId;
        this.provider = provider;
        this.client = client;
        this.date = date;
        this.type = type;
        this.products = products;
    }
}

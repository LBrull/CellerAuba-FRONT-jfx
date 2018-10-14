package cellerAubarca.models;

import javafx.beans.property.SimpleStringProperty;

import java.util.Map;

public class AlbaraDataModel {

    private final SimpleStringProperty objectId;
    private final SimpleStringProperty provider;
    private final SimpleStringProperty client;
    private final SimpleStringProperty date;
    private final SimpleStringProperty type;

    public AlbaraDataModel(String objectId, String provider, String client, String date, String type) {
        this.objectId = new SimpleStringProperty(objectId);
        this.provider = new SimpleStringProperty(provider);
        this.client = new SimpleStringProperty(client);
        this.date = new SimpleStringProperty(date);
        this.type = new SimpleStringProperty(type);
    }
}

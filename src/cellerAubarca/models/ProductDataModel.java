package cellerAubarca.models;

import javafx.beans.property.SimpleStringProperty;

public class ProductDataModel {

    private final SimpleStringProperty objectId;
    private final SimpleStringProperty description;
    private final SimpleStringProperty type;
    private final SimpleStringProperty price;
//    public enum Type {
//        AM("AM"),
//        RA("RA"),
//        OL("OL");
//
//        private String tipus;
//
//        Type(String type) {
//            this.tipus = type;
//        }
//
//        public String getTipus() {
//            return tipus;
//        }
//
//    }

    public ProductDataModel(String objectId, String description, String type, String price){
        this.objectId = new SimpleStringProperty(objectId);
        this.description = new SimpleStringProperty(description);
        this.type = new SimpleStringProperty(type);
        this.price = new SimpleStringProperty(price);
    }

    public Product toProduct() {
        Product p = new Product();
        p.setObjectId(getObjectId());
        p.setDescription(getDescription());
        p.setType(getType());
        p.setPrice(getPrice());
        return p;
    }

    public String getObjectId() {
        return objectId.get();
    }

    public SimpleStringProperty objectIdProperty() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId.set(objectId);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }
}

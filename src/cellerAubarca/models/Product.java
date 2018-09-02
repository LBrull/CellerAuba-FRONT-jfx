package cellerAubarca.models;

public class Product {

    private String description;
    private String type;
    private String price;
    private String objectId;


    public Product() {

    }

    public Product(String description, String type, String price) {
        this.description = description;
        this.type = type;
        this.price = price;
    }

    public Product(String objectId, String description, String type, String price) {
        this.objectId = objectId;
        this.description = description;
        this.type = type;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectId() {
        return objectId;
    }


}

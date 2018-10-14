package cellerAubarca.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Albara {
    private String objectId;
    private Provider provider;
    private Client client;
    private Date date;
    private Type type;
    private Map<Product, Double> products = new HashMap<Product, Double>();

    public Albara() {

    }

    public Albara(String objectId, Provider provider, Client client, Date date, Type type, Map<Product, Double> products) {
        this.objectId = objectId;
        this.provider = provider;
        this.client = client;
        this.date = date;
        this.type = type;
        this.products = products;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<Product, Double> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Double> products) {
        this.products = products;
    }

    public void addProduct (Product p, Double q) {
        products.put(p, q);
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}

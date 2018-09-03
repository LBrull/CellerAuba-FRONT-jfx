package cellerAubarca.models;

public class Temporada {

    private String objectId;
    private Type tipus;
    private String date;

    public Temporada() {}

    public Temporada (Type tipus, String date) {
        this.tipus = tipus;
        this.date = date;
    }

    public Type getTipus() {
        return tipus;
    }

    public void setTipus(Type tipus) {
        this.tipus = tipus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}

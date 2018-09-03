package cellerAubarca.models;

public class Temporada {

    private String objectId;
    private Type tipus;
    private String date;
    private Boolean active;

    public Temporada() {}

    public Temporada (Type tipus, String date, Boolean active) {
        this.tipus = tipus;
        this.date = date;
        this.active = active;
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

    public void setActive(boolean active) {
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }
}

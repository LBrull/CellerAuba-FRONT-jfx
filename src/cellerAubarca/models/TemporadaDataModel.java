package cellerAubarca.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class TemporadaDataModel {
    private final SimpleStringProperty objectId;
    private final SimpleStringProperty type;
    private final SimpleStringProperty date;
    private final SimpleBooleanProperty active;

    public TemporadaDataModel(String objectId, Type type, String date, Boolean active){
        this.objectId = new SimpleStringProperty(objectId);
        this.type = new SimpleStringProperty(type.getCode());
        this.date = new SimpleStringProperty(date);
        this.active = new SimpleBooleanProperty(active);
    }

    public Temporada toTemporada() {
        Temporada p = new Temporada();
        p.setObjectId(getObjectId());
        switch (type.getName()) {
            case "AM":
                p.setTipus(Type.AMETLLA);
                break;
            case "RA":
                p.setTipus(Type.RAIM);
                break;
            default:
                p.setTipus(Type.OLIVA);
                break;
        }
        p.setDate(getDate());
        p.setActive(isActive());
        return p;
    }

    public final Boolean getActive() { return active.get(); }

    public final void setActive(Boolean active) { this.active.set(active); }

    public String getObjectId() {
        return objectId.get();
    }

    public SimpleStringProperty objectIdProperty() {
        return objectId;
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public boolean isActive() {
        return active.get();
    }

    public BooleanProperty activeProperty() {
        return active;
    }
}

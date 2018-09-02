package cellerAubarca.models;

public enum Type {
    AMETLLA("AM", "Ametlla"),
    RAIM("RA", "Raïm"),
    OLIVA("OL", "Oliva");

    private String code;
    private String text;

    private Type(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }
    public String getText() {
        return text;
    }

    public static Type getByCode(String typeCode) {
        for (Type t: Type.values()) {
            if (t.code.equals(typeCode)) {
                return t;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return this.text;
    }

}

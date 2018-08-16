package cellerAubarca.models;

import org.json.JSONArray;

public class ServerResponse {
    private int status;
    private String message;
    private String token;
    private JSONArray returnedObjects;

    public ServerResponse () {

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JSONArray getReturnedObjects() {
        return returnedObjects;
    }

    public void setReturnedObjects(JSONArray returnedObjects) {
        this.returnedObjects = returnedObjects;
    }
}

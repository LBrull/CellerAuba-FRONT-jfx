package cellerAubarca.controllers;

import cellerAubarca.models.ServerResponse;
import cellerAubarca.models.Temporada;
import cellerAubarca.models.Type;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class DBTemporadesController {
    private static String DatabaseUrl = "https://cellerauba.herokuapp.com";
    private ArrayList<Temporada> temporades = null;
    private ArrayList<Temporada> ametllesTemporades = null;
    private ArrayList<Temporada> raimTemporades = null;
    private ArrayList<Temporada> olivaTemporades = null;

    ServerResponse saveTemporada(Temporada temporada) throws IOException, JSONException {
        String url = DatabaseUrl +"/api/temporada";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        post.setHeader("Content-Type", "application/json");
        String token = "Bearer "+ Preferences.userRoot().get("token", null);
        post.setHeader("authorization", token);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", temporada.getTipus().getCode());
        jsonObject.put("date", temporada.getDate());
        jsonObject.put("active", temporada.getActive());
        String json = jsonObject.toString(1);

        StringEntity entity = new StringEntity(json);
        post.setEntity(entity);

        CloseableHttpResponse response = client.execute(post);
        InputStream body = response.getEntity().getContent();

        ServerResponse res = new ServerResponse();

        if (response.getStatusLine().getStatusCode() == 200) {
            String responseString = new BasicResponseHandler().handleResponse(response);
            JSONObject jsonResponse = new JSONObject(responseString);
            System.out.println(jsonResponse.toString(1));
            res.setStatus(200);
            res.setMessage(responseString);
            client.close();
            return res;
        }
        else {
            res.setStatus(response.getStatusLine().getStatusCode());
            res.setMessage(readStream(body));
            client.close();
            return res;
        }

    }

    void resetData() {
        temporades = new ArrayList<>();
        ametllesTemporades = new ArrayList<>();
        olivaTemporades = new ArrayList<>();
        raimTemporades = new ArrayList<>();
    }

    private String readStream(InputStream body) throws IOException {
        String result = IOUtils.toString(body, StandardCharsets.UTF_8);
        System.out.println(result);
        return result;
    }

    ArrayList<Temporada> getTemporades() throws IOException, JSONException {
        String url = DatabaseUrl +"/api/temporades";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);

        get.setHeader("Content-Type", "application/json");
        String token = "Bearer "+ Preferences.userRoot().get("token", "");
        get.setHeader("authorization", token);
        CloseableHttpResponse response = client.execute(get);
        InputStream body = response.getEntity().getContent();
        ArrayList<Temporada> list = new ArrayList<>();

        if (response.getStatusLine().getStatusCode() == 200) {
            String responseString = new BasicResponseHandler().handleResponse(response);
            System.out.println("SERVER RESPONSE: "+ responseString);
            JSONArray temporades = new JSONArray(responseString);
            for (int i = 0; i < temporades.length(); ++i) {
                Temporada c = new Temporada();
                JSONObject jsonClient = temporades.getJSONObject(i);
                c.setObjectId(jsonClient.getString("_id"));

                String tipus = jsonClient.getString("type");
                switch (tipus) {
                    case "AM":
                        c.setTipus(Type.AMETLLA);
                        break;
                    case "RA":
                        c.setTipus(Type.RAIM);
                        break;
                    default:
                        c.setTipus(Type.OLIVA);
                        break;
                }
                c.setDate(jsonClient.getString("date"));
                c.setActive(jsonClient.getBoolean("active"));
                list.add(c);
            }
        }
        this.temporades = list;
        return list;
    }

    void splitTemporades() {

        for (Temporada temporade : this.temporades) {
            switch (temporade.getTipus().getCode()) {
                case "AM":
                    ametllesTemporades.add(temporade);
                    break;
                case "OL":
                    olivaTemporades.add(temporade);
                    break;
                default:
                    raimTemporades.add(temporade);
                    break;
            }
        }
    }

    ArrayList<Temporada> getTemporadesRaim() {
        return raimTemporades;
    }

    ArrayList<Temporada> getTemporadesAmetlla() {
        return ametllesTemporades;
    }

    ArrayList<Temporada> getTemporadesOliva() {
        return olivaTemporades;
    }

    ServerResponse deleteTemporada(String objectId) throws IOException {
        String url = DatabaseUrl +"/api/temporada/" + objectId;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpDelete delete = new HttpDelete(url);

        delete.setHeader("Content-Type", "application/json");
        String token = "Bearer "+ Preferences.userRoot().get("token", null);
        delete.setHeader("authorization", token);

        CloseableHttpResponse response = client.execute(delete);
        InputStream body = response.getEntity().getContent();

        ServerResponse res = new ServerResponse();

        if (response.getStatusLine().getStatusCode() == 200) {
            String responseString = new BasicResponseHandler().handleResponse(response);
            res.setStatus(200);
            res.setMessage(responseString);
            client.close();
            return res;
        }
        else {
            res.setStatus(response.getStatusLine().getStatusCode());
            res.setMessage(readStream(body));
            client.close();
            return res;
        }
    }
}

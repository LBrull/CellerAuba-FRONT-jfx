package cellerAubarca.controllers;

import cellerAubarca.models.Albara;
import cellerAubarca.models.Client;
import cellerAubarca.models.Provider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class DBAlbaransController {
    private static String DatabaseUrl = "https://cellerauba.herokuapp.com";

    public DBAlbaransController() {
    }
    public ArrayList<Albara> getInAlbarans () throws IOException, JSONException {
        ArrayList<Albara> list = new ArrayList<>();

        String url =DatabaseUrl + "/api/albaransIn";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);

        get.setHeader("Content-Type", "application/json");
        String token = "Bearer "+ Preferences.userRoot().get("token", null);
        get.setHeader("authorization", token);
        CloseableHttpResponse response = client.execute(get);
        String responseString = new BasicResponseHandler().handleResponse(response);
        System.out.println(responseString);
        client.close();

        if (response.getStatusLine().getStatusCode() == 200) {
            JSONArray albarans = new JSONArray(responseString);
            for (int i = 0; i < albarans.length(); ++i) {
                Albara a = new Albara();
                JSONObject jsonAlbara = albarans.getJSONObject(i);
                a.setObjectId(jsonAlbara.getString("_id"));

                Provider p = JSONObjectToProvider(jsonAlbara.getJSONObject("provider"));
                a.setProvider(p);
                Client c = JSONOObjectToClient(jsonAlbara.getJSONObject("client"));
                a.setClient(c);
                a.setDate(jsonAlbara.getString("date"));
                a.setType(jsonAlbara.getString("type"));
                //Map<Product, Double> products = JSONObjectToProductsMap(jsonAlbara.getJSONArray("products"));
                list.add(a);
            }
        }
        return list;
    }

//    private Map<Product, Double> JSONObjectToProductsMap(JSONArray products) {
//        for (int i=0; i<products.length(); ++i) {
//            Product product = products.getJSONObject(i);
//        }
//    }

    private Client JSONOObjectToClient(JSONObject client) throws JSONException {
        Client p = new Client();
        p.setObjectId(client.getString("_id"));
        p.setName(client.getString("name"));
        p.setSurname(client.getString("surname"));
        p.setDni_nif(client.getString("dni_nif"));
        p.setAccountNumber(client.getString("accountNumber"));
        p.setTelephone(client.getString("telephone"));
        p.setEmail(client.getString("email"));
        p.setCP(client.getString("cp"));
        p.setTown(client.getString("town"));
        p.setAddress(client.getString("address"));
        return p;
    }

    private Provider JSONObjectToProvider(JSONObject provider) throws JSONException {
        Provider p = new Provider();
        p.setObjectId(provider.getString("_id"));
        p.setName(provider.getString("name"));
        p.setSurname(provider.getString("surname"));
        p.setDni_nif(provider.getString("dni_nif"));
        p.setAccountNumber(provider.getString("accountNumber"));
        p.setTelephone(provider.getString("telephone"));
        p.setEmail(provider.getString("email"));
        p.setCP(provider.getString("cp"));
        p.setTown(provider.getString("town"));
        p.setAddress(provider.getString("address"));
        return p;
    }

}

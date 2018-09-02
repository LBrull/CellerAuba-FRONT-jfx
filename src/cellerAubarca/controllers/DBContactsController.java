package cellerAubarca.controllers;

import cellerAubarca.models.Client;
import cellerAubarca.models.Provider;
import cellerAubarca.models.ServerResponse;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.*;
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
import java.util.List;
import java.util.prefs.Preferences;

public class DBContactsController {

    private static String DatabaseUrl = "https://cellerauba.herokuapp.com";

    DBContactsController() {
    }

    public ArrayList<Client> getClients () throws IOException, JSONException {
        ArrayList<Client> list = new ArrayList<>();

        String url =DatabaseUrl + "/api/clients";
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
            JSONArray clients = new JSONArray(responseString);
            for (int i = 0; i < clients.length(); ++i) {
                Client c = new Client();
                JSONObject jsonClient = clients.getJSONObject(i);
                c.setObjectId(jsonClient.getString("_id"));
                c.setName(jsonClient.getString("name"));
                c.setSurname(jsonClient.getString("surname"));
                c.setDni_nif(jsonClient.getString("dni_nif"));
                c.setAccountNumber(jsonClient.getString("accountNumber"));
                c.setTelephone(jsonClient.getString("telephone"));
                c.setEmail(jsonClient.getString("email"));
                c.setCP(jsonClient.getString("cp"));
                c.setTown(jsonClient.getString("town"));
                c.setAddress(jsonClient.getString("address"));
                list.add(c);
            }
        }
        return list;
    }

    public ArrayList<Provider> getProviders() throws IOException, JSONException {
        ArrayList<Provider> list = new ArrayList<>();

       String url = DatabaseUrl + "/api/providers";
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
            JSONArray clients = new JSONArray(responseString);
            for (int i = 0; i < clients.length(); ++i) {
                Provider c = new Provider();
                JSONObject jsonClient = clients.getJSONObject(i);
                c.setObjectId(jsonClient.getString("_id"));
                c.setName(jsonClient.getString("name"));
                c.setSurname(jsonClient.getString("surname"));
                c.setDni_nif(jsonClient.getString("dni_nif"));
                c.setAccountNumber(jsonClient.getString("accountNumber"));
                c.setTelephone(jsonClient.getString("telephone"));
                c.setEmail(jsonClient.getString("email"));
                c.setCP(jsonClient.getString("cp"));
                c.setTown(jsonClient.getString("town"));
                c.setAddress(jsonClient.getString("address"));
                list.add(c);
            }
        }
        return list;
    }

    public ServerResponse saveNewClient(Client client) throws JSONException, IOException {
        String url = DatabaseUrl +"/api/client";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        post.setHeader("Content-Type", "application/json");
        String token = "Bearer "+ Preferences.userRoot().get("token", null);
        post.setHeader("authorization", token);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", client.getName());
        jsonObject.put("surname", client.getSurname());
        jsonObject.put("telephone", client.getTelephone());
        jsonObject.put("email", client.getEmail());
        jsonObject.put("cp", client.getCp());
        jsonObject.put("town", client.getTown());
        jsonObject.put("address", client.getAddress());
        jsonObject.put("dni_nif", client.getDni_nif());
        jsonObject.put("accountNumber", client.getAccountNumber());

        String json = jsonObject.toString(1);
        StringEntity entity = new StringEntity(json);
        post.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(post);
        InputStream body = response.getEntity().getContent();
        ServerResponse res = new ServerResponse();

        if (response.getStatusLine().getStatusCode() == 200) {
            String responseString = new BasicResponseHandler().handleResponse(response);
            res.setStatus(200);
            res.setMessage(responseString);
            httpClient.close();
            return res;
        }
        else {
            res.setStatus(response.getStatusLine().getStatusCode());
            res.setMessage(readStream(body));
            httpClient.close();
            return res;
        }
    }

    public ServerResponse saveNewProvider(Provider provider) throws JSONException, IOException {
        String url = DatabaseUrl +"/api/provider";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        post.setHeader("Content-Type", "application/json");
        String token = "Bearer "+ Preferences.userRoot().get("token", null);
        post.setHeader("authorization", token);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", provider.getName());
        jsonObject.put("surname", provider.getSurname());
        jsonObject.put("telephone", provider.getTelephone());
        jsonObject.put("email", provider.getEmail());
        jsonObject.put("cp", provider.getCp());
        jsonObject.put("town", provider.getTown());
        jsonObject.put("address", provider.getAddress());
        jsonObject.put("dni_nif", provider.getDni_nif());
        jsonObject.put("accountNumber", provider.getAccountNumber());

        String json = jsonObject.toString(1);
        StringEntity entity = new StringEntity(json);
        post.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(post);
        InputStream body = response.getEntity().getContent();
        ServerResponse res = new ServerResponse();

        if (response.getStatusLine().getStatusCode() == 200) {
            String responseString = new BasicResponseHandler().handleResponse(response);
            res.setStatus(200);
            res.setMessage(responseString);
            httpClient.close();
            return res;
        }
        else {
            res.setStatus(response.getStatusLine().getStatusCode());
            res.setMessage(readStream(body));
            httpClient.close();
            return res;
        }
    }

    private static String readStream(InputStream body) throws IOException {
        return IOUtils.toString(body, StandardCharsets.UTF_8);
    }

    public boolean clientExists(String name, String surname) throws IOException, JSONException {
        List<Client> clients = getClients();
        boolean found = false;
        int i=0;
        while (!found && i<clients.size()) {
            if (clients.get(i).getName().equals(name) && clients.get(i).getSurname().equals(surname)) found = true;
            ++i;
        }
        return found;
    }

    public boolean providerExists(String name, String surname) throws IOException, JSONException {
        List<Provider> providers = getProviders();
        boolean found = false;
        int i=0;
        while (!found && i<providers.size()) {
            if (providers.get(i).getName().equals(name) && providers.get(i).getSurname().equals(surname)) found = true;
            ++i;
        }
        return found;
    }

    public ServerResponse deleteOneProvider(String objectId) throws IOException {
        String url = DatabaseUrl +"/api/provider/" + objectId;
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

    public ServerResponse deleteOneClient(String objectId) throws IOException {
        String url = DatabaseUrl +"/api/client/" + objectId;
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

    public ServerResponse editProvider(Provider provider) throws JSONException, IOException {
        String url = DatabaseUrl +"/api/provider/" + provider.getObjectId();
        System.out.println("ID  QUE ENVIO: "+ provider.getObjectId());
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPut put = new HttpPut(url);

        put.setHeader("Content-Type", "application/json");
        String token = "Bearer "+ Preferences.userRoot().get("token", null);
        put.setHeader("authorization", token);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", provider.getName());
        jsonObject.put("surname", provider.getSurname());
        jsonObject.put("telephone", provider.getTelephone());
        jsonObject.put("email", provider.getEmail());
        jsonObject.put("cp", provider.getCp());
        jsonObject.put("town", provider.getTown());
        jsonObject.put("address", provider.getAddress());
        jsonObject.put("dni_nif", provider.getDni_nif());
        jsonObject.put("accountNumber", provider.getAccountNumber());
        String json = jsonObject.toString(1);

        StringEntity entity = new StringEntity(json);
        put.setEntity(entity);

        CloseableHttpResponse response = client.execute(put);
        InputStream body = response.getEntity().getContent();
        ServerResponse res = new ServerResponse();

        if (response.getStatusLine().getStatusCode() == 200) {
            String responseString = new BasicResponseHandler().handleResponse(response);
            res.setStatus(200);
            res.setMessage(responseString);
            client.close();
            System.out.println("OBJECTE QUE EM RETORNA EL SERVER: "+ res.getMessage());
            return res;
        }
        else {
            res.setStatus(response.getStatusLine().getStatusCode());
            res.setMessage(readStream(body));
            client.close();
            return res;
        }
    }

    public ServerResponse editClient(Client client) throws JSONException, IOException {
        String url = DatabaseUrl +"/api/client/" + client.getObjectId();
        System.out.println("ID  QUE ENVIO: "+ client.getObjectId());
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut put = new HttpPut(url);

        put.setHeader("Content-Type", "application/json");
        String token = "Bearer "+ Preferences.userRoot().get("token", null);
        put.setHeader("authorization", token);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", client.getName());
        jsonObject.put("surname", client.getSurname());
        jsonObject.put("telephone", client.getTelephone());
        jsonObject.put("email", client.getEmail());
        jsonObject.put("cp", client.getCp());
        jsonObject.put("town", client.getTown());
        jsonObject.put("address", client.getAddress());
        jsonObject.put("dni_nif", client.getDni_nif());
        jsonObject.put("accountNumber", client.getAccountNumber());
        String json = jsonObject.toString(1);

        StringEntity entity = new StringEntity(json);
        put.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(put);
        InputStream body = response.getEntity().getContent();
        ServerResponse res = new ServerResponse();

        if (response.getStatusLine().getStatusCode() == 200) {
            String responseString = new BasicResponseHandler().handleResponse(response);
            res.setStatus(200);
            res.setMessage(responseString);
            httpClient.close();
            System.out.println("OBJECTE QUE EM RETORNA EL SERVER: "+ res.getMessage());
            return res;
        }
        else {
            res.setStatus(response.getStatusLine().getStatusCode());
            res.setMessage(readStream(body));
            httpClient.close();
            return res;
        }
    }
}

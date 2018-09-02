package cellerAubarca.controllers;

import cellerAubarca.models.Product;
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
import java.util.prefs.Preferences;

public class DBProductsController {

    private static String DatabaseUrl = "https://cellerauba.herokuapp.com";

    public ServerResponse saveNewProduct(Product product) throws IOException, JSONException {
        String url = DatabaseUrl +"/api/product";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        post.setHeader("Content-Type", "application/json");
        String token = "Bearer "+ Preferences.userRoot().get("token", null);
        post.setHeader("authorization", token);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("description", product.getDescription());
        jsonObject.put("type", product.getType());
        jsonObject.put("price", product.getPrice());
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

    public ArrayList<Product> getProducts() throws IOException, JSONException {

        String url = DatabaseUrl +"/api/products";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);

        get.setHeader("Content-Type", "application/json");
        String token = "Bearer "+ Preferences.userRoot().get("token", "");
        get.setHeader("authorization", token);
        CloseableHttpResponse response = client.execute(get);
        InputStream body = response.getEntity().getContent();
        ArrayList<Product> list = new ArrayList<Product>();

        if (response.getStatusLine().getStatusCode() == 200) {
            String responseString = new BasicResponseHandler().handleResponse(response);
            JSONArray clients = new JSONArray(responseString);
            for (int i = 0; i < clients.length(); ++i) {
                Product c = new Product();
                JSONObject jsonClient = clients.getJSONObject(i);
                c.setObjectId(jsonClient.getString("_id"));
                c.setDescription(jsonClient.getString("description"));
                c.setType(jsonClient.getString("type"));
                c.setPrice(jsonClient.getString("price"));
                list.add(c);
            }
        }
        return list;
    }

    public ServerResponse deleteOneProduct(String ObjectId) throws IOException {

        String url = DatabaseUrl +"/api/product/" + ObjectId;
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

    private String readStream(InputStream body) throws IOException {
        String result = IOUtils.toString(body, StandardCharsets.UTF_8);
        System.out.println(result);
        return result;
    }

    public ServerResponse editProduct(Product newProduct) throws IOException, JSONException {
        String url = DatabaseUrl +"/api/product/" + newProduct.getObjectId();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPut put = new HttpPut(url);

        put.setHeader("Content-Type", "application/json");
        String token = "Bearer "+ Preferences.userRoot().get("token", null);
        put.setHeader("authorization", token);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("description", newProduct.getDescription());
        jsonObject.put("type", newProduct.getType());
        jsonObject.put("price", newProduct.getPrice());
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

package br.com.cast.turmaformacao.controledeestoque.model.http;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;

/**
 * Created by Administrador on 28/09/2015.
 */
public final class ProductService {

    //private static final String API_URL = "http://10.11.21.193:4000/api/v1/products";
    private static final String API_URL = "http://187.66.199.87:8080/ServidorRest/webresources/product/product";

    private ProductService() {
        super();
    }

    public static Product findByWebId(Long id) {

        Product product = null;

        try {

            URL url = new URL(API_URL);

            final HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                InputStream inputStream = con.getInputStream();
                product = objectMapper.readValue(inputStream, Product.class);
                con.disconnect();
            }


        } catch (Exception e) {

            Log.e(e.getClass().toString(), e.getMessage());
        }

        return product;
    }


    public static List<Product> getAll() {
        List<Product> list = new ArrayList<>();

        try {

            URL url = new URL(API_URL);
            final HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                InputStream inputStream = con.getInputStream();
                list = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class));
                con.disconnect();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    public static void save(Product produto) {

        try {
            URL url = new URL(API_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");

            OutputStream outputStream = con.getOutputStream();
            new ObjectMapper().writeValue(outputStream, produto);
            outputStream.flush();
            outputStream.close();


            int responseCode = con.getResponseCode();

            if(responseCode != HttpURLConnection.HTTP_OK){
                throw new RuntimeException("Error Code : "+responseCode);
            }

            con.disconnect();

        } catch (Exception e) {
            Log.e(e.getClass().toString(), e.getMessage());
        }



    }


}

package com.example.studentApp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class urlRequest {
    private String url;

    public urlRequest(String url) {
        this.url = url;
    }

    public String executeRequest() {
        ExecutorService exe;
        Future<String> todo;
        String response;
        URL u;

        response = "http://infort.gautero.fr/index.php?"+this.url;

        try {
            u = new URL(response);
        } catch (
                MalformedURLException e) {
            // Ce n'est qu'un exemple, pas de traitement propre de l'exception
            e.printStackTrace();
            u = null;
        }

        // On crée l'objet qui va gérer la thread
        exe = Executors.newSingleThreadExecutor();
        // On lance la thread
        todo = lireURL(exe, u);
        // On attend le résultat
        try {
            response = todo.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    private Future<String> lireURL(ExecutorService exe, URL u) {
        return exe.submit(() -> {
            URLConnection c;
            String inputline;
            StringBuilder codeHTML = new StringBuilder("");

            try {
                c = u.openConnection();
                //temps maximun alloué pour se connecter
                c.setConnectTimeout(60000);
                //temps maximun alloué pour lire
                c.setReadTimeout(60000);
                //flux de lecture avec l'encodage des caractères UTF-8
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(c.getInputStream(), "UTF-8"));
                while ((inputline = in.readLine()) != null) {
                    //concaténation+retour à la ligne avec \n
                    codeHTML.append(inputline + "\n");
                }
                //il faut bien fermer le flux de lecture
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return codeHTML.toString();
        });
    }

    public JSONObject executeRequestJSON() {
        ExecutorService exe;
        Future<String> todo;
        String response;
        URL u;

        response = "http://infort.gautero.fr/index.php?"+this.url;

        try {
            u = new URL(response);
        } catch (
                MalformedURLException e) {
            // Ce n'est qu'un exemple, pas de traitement propre de l'exception
            e.printStackTrace();
            u = null;
        }

        // On crée l'objet qui va gérer la thread
        exe = Executors.newSingleThreadExecutor();
        // On lance la thread
        todo = lireURL(exe, u);
        // On attend le résultat
        try {
            response = todo.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JSONObject rep;

        try {
            rep = new JSONObject(response);
        } catch (JSONException e) {
            e.printStackTrace();
            rep = new JSONObject();
        }
        return rep;
    }
}

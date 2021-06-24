package com.example.studentApp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);
        WebView mywebview = (WebView) findViewById(R.id.webview);
        mywebview.loadUrl("https://www.lemensuel.net/categorie/vie-etudiante/");
    }
}



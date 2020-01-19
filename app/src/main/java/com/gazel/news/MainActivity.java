package com.gazel.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.tls.OkHostnameVerifier;

public class MainActivity extends AppCompatActivity {

    private ArrayList<News> mNews;
    private ListView listView;
    private  CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        mNews = new ArrayList<>();

        customAdapter = new CustomAdapter(this, R.layout.item_news,mNews);

        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = mNews.get(position);

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.getLink()));
                startActivity(browserIntent);
            }
        });

       String url = "https://newsapi.org/v2/top-headlines?country=id&category=technology&apiKey=ceac321546c14b43a714f52720b0bf46";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("###", "onFailure: Failure");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    //Log.d("###", "onRespons" + response.body().string());

                    try {
                        String json = response.body().string();

                        JSONObject jsonObject = new JSONObject(json);
                        JSONArray articles = jsonObject.getJSONArray("articles");

                        for(int i = 0; i < articles.length(); i++) {
                            JSONObject article = (JSONObject) articles.get(i);
                            String title = article.getString("title");
                            String description = article.getString("description");
                            String image = article.getString("urlToImage");
                            String link = article.getString("url");

                            News news = new News(image, title, description,link);
                            mNews.add(news);

                            Log.d("###", "title: " + image);
                        }

                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                customAdapter.notifyDataSetChanged();
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        Log.d("###","onCreate: ");
    }
}

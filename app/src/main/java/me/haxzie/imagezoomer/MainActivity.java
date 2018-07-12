package me.haxzie.imagezoomer;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageListAdapter listAdapter;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        new LoadImages().execute("https://jsonplaceholder.typicode.com/photos");


    }

    class LoadImages extends AsyncTask<String, Integer, ArrayList> {

        private OkHttpClient okHttpClient;

        private LoadImages() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();

        }

        @Override
        protected ArrayList doInBackground(String... params) {

            ArrayList<ImageData> imageList = new ArrayList<>();
            Request.Builder builder = new Request.Builder();
            builder.url(params[0]);
            Request request = builder.build();

            try {
                Response response = okHttpClient.newCall(request).execute();
                String responseString = response.body().string();

                JSONArray jsonArray = new JSONArray(responseString);

                for (int i = 0; i < 10; i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String image_url = object.getString("url");
                    int image_id = object.getInt("id");
                    String image_title = object.getString("title");
                    imageList.add(new ImageData(image_id, image_url, image_title));
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return imageList;
        }

        @Override
        protected void onPostExecute(ArrayList imageDataList) {
            super.onPostExecute(imageDataList);

            listAdapter = new ImageListAdapter(mContext, imageDataList);
            recyclerView.setAdapter(listAdapter);

        }
    }
}

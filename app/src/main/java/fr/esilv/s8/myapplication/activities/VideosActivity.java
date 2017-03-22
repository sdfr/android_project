package fr.esilv.s8.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import fr.esilv.s8.myapplication.R;
import fr.esilv.s8.myapplication.adapters.VideosAdapter;
import fr.esilv.s8.myapplication.interfaces.OnVideoSelectedListener;
import fr.esilv.s8.myapplication.models.Videos;

public class VideosActivity extends AppCompatActivity implements OnVideoSelectedListener {
    private static final String VIDEOS_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=";
    private String query = "";
    public static final String API_KEY = "AIzaSyCW_4maNwr4CyTjRcgroR-6V8qgiGEtetg";

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        query = getIntent().getStringExtra("query");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getVideos();
    }

    private void getVideos(){
        StringRequest videosRequest = new StringRequest(VIDEOS_URL + query + "&type=video&key=" + API_KEY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("success", response);
                //parse data from webservice to get Videos as Java object
                Videos videos = new Gson().fromJson(response, Videos.class);

                setAdapter(videos);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Videos", "Error");
            }
        });
        Volley.newRequestQueue(this).add(videosRequest);
    }

    private void setAdapter(Videos videos) {
        VideosAdapter adapter = new VideosAdapter(videos);
        adapter.setOnVideoSelectedListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onVideoSelected(Videos.ItemsBean video) {
        String title = video .getSnippet().getTitle();
        String channel = video .getSnippet().getChannelTitle();
        String description = video .getSnippet().getDescription();
        String publicationDate = video .getSnippet().getPublishedAt();
        String videoId = video.getId().getVideoId();

        Intent nextActivity = new Intent(VideosActivity.this, VideoActivity.class);
        nextActivity.putExtra("title", title);
        nextActivity.putExtra("channel", channel);
        nextActivity.putExtra("description", description);
        nextActivity.putExtra("publicationDate", publicationDate);
        nextActivity.putExtra("videoId", videoId);

        startActivity(nextActivity);
    }
}

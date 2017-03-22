package fr.esilv.s8.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import fr.esilv.s8.myapplication.R;

public class VideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static String title = "title";
    private static String channel = "channel";
    private static String description = "description";
    private static String publicationDate = "publicationDate";
    private static String videoId = "videoId";

    private TextView titleTextView;
    private TextView channelTextView;
    private TextView descriptionTextView;
    private TextView publicationDateTextView;

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    public static final String API_KEY = "AIzaSyCW_4maNwr4CyTjRcgroR-6V8qgiGEtetg";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        title = getIntent().getStringExtra("title");
        channel = getIntent().getStringExtra("channel");
        description = getIntent().getStringExtra("description");
        publicationDate = getIntent().getStringExtra("publicationDate");


        titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(title);

        channelTextView = (TextView) findViewById(R.id.channel);
        channelTextView.setText(channel);

        descriptionTextView = (TextView) findViewById(R.id.description);
        descriptionTextView.setText(description);

        publicationDateTextView = (TextView) findViewById(R.id.publicationDate);
        publicationDateTextView.setText(publicationDate);

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            videoId = getIntent().getStringExtra("videoId");
            player.cueVideo(videoId); // Plays 'https://www.youtube.com/watch?v='+videoId
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format("error", errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(API_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubeView;
    }
}
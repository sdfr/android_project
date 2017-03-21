package fr.esilv.s8.myapplication.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import fr.esilv.s8.myapplication.R;

public class VideoActivity extends FragmentActivity {
    private static String title = "title";
    private static String channel = "channel";
    private static String description = "description";
    private static String publicationDate = "publicationDate";

    private TextView titleTextView;
    private TextView channelTextView;
    private TextView descriptionTextView;
    private TextView publicationDateTextView;

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
    }
}
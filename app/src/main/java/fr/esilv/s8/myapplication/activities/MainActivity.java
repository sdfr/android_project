package fr.esilv.s8.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.esilv.s8.myapplication.R;

public class MainActivity extends AppCompatActivity {
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = (EditText) findViewById(R.id.videoEditText);
        Button searchButton = (Button) findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchEditText.getText().toString().replace(" ", "+");
                findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                Intent nextActivity = new Intent(MainActivity.this, VideosActivity.class);
                nextActivity.putExtra("query", query);
                startActivity(nextActivity);
            }
        });
    }
}

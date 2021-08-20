package com.mayank.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private EditText mWebSiteEditText;
    private EditText mOpenLocationText;
    private EditText mShareText;
    private Button mShareTextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebSiteEditText = findViewById(R.id.open_website_input);
        mOpenLocationText = findViewById(R.id.open_location);
        mShareText = findViewById(R.id.share_text);
        mShareTextButton =  findViewById(R.id.share_text_btn);

        mShareTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = mShareText.getText().toString();
                String mimeType = "text/plain";
                ShareCompat.IntentBuilder
                        .from(MainActivity.this)
                        .setType(mimeType)
                        .setChooserTitle(R.string.share)
                        .setText(txt)
                        .startChooser();
            }
        });
    }

    public void openWebsite(View view) {

        String url = mWebSiteEditText.getText().toString(); //get the string value
        Uri webPage = Uri.parse(url); // encode and parse string into a Uri object
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("Web", "Can't handle this!");
        }
    }

    public void openLocation(View view) {
        String loc = mOpenLocationText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" +loc); // parse that string into a Uri object with a geo search query
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("Location", "Can't handle this");
        }
    }


}
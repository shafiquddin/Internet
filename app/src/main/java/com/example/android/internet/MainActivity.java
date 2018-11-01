package com.example.android.internet;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.time.Instant;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    boolean wifi=false;
    boolean mobile=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Webview.class);
                startActivity(intent);
            }
        });
        Button fbtn = (Button) findViewById(R.id.fbtn);
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://fb.com"));
                startActivity(intent);
            }
        });
        Button you = (Button) findViewById(R.id.yout);
        you.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mimeType="Test/plain";
                String title="Learning how to share";
                String textToshare="hello There";
                ShareCompat.IntentBuilder.from(MainActivity.this)
                        .setChooserTitle(title)
                        .setType(mimeType)
                        .setText(textToshare);

//                Intent intent = new Intent(MainActivity.this, Ywebview.class);
//                startActivity(intent);

            }
        });
        Button byou = (Button) findViewById(R.id.byou);
        byou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://youtube.com"));
                startActivity(intent);
            }
        });
        Button map = (Button) findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addressString = "India";
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("geo")
                        .path("0,0")
                        .query(addressString);
                Uri uri = builder.build();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });
        if(!Network()){
            Toast.makeText(this, "not Connected", Toast.LENGTH_SHORT).show();
            btn.setEnabled(false);
            you.setEnabled(false);
            byou.setEnabled(false);
            map.setEnabled(false);
            fbtn.setEnabled(false);
        }
        else{
            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
            btn.setEnabled(true);
            you.setEnabled(true);
            byou.setEnabled(true);
            map.setEnabled(true);
            fbtn.setEnabled(true);
        }
    }
    public boolean Network(){
        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos=connectivityManager.getAllNetworkInfo();

        for(NetworkInfo info:networkInfos){
            if(info.isConnected()){
                wifi=true;
            }
            else if(info.isConnected()){
                mobile=true;
            }
        }
        return  wifi||mobile;

    }

}
package com.example.user.broadcastassignment.activity.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.broadcastassignment.R;
import com.example.user.broadcastassignment.activity.Service.MyService;

public class MainActivity extends AppCompatActivity {

    private String stringData;
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent()!=null && getIntent().getExtras()!=null)
            stringData = getIntent().getExtras().getString("Data");
        textView=(TextView) findViewById(R.id.textView);
        button=(Button) findViewById(R.id.button);

        if(stringData!=null) {
            textView.setText(stringData);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, MyService.class));
            }
        });


    }
}

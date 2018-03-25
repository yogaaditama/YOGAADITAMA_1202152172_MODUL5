package com.example.asusx453sa.YOGAADITAMA_1202152172_MODUL5.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.asusx453sa.YOGAADITAMA_1202152172_MODUL5.R;

public class AddToDoActivity extends AppCompatActivity {
    private EditText Eactivity, Edescription, Epriority;
    private Button Simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        Eactivity = findViewById(R.id.Etodo);
        Edescription = findViewById(R.id.Edesc);
        Epriority = findViewById(R.id.Eprio);
        Simpan = findViewById(R.id.save);

        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("activity",Eactivity.getText().toString());
                intent.putExtra("desc",Edescription.getText().toString());
                intent.putExtra("priority",Epriority.getText().toString());
                setResult(1,intent);
                finish();//finishing activity
            }
        });
    }
}

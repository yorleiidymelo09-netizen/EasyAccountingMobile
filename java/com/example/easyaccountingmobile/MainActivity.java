package com.example.easyaccountingmobile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnIngresos).setOnClickListener(v -> {
                startActivity(new android.content.Intent(this, IngresosActivity.class));
        });
        findViewById(R.id.btnGastos).setOnClickListener(v -> {
                startActivity(new android.content.Intent(this, GastosActivity.class));
        });

        findViewById(R.id.btnResumen).setOnClickListener(v -> {
            startActivity(new android.content.Intent(this, ResumenActivity.class));
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}
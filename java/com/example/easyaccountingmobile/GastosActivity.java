package com.example.easyaccountingmobile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class GastosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_gastos);

        android.content.SharedPreferences datos =
                getSharedPreferences("EasyAccounting", MODE_PRIVATE);

        ((android.widget.EditText) findViewById(R.id.etDescripcionGasto))
                .setText(datos.getString("descripcionGasto", ""));

        ((android.widget.EditText) findViewById(R.id.etValorGasto))
                .setText(datos.getString("valorGasto", ""));


        findViewById(R.id.btnGuardarGasto).setOnClickListener(v -> {
            String descripcion = ((android.widget.EditText) findViewById(R.id.etDescripcionGasto)).getText().toString();
            String valor = ((android.widget.EditText) findViewById(R.id.etValorGasto)).getText().toString();

            if (descripcion.trim().isEmpty() || valor.trim().isEmpty()) {
                android.widget.Toast.makeText(this, "Completa los dos campos", android.widget.Toast.LENGTH_SHORT).show();
                return;
            }
            double nuevoGasto;

            try {
                nuevoGasto = Double.parseDouble(valor.trim());

                if (!Double.isFinite(nuevoGasto) || nuevoGasto <= 0) {
                    throw new NumberFormatException();
                }

            } catch (NumberFormatException e) {
                android.widget.Toast.makeText(this,
                        "Ingresa un valor válido mayor que cero",
                        android.widget.Toast.LENGTH_SHORT).show();
                return;
            }


            double totalAnterior = Double.longBitsToDouble(
                    datos.getLong("totalGastosBits",
                            Double.doubleToRawLongBits(0.0)));

            double totalGastos = totalAnterior + nuevoGasto;
            datos.edit()
                    .putLong("totalGastosBits",
                            Double.doubleToRawLongBits(totalGastos))
                    .putString("descripcionGasto", descripcion)
                    .putString("valorGasto", valor)
                    .apply();

            android.widget.Toast.makeText(this, "Gasto guardado correctamente", android.widget.Toast.LENGTH_SHORT).show();
        });


    }
}
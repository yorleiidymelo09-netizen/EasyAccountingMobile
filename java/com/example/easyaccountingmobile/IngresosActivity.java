package com.example.easyaccountingmobile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class IngresosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_ingresos);
        android.content.SharedPreferences datos = getSharedPreferences("EasyAccounting", MODE_PRIVATE);
        ((android.widget.EditText) findViewById(R.id.etDescripcionIngreso))
                .setText(datos.getString("descripcionIngreso", ""));
        ((android.widget.EditText) findViewById(R.id.etValorIngreso))
                .setText(datos.getString("valorIngreso", ""));
        findViewById(R.id.btnGuardarIngreso).setOnClickListener(v -> {


            String valorTexto = ((android.widget.EditText)
                    findViewById(R.id.etValorIngreso))
                    .getText().toString().trim();

            double nuevoIngreso;

            try {
                nuevoIngreso = Double.parseDouble(valorTexto);
                if (!Double.isFinite(nuevoIngreso) || nuevoIngreso <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                android.widget.Toast.makeText(this,
                        "Ingresa un valor válido mayor que cero",
                        android.widget.Toast.LENGTH_SHORT).show();
                return;
            }

            double totalAnterior = Double.longBitsToDouble(
                    datos.getLong("totalIngresosBits",
                            Double.doubleToRawLongBits(0.0)));

            double totalIngresos = totalAnterior + nuevoIngreso;

            datos.edit()
                    .putLong("totalIngresosBits", Double.doubleToRawLongBits(totalIngresos))
                .putString("descripcionIngreso", ((android.widget.EditText) findViewById(R.id.etDescripcionIngreso)).getText().toString())
                .putString("valorIngreso", ((android.widget.EditText) findViewById(R.id.etValorIngreso)).getText().toString())
                .apply();

        android.widget.Toast.makeText(this, "Ingreso guardado correctamente", android.widget.Toast.LENGTH_SHORT).show();
    });
    }
}
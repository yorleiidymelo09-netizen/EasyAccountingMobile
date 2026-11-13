package com.example.easyaccountingmobile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResumenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_resumen);

        android.content.SharedPreferences datos =
                getSharedPreferences("EasyAccounting", MODE_PRIVATE);

        double totalIngresosGuardados = Double.longBitsToDouble(
                datos.getLong("totalIngresosBits",
                        Double.doubleToRawLongBits(0.0)));
        double totalGastosGuardados = Double.longBitsToDouble(
                datos.getLong("totalGastosBits",
                        Double.doubleToRawLongBits(0.0)));

        double ingresos = totalIngresosGuardados;
        double gastos = totalGastosGuardados;


        double saldo = ingresos - gastos;

        ((android.widget.TextView) findViewById(R.id.tvTotalIngresos))
                .setText("Total ingresos: $" + ingresos);

        ((android.widget.TextView) findViewById(R.id.tvTotalGastos))
                .setText("Total gastos: $" + gastos);

        ((android.widget.TextView) findViewById(R.id.tvSaldo))
                .setText("Saldo disponible: $" + saldo);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
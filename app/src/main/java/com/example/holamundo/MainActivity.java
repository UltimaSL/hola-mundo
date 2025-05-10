package com.example.holamundo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btnImpression = null;
    EditText editLabel1 = null;
    CheckBox id_checkBox = null;
    ImageButton imageButton = null;
    CalendarView miCalendario = null;
    String miFecha = "";

    @SuppressLint({"MissingInflatedId", "WrongViewCast", "SimpleDateFormat"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Inicialización de Vistas
        this.btnImpression = findViewById(R.id.btnImpression);
        editLabel1 = findViewById(R.id.txtCampo);
        id_checkBox = findViewById(R.id.id_checkBox);
        imageButton = findViewById(R.id.imageButton);
        miCalendario = findViewById(R.id.calendarView);


        SimpleDateFormat formatoFechaInicial = new SimpleDateFormat("dd/MM/yyyy");
        miFecha = formatoFechaInicial.format(new Date(miCalendario.getDate()));

        miCalendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                miFecha = dayOfMonth + "/" + (month + 1) + "/" + year;
            }
        });


        btnImpression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hora actual
                SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
                String horaActual = formatoHora.format(new Date());

                // texto del EditText
                String textoIngresado = editLabel1.getText().toString();

                // estado del CheckBox
                boolean checkSeleccionado = id_checkBox.isChecked();

                // mensaje completo
                String msgCompleto =
                        "Texto: " + textoIngresado +
                        ", CheckBox: " + checkSeleccionado +
                        ", Hora: " + horaActual +
                        ", Fecha: " + miFecha;

                // para mostrar en el snackbar
                // Snackbar.make(v, msgCompleto, Snackbar.LENGTH_LONG).show();

                // por si quiero el Toast
                Toast.makeText(getApplicationContext(), msgCompleto, Toast.LENGTH_LONG).show();
            }
        });

        // boton en un imagen
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent puente = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(puente);
            }
        });
    }
}
package com.example.holamundo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
  private long fechaSeleccionadaMillis; // Variable para guardar la fecha

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

    // Referencias a los elementos
    Button btn1 = findViewById(R.id.mi_button);
    EditText editText = findViewById(R.id.editTextText);
    TextView textView = findViewById(R.id.textView);
    CheckBox checkBox = findViewById(R.id.checkBox);
    ImageView imageView = findViewById(R.id.imageView);
    CalendarView calendario = findViewById(R.id.calendarView);
    ImageButton imgButton = findViewById(R.id.imageButton);

    // Guardar la fecha seleccionada al cambiar el calendario
    fechaSeleccionadaMillis = calendario.getDate(); // valor inicial

    calendario.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
      Log.i("Fecha", "Actualiza fecha");
      Calendar c = Calendar.getInstance();
      c.set(year, month, dayOfMonth);
      fechaSeleccionadaMillis = c.getTimeInMillis(); // actualizar fecha seleccionada
    });
    btn1.setOnClickListener((v) -> {
      Log.d("FECHA_CALENDARIO", "getDate(): " + new Date(calendario.getDate()).toString());
      Date fecha = new Date(calendario.getDate());
      String texto = editText.getText().toString();
      textView.setText(texto);

      Date fechaSeleccionada = new Date(fechaSeleccionadaMillis);
      SimpleDateFormat formato = new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss", Locale.getDefault());

      Toast.makeText(MainActivity.this, formato.format(fecha), Toast.LENGTH_SHORT).show();
      Snackbar snack = Snackbar.make(v, "No me deja simples", Snackbar.LENGTH_LONG);
      snack.show();
    });
    checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
      imageView.setVisibility(isChecked ? View.VISIBLE : View.GONE);
    });
    imgButton.setOnClickListener((View v) -> {
      Intent intent = new Intent(this, MainActivity2.class);
      startActivity(intent);
    });
  }
}

package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText poleImie, poleNazwisko, poleEmail, poleHaslo;

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

        poleImie = findViewById(R.id.poleImie);
        poleNazwisko = findViewById(R.id.poleNazwisko);
        poleEmail = findViewById(R.id.poleEmail);
        poleHaslo = findViewById(R.id.poleHaslo);
        Button przycisk = findViewById(R.id.przycisk);

        przycisk.setOnClickListener(v -> waliduj());
    }

    private void waliduj() {
        String imie = poleImie.getText().toString().trim();
        String nazwisko = poleNazwisko.getText().toString().trim();
        String email = poleEmail.getText().toString().trim();
        String haslo = poleHaslo.getText().toString().trim();

        boolean maDlugosc = haslo.length() >= 8;
        boolean maDuza = haslo.matches(".*[A-Z].*");
        boolean maMala = haslo.matches(".*[a-z].*");
        boolean maSpecjalny = haslo.matches(".*[^A-Za-z0-9].*");

        String wiadomosc;

        if(!maDlugosc){
            wiadomosc = "Hasło musi zawierać co najmniej 8 znaków";
        }
        if(!maDuza){
            wiadomosc = "Hasło musi zawierać dużą literę";
        }
        if(!maMala){
            wiadomosc = "Hasło musi zawierać małą literę";
        }
        if(!maSpecjalny){
            wiadomosc = "Hasło musi zawierać znak specjalny";
        }

        if(imie.isEmpty() || nazwisko.isEmpty() || email.isEmpty() || haslo.isEmpty()){
            wiadomosc = "Uzupełnij wszystkie pola";
            Toast.makeText(this, wiadomosc, Toast.LENGTH_LONG).show();
        }else if(!email.contains("@") || !email.contains(".")){
            wiadomosc = "Podaj poprawny adres email";
            Toast.makeText(this, wiadomosc, Toast.LENGTH_LONG).show();
        }else if(!maDlugosc || !maDuza || !maMala || !maSpecjalny){
            Toast.makeText(this, wiadomosc, Toast.LENGTH_LONG).show();
        }else{
            wiadomosc = "Dane są poprawne";
            Toast.makeText(this, wiadomosc, Toast.LENGTH_LONG).show();
        }
    }
}

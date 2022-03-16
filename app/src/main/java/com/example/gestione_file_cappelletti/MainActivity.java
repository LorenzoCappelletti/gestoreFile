package com.example.gestione_file_cappelletti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText testo_nome_file;
    Button btnScrivere;
    Button btnLeggere;
    TextView testo;
    gestoreFile ges;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testo_nome_file = (EditText) findViewById(R.id.titolo);
        btnScrivere = (Button) findViewById(R.id.btnScrivere);
        btnLeggere = (Button) findViewById(R.id.btnLeggere);
        testo = (TextView) findViewById(R.id.textView);

        ges = new gestoreFile(testo_nome_file.getText().toString());

        btnScrivere.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String esito = ges.scriviFile("prova.txt", getApplicationContext());
                Toast.makeText(getApplicationContext(), esito, Toast.LENGTH_SHORT).show();
            }
        });

        btnLeggere.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {

                StringBuilder stringaletta = ges.leggiFile("prova.txt", getApplicationContext());
                String stringa = stringaletta.toString();
                testo.setText(stringa);

                //String stringaletta = ges.leggiFileRaw(getApplicationContext()); // serve solo il contesto poichè il nome lo riprende da la
                //testo.setText(stringaletta);

            }
        });
    }
}
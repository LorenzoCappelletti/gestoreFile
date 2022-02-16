package com.example.gestione_file_cappelletti;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class gestoreFile
{
    private String nome_file;
    Context c;
    StringBuilder sb; // variabile di istanza

    public gestoreFile(String nome_file)
    {
        this.nome_file = nome_file;
        sb= new StringBuilder();
    }

    public StringBuilder leggiFile(String nome_file, Context c)
    {

        try
        {
            String inputString;
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(c.openFileInput(nome_file)));
            while((inputString = inputReader.readLine()) != null)
            {
                sb.append(inputString);
            }
        }

        catch (FileNotFoundException e) // se il file non esiste genera un errore
        {
            Log.e("Errore", "Errore file non esistente");
        }

        catch (IOException e) // è la classe madre delle eccezioni e va messo sempre sotto il primo catch
        {
            Log.e("errore", e.toString()); // errore di Log in lettura
        }
        return sb;
    }

    public String scriviFile(String nome_file, Context c)
    {
        FileOutputStream fileO;
        String esito = "";
        String testo = "Questo è il testo del file";
        try
        {
            //openFileOutput all'inizio da errore perchè ci vogliono le eccezioni (file può essere inesistente)
            fileO =  c.openFileOutput(nome_file,Context.MODE_PRIVATE); // come parametri si ha nome file è il modo in cui viene aperto
            fileO.write(testo.getBytes()); // conversione da caratteri a byte
            fileO.close();
            esito = "Scrittura corretta";
        }
        catch (FileNotFoundException e)
        {
            esito = "Attenzione errore in apertura";
        }
        catch (IOException e)
        {
            esito = "Errore in scrittura";
        }
        return esito;
    }
}

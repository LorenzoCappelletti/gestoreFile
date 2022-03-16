package com.example.gestione_file_cappelletti;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

/**
 * @author
 * @see
 */
public class gestoreFile
{
    private String nome_file;
    Context c;
    StringBuilder sb; // variabile di istanza

    /**
     *
     * @param nome_file
     */
    public gestoreFile(String nome_file)
    {
        this.nome_file = nome_file;
        sb= new StringBuilder();
    }

    public StringBuilder leggiFile(String nome_file, Context c)
    {
        String inputString;
        try
        {

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(c.openFileInput(nome_file)));
            while((inputString = inputReader.readLine()) != null)
            {
                sb.append(inputString);
            }
        }

        catch (FileNotFoundException e) // se il file non esiste genera un errore
        {
            Log.e("Errore", "Errore file non esistente");
            //return sb.append("errore file non esistente");
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

    public String leggiFileRaw(Context c)
    {
        String testo = "";
        Resources res = c.getResources();// prendere le risorse del contesto. Nel gestore si hanno tutte le risorse della mainActivity
        InputStream is = res.openRawResource(R.raw.brani);//inputStream deve aprire le risorse raw.
        // prima si va nella cartella resources res e poi si entra in quella raw

        BufferedReader br = new BufferedReader(new InputStreamReader(is)); // leggere una riga tramite BufferedReader, tramite il BufferedReader apriamo il file
        try // si mettono eccezioni per il readLine
        {
            while((testo = br.readLine()) != null)
            {
                sb.append(testo);
            }
        }
        catch (IOException e)
        {
            Log.e("errore", "errore in lettura");
        }

        return sb.toString();
    }

    public String leggiFileAssets()
    {
        String testo = " ";
        AssetManager am = c.getAssets(); // gestore degli assets
        try
        {
            //apertura viene messa dentro il try perchè non viene passato l'id, ma il nome che potrà essere digitato in modo errato
            InputStream is = am.open("lyrics.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while((testo = br.readLine()) != null)
            {
                sb.append(testo);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return sb.toString();

    }


}

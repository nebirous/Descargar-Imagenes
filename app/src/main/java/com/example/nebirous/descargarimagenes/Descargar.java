package com.example.nebirous.descargarimagenes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Descargar
        extends AppCompatActivity {

    private android.widget.ProgressBar progressBar;
    private android.widget.ListView listView;
    private Adaptador adp;
    private boolean cambio = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descargar);
        this.listView = (ListView) findViewById(R.id.listView);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Intent i = this.getIntent();
        Bundle b = i.getExtras();
        DescargarImg descarga = new DescargarImg();
        descarga.execute(b.getString("pag"));
    }

    public class DescargarImg extends AsyncTask<String, Integer, ArrayList<String>>{
        ArrayList<Bitmap> imagenes = new ArrayList<>();
        int recorrido = 0;
        @Override
        protected ArrayList<String> doInBackground(String... params) {
            try{
                URL url = new URL(params[0]);
                BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream()));
                String l, out = "";
                for(recorrido = 0; (l = input.readLine()) != null; recorrido++){
                    publishProgress(recorrido);
                    out = out + l + " ";
                }
                input.close();
                ArrayList<String> rutaImagenes = extImagen(out);
                cambio = true;
                for(String cad: rutaImagenes){
                    recorrido++;
                    publishProgress(recorrido);
                    URL urlimg = new URL(cad);
                    imagenes.add(BitmapFactory.decodeStream(urlimg.openConnection().getInputStream()));
                }
                return rutaImagenes;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute(){
            progressBar.setProgress(0);
            progressBar.setMax(100);
        }

        @Override
        protected void onProgressUpdate(Integer... values){
            super.onProgressUpdate(values);
            progressBar.setProgress(values[values.length - 1]);
        }

        @Override
        protected void onPostExecute(ArrayList<String> ruta){
            adp = new Adaptador(Descargar.this, R.layout.item, ruta);
            listView.setAdapter(adp);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(Descargar.this, Individual.class);
                    i.putExtra("img", imagenes.get(position));
                    startActivity(i);
                }
            });
        }

        public ArrayList extImagen(String cadena){
            ArrayList<String> rutaImagenes = new ArrayList<>();
            int posInicial = 0, posFinal;
            String aux;
            System.out.println(cadena.indexOf("<img src=\"", posInicial));
            while (cadena.indexOf("<img src=\"", posInicial) != -1) {
                posInicial = cadena.indexOf("<img src=\"", posInicial);
                posInicial=posInicial+10;
                posFinal = cadena.indexOf("\"", posInicial);
                aux=cadena.substring(posInicial , posFinal);
                if (!aux.contains(".gif")&&aux.contains("http"))
                    rutaImagenes.add(aux);
            }
            return rutaImagenes;
        }
    }

}

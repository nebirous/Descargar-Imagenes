package com.example.nebirous.descargarimagenes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActividadPrincipal extends AppCompatActivity {

    private android.widget.EditText editText;
    private android.widget.Button button;
    private android.widget.TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);

        init();
    }

    public void init(){
        this.editText = (EditText) findViewById(R.id.editText);
        this.textView = (TextView) findViewById(R.id.textView);
        this.button = (Button) findViewById(R.id.button);
    }

    public void descargar(View v){
        Intent i = new Intent (this, Descargar.class);
        String aux = editText.getText().toString();
        Bundle b =  new Bundle();
        b.putString("pag", aux);
        i.putExtras(b);
        startActivity(i);
    }

}

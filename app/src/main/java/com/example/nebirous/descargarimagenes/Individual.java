package com.example.nebirous.descargarimagenes;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class Individual extends AppCompatActivity {
    private ImageView iv;
    private EditText etGuardar;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual);
        this.iv = (ImageView) findViewById(R.id.imageView);
        this.etGuardar = (EditText) findViewById(R.id.editText2);

        Bitmap bitmap = getIntent().getParcelableExtra("img");
        iv.setImageBitmap(bitmap);
    }

    public void Guardar(View v) {
        BitmapDrawable bmpDOriginal = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = bmpDOriginal.getBitmap();
        String nombre = etGuardar.getText().toString() + ".jpg";
        File file = new File(Environment.getExternalStorageDirectory().toString(), nombre);
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            tostada(R.string.hecho);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void tostada(int a){
        Toast.makeText(this, a, Toast.LENGTH_LONG).show();
    }
}

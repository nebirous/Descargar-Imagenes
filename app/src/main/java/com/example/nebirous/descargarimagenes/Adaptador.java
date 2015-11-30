package com.example.nebirous.descargarimagenes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nebirous on 30/11/2015.
 */
public class Adaptador extends ArrayAdapter {
    private Context ctx;
    private int recurso;
    private ArrayList ruta;

    public Adaptador(Context ctx, int recurso, ArrayList ruta){
        super(ctx, recurso, ruta);
        this.ctx = ctx;
        this.recurso = recurso;
        this.ruta = ruta;
    }

    static class ViewHolder{
        public TextView tv;
    }

    @Override
    public View getView(final int posicion, View view, ViewGroup group){
        LayoutInflater i = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder vh = new ViewHolder();
        if(view == null){
            view = i.inflate(recurso, null);
            vh.tv = (TextView) view.findViewById(R.id.textView2);
            view.setTag(vh);
        }else{
            vh = (ViewHolder) view.getTag();
        }
        vh.tv.setText(ruta.get(posicion).toString());
        return view;
    }

}

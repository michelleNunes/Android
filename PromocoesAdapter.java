package com.example.adrianomerodack.promofind;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import model.bean.Promocoes;

/**
 * Created by Adriano Merodack on 23/10/2015.
 */
public class PromocoesAdapter extends ArrayAdapter<Promocoes> {
    Context contexto;
    int id;
    List<Promocoes> lista;

    public PromocoesAdapter(Context contexto, int id, List<Promocoes> lista){
        super(contexto,id,lista);
        this.contexto = contexto;
        this.lista = lista;
        this.id = id;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        Promocoes promocoes;
        ImageView foto;
        TextView Promo;
        TextView Desc;
        TextView Data;
        Bitmap raw;
        byte[] fotoArray;

        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(contexto);
            view = inflater.inflate(id, parent, false);
        }

        Promo = (TextView)view.findViewById(R.id.textViewPromo);
        Desc = (TextView)view.findViewById(R.id.textViewDesc);
        Data = (TextView)view.findViewById(R.id.textviewData);
        foto = (ImageView)view.findViewById(R.id.imageView);

        promocoes= lista.get(position);

        Promo.setText(promocoes.getPromocao());
        Desc.setText(promocoes.getDescricao());
        Data.setText(promocoes.getData());
        fotoArray = promocoes.getFoto();

        if(fotoArray!=null){
            raw  = BitmapFactory.decodeByteArray(fotoArray, 0, fotoArray.length);
            foto.setImageBitmap(raw);
        }

        return view;
    }



}

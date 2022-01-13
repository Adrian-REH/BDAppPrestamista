package com.example.benjabd.CrearCuenta;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benjabd.HomeI;
import com.example.benjabd.R;

import java.util.ArrayList;
import java.util.List;


import android.app.Dialog;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benjabd.Agenda.BenjaBD;
import com.example.benjabd.Agenda.EditAgenda;
import com.example.benjabd.BasedeDatos;
import com.example.benjabd.CambiarUsuario;
import com.example.benjabd.R;

import java.util.List;

public class AdapterC extends RecyclerView.Adapter<AdapterC.ViewHolder> {
    private static final String TAGs = "AdapterC";


    private final Context context;
    public static  class ViewHolder extends RecyclerView.ViewHolder{

        final   private TextView tv_Old,tv_Name,tv_tel;

        public ViewHolder( View itemView) {
            super(itemView);
            tv_tel = itemView.findViewById(R.id.tv_tel);
            tv_Old = itemView.findViewById(R.id.tv_Old);
            tv_Name = itemView.findViewById(R.id.tv_Name);

        }
    }
    public List<ItemC> itemCS;

    public AdapterC(List<ItemC> itemCS, Context context) {
        this.itemCS = itemCS;

        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_c,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tv_tel.setText(itemCS.get(i).getId());
        viewHolder.tv_Old.setText(itemCS.get(i).getUsuario());
        viewHolder.tv_Name.setText(itemCS.get(i).getContraseña());

        final String tel = itemCS.get(i).getId();
        final String old = itemCS.get(i).getUsuario();
        final String name = itemCS.get(i).getContraseña();
        Log.d(TAGs, "onBindViewHolder: called.");

        final Intent intent = new Intent(context, CambiarUsuario.class);


        intent.putExtra("OLD",old);
        final BasedeDatos benjaBD = new BasedeDatos(context);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle("ALERTA DE BOLUDO QUERIENDO BORRAR ALGO :V ").setMessage("EEH ESTAS POR BORRAR UN USUARIO LO QUERI BORRA O NO?" + " ORIGINAL:\n" +
                        "ID: " + tel +"\n" +
                        "Usuario: " +old+"\n" +
                        "Contraseña: "+name).setPositiveButton("SI QUERI?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        benjaBD.eliminarAgenda(tel);
                        Toast.makeText(context, "VEEEE YA LO ELIMINASTE CLIAAU!!",Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("O NO QUERI?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "JEJEJEJ NO SE ELIMINO ",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();

                alertDialog.show();



            }
        });
    }

    @Override
    public int getItemCount() {
        return itemCS.size();
    }
}

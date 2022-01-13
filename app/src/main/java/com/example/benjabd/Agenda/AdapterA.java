package com.example.benjabd.Agenda;

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

import static android.content.ContentValues.TAG;

public class AdapterA extends RecyclerView.Adapter<AdapterA.ViewHolder> {


    private final Context context;
    private static final String TAGs = "AdapterA";



    public static  class ViewHolder extends RecyclerView.ViewHolder{

      final   private TextView tv_Old,tv_Name,tv_tel,tv_FI,tv_FF;

        public ViewHolder( View itemView) {
            super(itemView);

            tv_Old = itemView.findViewById(R.id.tv_Old);
            tv_Name = itemView.findViewById(R.id.tv_Name);
            tv_tel = itemView.findViewById(R.id.tv_tel);
            tv_FI = itemView.findViewById(R.id.tv_FI);
            tv_FF = itemView.findViewById(R.id.tv_FF);
        }
    }





    public static ArrayList<ItemsA> listaA;


    public AdapterA(List<ItemsA> listaA, Context context) {
        this.listaA = (ArrayList<ItemsA>) listaA;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_agendar,viewGroup,false);
        ViewHolder viewHolder =new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        viewHolder.tv_Old.setText(listaA.get(i).getApellido());
        viewHolder.tv_Name.setText(listaA.get(i).getNombre());
        viewHolder.tv_tel.setText(listaA.get(i).getTelefono());
        viewHolder.tv_FI.setText(listaA.get(i).getFechaI());
        viewHolder.tv_FF.setText(listaA.get(i).getFechaF());
       // viewHolder.imCurso.setImageResource(listaA.get(i).getFechaF());



        final String old = listaA.get(i).getApellido();
        final String name = listaA.get(i).getNombre();
        final String tel = listaA.get(i).getTelefono();
        final String fi = listaA.get(i).getFechaI();
        final String ff = listaA.get(i).getFechaF();
        Log.d(TAGs, "onBindViewHolder: called.");

        final Intent intent = new Intent(context, EditAgenda.class);


        intent.putExtra("OLD",old);
        intent.putExtra("NAME",name);
        intent.putExtra("TEL",tel);
        intent.putExtra("FI",fi);
        intent.putExtra("FF",ff);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            //ABRIMOS SI ENCONTRAMOS EL NOMBRE DEL ARCHIVO
            public  void onClick(View view){


                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle("UN DIALOGO COMO CUALQUIER OTRO").setMessage("Eliminalo o Editalo, que desea Usted Señor?:\n" +
                        "ID: " + tel +"\n" +
                        "USUARIO: " +old+"\n" +
                        "CONTRASEÑA: "+name).setPositiveButton("Desea usted señor ELIMINARLO?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        final BenjaBD benjaBD = new BenjaBD(context);
                        benjaBD.eliminarAgenda(tel);
                        Toast.makeText(context, "VEEEE YA LO ELIMINASTE CLIAAU!!",Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Desea usted señor EDITARLO?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        context.startActivity(intent);
                    }
                });

                AlertDialog alertDialog = builder.create();

                alertDialog.show();



            }
        });

    }

    @Override
    public int getItemCount() {
        return listaA.size();
    }




    public void setfilter(List<ItemsA> listitem)
    {
        listaA=new ArrayList<>();
        listaA.addAll(listitem);

        notifyDataSetChanged();
    }

}

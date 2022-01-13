package com.example.benjabd.Agenda;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benjabd.HomeI;
import com.example.benjabd.R;

public class EditAgenda extends AppCompatActivity {
    EditText edt_oldE,edt_nameE,edt_telE,edt_FIE,edt_FFE,edt_MntIE,edt_MntFE;
    Button btn_Editar,btn_Borrar;
    ImageButton btn_lmr;
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_agenda);
        context=this;
        edt_oldE = findViewById(R.id.edt_oldE);
        edt_nameE = findViewById(R.id.edt_nameE);
        edt_telE = findViewById(R.id.edt_telE);
        edt_FIE = findViewById(R.id.edt_FIE);
        edt_FFE = findViewById(R.id.edt_FFE);
        btn_Borrar = findViewById(R.id.btn_Borrar);
        btn_Editar = findViewById(R.id.btn_Editar);
        btn_lmr = findViewById(R.id.btn_lmr);
        Bundle b = getIntent().getExtras();

        if(b!=null) {
           final String i = b.getString("NAME");
            edt_nameE.setText(i);

            final  String c = b.getString("OLD");
            edt_oldE.setText(c);

            final   String d = b.getString("TEL");
            edt_telE.setText(d);

            final  String e = b.getString("FI");
            edt_FIE.setText(e);

            final  String f = b.getString("FF");
            edt_FFE.setText(f);

        }
        final BenjaBD benjaBD = new BenjaBD(context);
        btn_Borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String g =edt_oldE.getText().toString().trim() ;
                String h =edt_nameE.getText().toString().trim() ;
                String bc =edt_telE.getText().toString().trim() ;

                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle("ALERTA DE BOLUDO QUERIENDO BORRAR ALGO :V ").setMessage("EEH ESTAS POR BORRAR UN AGENDADO LO QUERI BORRA O NO?" + "ORIGINAL:\n" +
                        "Apellido: " + g +"\n" +
                        "Nombre: " +h+"\n" +
                        "Telefono: "+bc).setPositiveButton("SI QUERI?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        benjaBD.eliminarAgenda(edt_nameE.getText().toString().trim());
                        Toast.makeText(EditAgenda.this, "YAS SE ELIMINO WEEY",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).setNegativeButton("TONSE NO QUERI?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "JEJEJEJ NO SE ELIMINO ",Toast.LENGTH_SHORT).show();
                        final Intent intent = new Intent(context, EditAgenda.class);
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();

                alertDialog.show();

            }
        });
        btn_Editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                benjaBD.editarAgenda(edt_nameE.getText().toString().trim(),edt_oldE.getText().toString().trim(),edt_telE.getText().toString().trim(),edt_FIE.getText().toString().trim(),edt_FFE.getText().toString().trim());

                Toast.makeText(EditAgenda.this, "PERFECTAMENTE EDITADO BLDOOOOO :)!",Toast.LENGTH_LONG).show();
                finish();
            }
        });
        btn_lmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bc =edt_telE.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+bc));
                startActivity(intent);
            }
        });
    }
}

package com.example.benjabd;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benjabd.HomeI;
import com.example.benjabd.R;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.benjabd.CrearCuenta.AdapterC;
import com.example.benjabd.CrearCuenta.ItemC;

import java.util.ArrayList;
import java.util.List;

public class CambiarUsuario extends AppCompatActivity {
Button btnR;
EditText txtUserR,txtPassR,txtCPassR;
Context context;
    private RecyclerView RV2;
    private AdapterC adapterC;
    List<ItemC> newlist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_usuario);

        btnR = findViewById(R.id.btnR);
        txtUserR = findViewById(R.id.txtUserR);
        txtPassR = findViewById(R.id.txtPassR);
        txtCPassR = findViewById(R.id.txtPassR);

context=this;

        Bundle b = getIntent().getExtras();

        RV2 = findViewById(R.id.RV2);
        RV2.setLayoutManager(new LinearLayoutManager(this));


        final BasedeDatos basedeDatos = new BasedeDatos(getApplicationContext());
        newlist= basedeDatos.verlist();
        adapterC = new AdapterC(newlist, context);

        RV2.setAdapter(adapterC);




        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtUserR.getText().toString().trim();
                String password = txtPassR.getText().toString().trim();
                String c_pwr = txtCPassR.getText().toString().trim();

                if (password.equals(c_pwr)){

                  long val=  basedeDatos.addUser(user,password);

                    if (val!=0){
                        Toast.makeText(CambiarUsuario.this, "YA TENII LA CUENTA NO TE OLVIDE LA CONTRA NOMAAAAA!!",Toast.LENGTH_SHORT).show();


                    }else {
                        Toast.makeText(CambiarUsuario.this, "COMO TE VAS A REGISTRAR SI NO INGRESASTE NADAAAAA CIEEGOOOO!!",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(CambiarUsuario.this, "LAS CONTRASEÑAS NO COINCIDEN DORII(pta que Memoria de corto plazo...)!!",Toast.LENGTH_SHORT).show();
                }



                newlist= basedeDatos.verlist();
                adapterC = new AdapterC(newlist, getApplicationContext());
                RV2.setAdapter(adapterC);

            }
        });


    }



}

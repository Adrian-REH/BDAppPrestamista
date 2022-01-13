package com.example.benjabd.Agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.benjabd.CambiarUsuario;
import com.example.benjabd.Fragment.AgendaFragment;
import com.example.benjabd.HomeI;
import com.example.benjabd.R;

public class Registro extends AppCompatActivity {
EditText edt_oldA,edt_nameA,edt_telA,edt_FIA,edt_FFA;
Button btn_Registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edt_oldA = findViewById(R.id.edt_oldA);
        edt_nameA = findViewById(R.id.edt_nameA);
        edt_telA = findViewById(R.id.edt_telA);
        edt_FIA = findViewById(R.id.edt_FIA);
        edt_FFA = findViewById(R.id.edt_FFA);
        btn_Registrar = findViewById(R.id.btn_Registrar);

        final BenjaBD benjaBD = new BenjaBD(getApplicationContext());
        btn_Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                benjaBD.agregarAgenda(edt_nameA.getText().toString().trim(),edt_oldA.getText().toString().trim(),edt_telA.getText().toString().trim(),edt_FIA.getText().toString().trim(),edt_FFA.getText().toString().trim());
                Toast.makeText(Registro.this, "YA TIENES A ALGUIEN SIGUE ASI!",Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }
}

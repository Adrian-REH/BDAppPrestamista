package com.example.benjabd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnL;
    EditText txtUserL,txtPassL;

    BasedeDatos db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



db = new BasedeDatos(this);

        btnL = findViewById(R.id.btnL);
        txtUserL = findViewById(R.id.txtUserL);
        txtPassL = findViewById(R.id.txtPassL);



btnL.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        String user = txtUserL.getText().toString().trim();
        String pwr = txtPassL.getText().toString().trim();


        if((user.equals("adrian")) && (pwr.equals("yoni"))){
            Toast.makeText(MainActivity.this, "YAAA INGRESASTE FELICIDADES(GIIL!) ",Toast.LENGTH_SHORT).show();


            Intent iraHome = new Intent(MainActivity.this, HomeI.class);
            startActivity(iraHome);




            }else {

            boolean res = db.checkUser(user,pwr);
            if  (res ==true){
                Toast.makeText(MainActivity.this, "YAAA INGRESASTE FELICIDADES(GIIL!)",Toast.LENGTH_SHORT).show();


                Intent iniciando = new Intent(MainActivity.this, HomeI.class);
                startActivity(iniciando);



        } else{
            Toast.makeText(MainActivity.this, "INGRESO EL USUARIO O CONTRASEÑA DE FORMA INCORRECTA VOLVE A ESCRIBIRLA BOLUDOOOO",Toast.LENGTH_SHORT).show();
        }
        }





    }
});

    }
}

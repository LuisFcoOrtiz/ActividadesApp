package com.example.root.actividadesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected String password, nombre;
    protected TextView userText, passwordText, userActiveText;
    protected Button button;
    protected Bundle datos;
    protected ArrayList<Miembros> miembrosArray;
    protected Miembros miembro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miembrosArray = new ArrayList<Miembros>();    //Array miembros
        userActiveText = (TextView) findViewById(R.id.userActiveText);  //TextView tos show user
        datos = getIntent().getExtras();        //get data from another activity
        createDefaultMiembro();                 //Default miembro
        if (datos!=null) {
            userActiveText.setText("El usuario es administrador");
        }//comprueba el administrador

    }//Fin on create

    public void createDefaultMiembro() {
        //Default miembro to entry
        miembro = new Miembros("admin","admin","19/05/92",true);
        miembrosArray.add(miembro);
    }//Miembro default to entry

    public void collectData(View v) {
        userText = (TextView) findViewById(R.id.userText);
        passwordText = (TextView) findViewById(R.id.passText);
        //get into variables
        nombre = userText.getText().toString();
        password = passwordText.getText().toString();
    }//Collect all the data

    public void loginUser(View v) {
        //to send
        Intent mandarAOtroActivity =new Intent(this,CollectDataFromActivity.class);
        mandarAOtroActivity.putExtra("USER_SENT",nombre);
        mandarAOtroActivity.putExtra("PASSWORD_SENT",password);
        startActivity(mandarAOtroActivity);
        //prueba

    }//send data

}//Fin clase principal

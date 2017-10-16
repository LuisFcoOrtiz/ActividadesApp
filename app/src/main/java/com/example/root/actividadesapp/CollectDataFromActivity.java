package com.example.root.actividadesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CollectDataFromActivity extends AppCompatActivity {

    protected Bundle datos;
    protected List<Miembros> miembrosArray;
    protected String user,password;
    protected int userArrayPossition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_data_from);

        TextView resultadoText = (TextView) findViewById(R.id.resultadoText);
        //miembrosArray = new ArrayList<Miembros>();
        try {
            getDatosRecieved();
        } catch (NullPointerException ex) {}
        if (userCorrect()) {
            if (adminOrUser()) {
                resultadoText.setText("usuario introducido correctamente (administrador)");
            } else {
                resultadoText.setText("usuario introducido correctamente (usuario)");
            }
        } else {
            resultadoText.setText("usuario introducido incorrecto");
        }

    }//end on create

    public void getDatosRecieved() {
        //if (datos!=null) {
            datos = getIntent().getExtras();
            //datosIntent = this.getIntent();
            if (datos!=null) {
                //collect miembros array
                miembrosArray = (ArrayList<Miembros>) getIntent().getSerializableExtra("ARRAY_MIEMBROS");
                //get variables
                user = datos.getString("USER_SENT").toString();
                password = datos.getString("PASSWORD_SENT").toString();
                //(String)datos.get("PASSWORD_SENT");
            }
        //}
    }//get data from intent

    public boolean userCorrect() {
        for(int i=0; i<miembrosArray.size();i++) {
            if (miembrosArray.get(i).getNombre().equals(user) && miembrosArray.get(i).getPassword().equals(password)) {
                userArrayPossition=i;
                return true;    //user correct
            }
        }//
        return false;
    }//Check user senteCheck if user is admin or user

    public boolean adminOrUser() {
        return miembrosArray.get(userArrayPossition).isTipoUsuario();
    }// True=Administrator | False=user

    public void getBackUser(boolean isCorrect) {
        Intent mandarAOtroActivity =new Intent(this,MainActivity.class);
        if (isCorrect) {
            mandarAOtroActivity.putExtra("USER_TYPE", adminOrUser());
            mandarAOtroActivity.putExtra("USER_NAME", miembrosArray.get(userArrayPossition).getNombre());
            mandarAOtroActivity.putExtra("ARRAY_MIEMBROS", (Serializable)miembrosArray);
            startActivity(mandarAOtroActivity);
        } else {
            startActivity(mandarAOtroActivity);
        }
    }//Get back to main activity

    public void pulseButton(View v) {
        getBackUser(userCorrect());
    }//action for button

}//end class

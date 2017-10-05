package com.example.root.actividadesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class CollectDataFromActivity extends AppCompatActivity {

    Bundle datos;
    boolean correcto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_data_from);

        datos = getIntent().getExtras();
        if (datos!=null) {
            if(datos.get("USER_SENT").equals("usu") && datos.get("PASSWORD_SENT").equals("1234") ) {
                correcto=true;

                Intent mandarAOtroActivity =new Intent(this,MainActivity.class);
                mandarAOtroActivity.putExtra("TIPO_USUARIO",correcto);
                startActivity(mandarAOtroActivity);

                Toast toast = Toast.makeText(getApplicationContext(), "El usuario es correcto", Toast.LENGTH_SHORT);
                toast.show();
            }
        }//comprueba los datos


    }//fin on create

}

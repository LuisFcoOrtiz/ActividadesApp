package com.example.root.actividadesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected String password, nombre;
    protected TextView userText, passwordText, userActiveText;
    protected Button button;
    protected Bundle datosBundle;
    protected List<Miembros> miembrosArray;
    protected Miembros miembro;
    protected Intent mandarAOtroActivity;
    protected Toolbar toolMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userActiveText = (TextView) findViewById(R.id.userActiveText);  //TextView tos show user
        //toolbar for options
        toolMenu = (Toolbar) findViewById(R.id.toolMenu);
        toolMenu.setTitle("Miembros");
        setSupportActionBar(toolMenu);
        //TO SEND
        miembrosArray = new ArrayList<Miembros>();
        //mandarAOtroActivity = new Intent(this,CollectDataFromActivity.class);   //To send data
        //To recieve
        datosBundle = getIntent().getExtras();      //get data from another activity
        //
        createDefaultMiembro();                 //Default miembro
        try {
            if (datosBundle != null) {
                userActiveText.setText("Usuario activo: " + datosBundle.getString("USER_NAME"));
                //miembrosArray = new ArrayList<Miembros>();
                miembrosArray = (ArrayList<Miembros>) getIntent().getSerializableExtra("ARRAY_MIEMBROS");
                if (datosBundle.getString("NEW_MEMBER_NAME") != null && datosBundle.getString("NEW_MEMBER_PASS") != null && datosBundle.get("NEW_MEMBER_TYPE") != null && datosBundle.getString("NEW_MEMBER_DATE") != null) {

                    miembro = new Miembros(datosBundle.getString("NEW_MEMBER_NAME").toString(),
                            datosBundle.getString("NEW_MEMBER_PASS").toString(),
                            datosBundle.getString("NEW_MEMBER_DATE").toString(),
                            (boolean) datosBundle.get("NEW_MEMBER_TYPE"));
                    miembrosArray.add(miembro);
                    Toast toast = Toast.makeText(getApplicationContext(),miembrosArray.size()+" usuarios en el sistema", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }//comprueba datos recividos
        } catch (NullPointerException ex) {}

    }//Fin on create

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tool_items, menu);
        return true;
    }//preparar menu

    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {
        menu.getItem(0).setEnabled(false);
        menu.getItem(1).setEnabled(false);
        menu.getItem(2).setEnabled(false);
        menu.getItem(3).setEnabled(false);
        try {
            if (datosBundle != null) {
                boolean userType = (boolean) datosBundle.get("USER_TYPE");
                if (userType == true) {
                    //administrator
                    menu.getItem(0).setEnabled(true);
                    menu.getItem(1).setEnabled(true);
                    menu.getItem(2).setEnabled(true);
                    menu.getItem(3).setEnabled(true);
                } else if (userType == false) {
                    //normal user
                    menu.getItem(0).setEnabled(false);
                    menu.getItem(1).setEnabled(false);
                    menu.getItem(2).setEnabled(true);   //Only can search
                    menu.getItem(3).setEnabled(false);
                }
            }//check bundle
        } catch (NullPointerException ex) {}
        return true;
    }//menu ya preparado

    public void createDefaultMiembro() {
        try {//Default miembro to entry
            if (miembrosArray.size()<=0) {
                miembrosArray = new ArrayList<Miembros>();
                miembro = new Miembros("admin", "admin", "19/05/92", true);
                miembrosArray.add(miembro);
            }

        } catch (IndexOutOfBoundsException ex) {}
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
        collectData(v); //Collect data from textview
        Intent mandarAOtroActivity = new Intent(this,CollectDataFromActivity.class);

        mandarAOtroActivity.putExtra("ARRAY_MIEMBROS", (Serializable)miembrosArray); //send array
        mandarAOtroActivity.putExtra("PASSWORD_SENT",password);
        mandarAOtroActivity.putExtra("USER_SENT",nombre);
        //defeault user

        startActivity(mandarAOtroActivity);
        //prueba

    }//send data

    /*events for ITEMS MENU TOOLBAR*/
    public void addNewMiembro(MenuItem item) {
        Intent mandarAOtroActivity =new Intent(this,AddNewMiembro.class);
        mandarAOtroActivity.putExtra("ARRAY_MIEMBROS", (Serializable)miembrosArray); //send array
        //send user and type user
        mandarAOtroActivity.putExtra("USER_NAME",datosBundle.getString("USER_NAME"));
        mandarAOtroActivity.putExtra("USER_TYPE", (boolean)datosBundle.get("USER_TYPE"));
        startActivity(mandarAOtroActivity);
    }//buton to add a member

    public void searchMember(MenuItem item) {
        Intent mandarAOtroActivity =new Intent(this,SearchMiembro.class);
        mandarAOtroActivity.putExtra("ARRAY_MIEMBROS", (Serializable)miembrosArray); //send array
        mandarAOtroActivity.putExtra("USER_NAME",datosBundle.getString("USER_NAME"));
        mandarAOtroActivity.putExtra("USER_TYPE", (boolean)datosBundle.get("USER_TYPE"));
        startActivity(mandarAOtroActivity);
    }

    public void dropMember(MenuItem item) {
        Intent mandarAOtroActivity =new Intent(this,DropMiembro.class);
        mandarAOtroActivity.putExtra("ARRAY_MIEMBROS", (Serializable)miembrosArray); //send array
        mandarAOtroActivity.putExtra("USER_NAME",datosBundle.getString("USER_NAME"));
        mandarAOtroActivity.putExtra("USER_TYPE", (boolean)datosBundle.get("USER_TYPE"));
        startActivity(mandarAOtroActivity);
    }//boton to drop member

    public void modifyMember(MenuItem item) {
        Intent mandarAOtroActivity =new Intent(this,ModifyMiembro.class);
        mandarAOtroActivity.putExtra("ARRAY_MIEMBROS", (Serializable)miembrosArray); //send array
        mandarAOtroActivity.putExtra("USER_NAME",datosBundle.getString("USER_NAME"));
        mandarAOtroActivity.putExtra("USER_TYPE", (boolean)datosBundle.get("USER_TYPE"));
        startActivity(mandarAOtroActivity);
    }//boton to modify member

}//Fin clase principal

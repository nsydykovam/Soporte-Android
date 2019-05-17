package com.example.jorgeantoniojuarezleyva.reportedeeventos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jorgeantoniojuarezleyva.reportedeeventos.ImpDAO.impUsuario;
import com.example.jorgeantoniojuarezleyva.reportedeeventos.Modelo.Usuario;
import com.example.jorgeantoniojuarezleyva.reportedeeventos.Modelo.sUsuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class InicioSesion extends AppCompatActivity {

    EditText txtUser;
    EditText txtPass;

    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPass = (EditText) findViewById(R.id.txtPass);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String user = txtUser.getText().toString();
                String pass = txtPass.getText().toString();
                impUsuario impUser = new impUsuario(InicioSesion.this);
                int tipo = impUser.iniciarSesion(user,pass);

                if (tipo == 1){
                    Intent int1 = new Intent(InicioSesion.this,verOperador.class);
                    startActivity(int1);

                }else if(tipo == 2){
                    Intent int2 = new Intent(InicioSesion.this,verInge.class);
                    startActivity(int2);
                }else if(tipo == 3){
                    Intent int3 = new Intent(InicioSesion.this,verGerente.class);
                    startActivity(int3);

                }else {
                    Toast.makeText(InicioSesion.this,"Usuario o Contraseña incorrectos",Toast.LENGTH_SHORT).show();
                }



            }
        });




    }
}



/*
* Ejemplo conexion que no funciona, pero almenos lo intente
* String server = "192.168.1.68:3306";
        String baseDatos = "JMDos";
        String usr = "root";
        String pswd = "n0m3l0";
//        String pswd = "Rusher";
        Connection con = null;
        Statement sta = null;
        ResultSet res = null;

        int id = 0;
        String datito = "";


        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://"+server+"/"+baseDatos, usr, pswd);
        } catch (Exception xD) {}

        try {
            sta = con.createStatement();
            res = sta.executeQuery("SELECT * FROM datitos; ");
            while (res.next())
            {
                id = res.getInt(1);
                datito = res.getString(2);
                Toast.makeText(InicioSesion.this,"AAAA:" + id + "WWW" + datito, Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e)
        {

        }
*
*
*
* */

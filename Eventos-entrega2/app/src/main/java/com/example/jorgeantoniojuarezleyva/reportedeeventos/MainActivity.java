package com.example.jorgeantoniojuarezleyva.reportedeeventos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jorgeantoniojuarezleyva.reportedeeventos.ImpDAO.impUsuario;
import com.example.jorgeantoniojuarezleyva.reportedeeventos.dao.UsuarioDao;
import com.example.jorgeantoniojuarezleyva.reportedeeventos.extras.Constante;

public class MainActivity extends AppCompatActivity {
    Button entrar;
    EditText etUsuario, etContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entrar = (Button) findViewById(R.id.entrar);
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etContrasena = (EditText) findViewById(R.id.etContrasena);
    }

    //metodo para ir a las recetas
    public void entrar(View v){
        UsuarioDao usuarioDao = new UsuarioDao();

        String usuario = etUsuario.getText().toString();
        String contrasena = etContrasena.getText().toString();

        Constante.usuarioActual = usuarioDao.acceso(this, usuario, contrasena);
        impUsuario impUser = new impUsuario(MainActivity.this);
        int tipo = impUser.iniciarSesion(usuario,contrasena);
        
        if(Constante.usuarioActual != null) { // Si el usuario con ese usuario y contraseña existe
            Intent i = new Intent(MainActivity.this, ListaReportes.class);
            startActivity(i);
        } else if (tipo == 1){
            Intent int1 = new Intent(MainActivity.this,verOperador.class);
            startActivity(int1);

        }else if(tipo == 2){
            Intent int2 = new Intent(MainActivity.this,verInge.class);
            startActivity(int2);
        }else if(tipo == 3){
            Intent int3 = new Intent(MainActivity.this,verGerente.class);
            startActivity(int3);

        }else {
            Toast.makeText(MainActivity.this,"Usuario o Contraseña incorrectos",Toast.LENGTH_SHORT).show();
        }
    }
}

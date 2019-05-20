package omniknow.soporte.reportemantenimiento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import omniknow.soporte.reportemantenimiento.dao.UsuarioDao;
import omniknow.soporte.reportemantenimiento.extras.Constante;

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

        if(Constante.usuarioActual != null) { // Si el usuario con ese usuario y contraseña existe
            Intent i = new Intent(MainActivity.this, ListaReportes.class);
            startActivity(i);
        } else {
            Toast.makeText(MainActivity.this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG).show();
        }
    }
}

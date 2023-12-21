package com.ariel.usofirebase;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button registrar,login;
    EditText email,contra;
    FirebaseAuth autenticacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registrar=findViewById(R.id.register);
        autenticacion = FirebaseAuth.getInstance();
        email=findViewById(R.id.emailEditText);
        contra=findViewById(R.id.passwordEditText);
        login=findViewById(R.id.loginButton);
        registrar.setOnClickListener(l->abrirRegister());
        login.setOnClickListener(l->login());


    }

    public void login(){
        if(email.getText().toString().isEmpty() || contra.getText().toString().isEmpty()){
            Toast.makeText(Login.this,"¡Aún hay campos por completar!",Toast.LENGTH_LONG).show();
        } else{
            autenticacion.signInWithEmailAndPassword(email.getText().toString(),contra.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"¡Login exitoso!", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                i.putExtra("correo", email.getText().toString());
                                email.setText("");
                                contra.setText("");
                                startActivity(i);

                                //finish();
                            } else{
                                Toast.makeText(Login.this,"¡Usuario no valido!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        }
    }
    public void abrirRegister(){
        Intent intent = new Intent(this, Registrar.class);
        startActivity(intent);
        email.setText("");
        contra.setText("");


    }

}



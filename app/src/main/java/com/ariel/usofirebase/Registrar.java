package com.ariel.usofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class Registrar extends AppCompatActivity {
    Button login,regreso;
    EditText email,contra;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        mAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.emailEditText);
        contra=findViewById(R.id.passwordEditText);
        login=findViewById(R.id.loginButton);
        regreso=findViewById(R.id.regresar);
        regreso.setOnClickListener(l->retroceder());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo=email.getText().toString().trim();
                String contrase=contra.getText().toString().trim();

                if (correo.isEmpty()&&contrase.isEmpty()){
                    Toast.makeText(Registrar.this,"Llene los campor",Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(correo, contrase)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    email.setText("");
                                    contra.setText("");
                                    Toast.makeText(Registrar.this, "Cuenta creada.",
                                            Toast.LENGTH_SHORT).show();
                                     retroceder();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Registrar.this, "Creacion fallida.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });



    }
    public void retroceder() {
        email.setText("");
        contra.setText("");
        super.onBackPressed();

    }



}
package com.example.robots;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


import com.example.robots.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;


public class MainActivity extends AppCompatActivity {
    Button btnSignIn,btnRegister;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    FirebaseAuth.AuthStateListener authListener;
    RelativeLayout root;
    Animation slide_upp, slide_downn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnSignIn=findViewById(R.id.btnSingIn);
        btnRegister=findViewById(R.id.btnRegister);
        root=findViewById(R.id.root_element);



        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        if (auth.getCurrentUser() != null) {
            // User is signed in (getCurrentUser() will be null if not signed in)
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
            finish();
        }


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.slide_up);
                showRegisterWindow();

            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.slide_up);
                showSignWindow();

            }
        });
    }
    private  void showSignWindow(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("Login");

        LayoutInflater inflater=LayoutInflater.from(this);
        View sign_in_window = inflater.inflate(R.layout.login,null);
        dialog.setView(sign_in_window);

        final MaterialEditText email= sign_in_window.findViewById(R.id.emailField);
        final MaterialEditText pass= sign_in_window.findViewById(R.id.passField);

        //ОТМЕНА РЕГИСТРАЦИИ
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        //<ОТМЕНА РЕГИСТРАЦИИ
        //РЕГИСТРАЦИЯ
        dialog.setPositiveButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if(TextUtils.isEmpty(email.getText().toString()))
                {
                    Snackbar.make(root,"Write your Email!",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(pass.getText().toString()))
                {
                    Snackbar.make(root,"Write your password!",Snackbar.LENGTH_LONG).show();
                    return;
                }
                auth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                startActivity(new Intent(MainActivity.this,IntroActivity.class));
                                finish();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(root,"Error, please check the form",Snackbar.LENGTH_SHORT).show();

                    }
                });
            }
        });


        dialog.show();

    }



    private void showRegisterWindow() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("Registration");

        LayoutInflater inflater=LayoutInflater.from(this);
        View register_window = inflater.inflate(R.layout.register,null);
        dialog.setView(register_window);

        final MaterialEditText email= register_window.findViewById(R.id.emailField);
        final MaterialEditText number= register_window.findViewById(R.id.numberField);
        final MaterialEditText name= register_window.findViewById(R.id.nameField);
        final MaterialEditText pass= register_window.findViewById(R.id.passField);

        //ОТМЕНА РЕГИСТРАЦИИ
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        //<ОТМЕНА РЕГИСТРАЦИИ
        //РЕГИСТРАЦИЯ
        dialog.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if(TextUtils.isEmpty(email.getText().toString()))
                {
                    Snackbar.make(root,"Write you Email!",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(pass.getText().toString()))
                {
                    Snackbar.make(root,"Write your password!",Snackbar.LENGTH_LONG).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                User user = new User();
                                user.setEmail(email.getText().toString());
                                user.setName(name.getText().toString());
                                user.setPaa(pass.getText().toString());
                                user.setNumber(number.getText().toString());

                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Snackbar.make(root,"Successful. Your account created!", Snackbar.LENGTH_SHORT).show();


                                            }
                                        });


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(root,"Person with this Email already present.",Snackbar.LENGTH_SHORT).show();
                    }
                });

            }
        });
        //<РЕГИСТРАЦИЯ
        dialog.show();


    }


}
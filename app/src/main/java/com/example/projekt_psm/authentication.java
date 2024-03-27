package com.example.projekt_psm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.projekt_psm.PostgreSQLJDBC;
import com.example.projekt_psm.R;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class authentication extends AppCompatActivity {

    EditText email;
    EditText username;
    EditText password_From_Activity;
    TextView sprawdzenie;
    Button registerButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        email = findViewById(R.id.Email);
        username = findViewById(R.id.username);
        password_From_Activity = findViewById(R.id.password_From_Activity);
        registerButton = findViewById(R.id.registerButton);
        sprawdzenie = findViewById(R.id.Sprawdzenie);

    }
    PostgreSQLJDBC con = new PostgreSQLJDBC("","");
    public void registerFun(View view) throws SQLException {

        if(con.makeConnection() != null){
            String uN, pass, em;
            if (!email.getText().toString().isEmpty() && !username.getText().toString().isEmpty() && !password_From_Activity.getText().toString().isEmpty()) {
                try {
                    // Pobranie danych z pól formularza
                    uN = username.getText().toString();
                    pass = password_From_Activity.getText().toString();
                    em = email.getText().toString();

                    // Tworzenie zapytania SQL
                    String query="INSERT INTO Projekt_PSM.Users (login, password, email) VALUES (?, ?, ?)";

                    // Wykonanie zapytania przy użyciu PreparedStatement
                    try (PreparedStatement statement = con.getConnection().prepareStatement(query)) {
                        statement.setString(1, uN);
                        statement.setString(2, pass);
                        statement.setString(3, em);

                        int rowsInserted = statement.executeUpdate();
                        if (rowsInserted > 0) {
                            // Sukces - użytkownik dodany
                            sprawdzenie.setVisibility(View.VISIBLE);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    // Zamknięcie połączenia z bazą danych
                    con.close();
                }
            }
        }else{
            Log.d("con=null","wyjebaloooooooo");
        }
    }

}






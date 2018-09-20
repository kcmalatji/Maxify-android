package com.ransoft.maxify;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    ArrayList<User> user_profile=new ArrayList<>();

    private Connection connect=null;
    private Statement statement = null;
    private ResultSet resultSet=null;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        Button btn = (Button)findViewById(R.id.email_sign_in_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
//                    readDataBase();
                    startActivity(new Intent(LoginActivity.this, MapsActivity.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



    }

    public void readDataBase(int phone_number) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        connect=DriverManager.getConnection("jdbc:mysql://164.160.91.27:3306/ransoftc_maxify?autoReconnect=true&useSSL=false&"+"user=ransoftc_ransoft&password=Builditfromscratch@2018");
        statement= connect.createStatement();
        resultSet=statement.executeQuery("SELECT id FROM ransoftc_maxify.pasenger WHERE email='"+phone_number +"'");

        getid(resultSet);

    }
    public String getid(ResultSet resultSet)throws SQLException {

        while (resultSet.next()) {
            User user = new User();
            userid = resultSet.getString("id");
        }
        System.out.println(userid);
        return userid;

    }

}


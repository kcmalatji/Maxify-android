package com.ransoft.maxify;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.StrictMode;
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
import android.text.Editable;
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
//
//    ArrayList<User> user_profile=new ArrayList<>();
//
//    private Connection connect=null;
//    private Statement statement = null;
//    private ResultSet resultSet=null;
//    String userid;
    EditText mobile_number;
    Connection con;
    String un,pass,db,ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        Button btn = (Button)findViewById(R.id.email_sign_in_button);
        mobile_number= (EditText)findViewById(R.id.email);
        final String me= mobile_number.getText().toString();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                  readDataBase(me);
                    startActivity(new Intent(LoginActivity.this, MapsActivity.class));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        }

    public String readDataBase(String phone_number) throws Exception{

ip="164.160.91.27";
un="ransoftc_ransoft";
db="ransoftc_maxify";
pass="Builditfromscratch@2018";
con = connectionClass(un,pass,db,ip);
String results="haven't got connections";
try{
    con = connectionClass(un,pass,db,ip);
    if(con==null){
        results="Check your internet access";
        System.out.println(results);
    }else{

        String query="SELECT * FROM pasenger";
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery(query);

        while(rs.next()){
            System.out.println(rs.getString(3));
        }
    }
}catch(Exception ex){
    System.out.println(ex);
}
return results;
    }


    public Connection connectionClass(String username,String Password, String database, String server){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection=null;
        String ConnectionURL=null;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //ConnectionURL="jdbc:jtds:sqlserver://"+server+database+";user="+username+";password="+Password;
            ConnectionURL = "jdbc:jtds:sqlserver://" + server + ";"
                    + "databaseName=" + database + ";user=" + username
                    + ";password=" + Password + ";" + "ssl=false";
            connection=DriverManager.getConnection(ConnectionURL);

        }catch (Exception ex){

            System.out.println("error message is :"+ex);
        }

        return connection;
    }
//    public String getid(ResultSet resultSet)throws SQLException {
//
//        while (resultSet.next()) {
//            User user = new User();
//            userid = resultSet.getString("id");
//        }
//        System.out.println(userid);
//        return userid;
//
//    }

}


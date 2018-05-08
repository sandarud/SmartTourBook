package com.example.sandarudaminda.smarttourbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    LoginButton LoginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        LoginButton=(LoginButton)findViewById(R.id.login_button);
        LoginButton.setReadPermissions("email","public_profile");

        callbackManager= CallbackManager.Factory.create();
        LoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String userid=loginResult.getAccessToken().getUserId();

                GraphRequest graphRequest=GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response)
                    {
                        displayuserinfo(object);

                    }
                });

                Bundle parameters=new Bundle();
                parameters.putString("fields","first_name,last_name,email,id");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();

                Toast toast = Toast.makeText(Login.this, "test", Toast.LENGTH_SHORT);
                toast.show();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });




    }

    public void displayuserinfo(JSONObject object)
    {
        String first_name,last_name,email,id;

        first_name="";
        last_name="";
        email="";
        id="";


        try
        {
            first_name=object.getString("first_name");
            last_name=object.getString("last_name");
            email=object.getString("email");
            id=object.getString("id");

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        TextView tvname,tvemail,tvid;


        tvname=(TextView)findViewById(R.id.TVname);
        tvemail=(TextView)findViewById(R.id.TVemail);
        tvid=(TextView)findViewById(R.id.TVid);

        tvname.setText(first_name+" "+last_name);
        tvemail.setText("E mail :"+ email);
        tvid.setText("ID :"+id);

    }

    ImageButton onNext;
    public void init(View view)
    {
        onNext=(ImageButton)findViewById(R.id.onNext);
        onNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ss=new Intent(Login.this,MainActivity.class);
                startActivity(ss);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }


}

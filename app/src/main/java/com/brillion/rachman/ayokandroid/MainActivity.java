package com.brillion.rachman.ayokandroid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginFormActivityListener, WelcomeFragment.OnLogoutListener {

    public static PrefConfig prefConfig;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefConfig = new PrefConfig(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        if(findViewById(R.id.fragment_container)!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }

            if(prefConfig.readLoginStatus())
            {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new WelcomeFragment()).commit();
            }
            else
            {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new LoginFragment()).commit();
            }
        }
    }

    @Override
    public void performRegister() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new RegistrationFragment()).addToBackStack(null).commit();
    }

    @Override
    public void performLogin(String email)
    {
        prefConfig.writeEmail(email);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new WelcomeFragment()).commit();
    }

    @Override
    public void logoutPerformed() {
        prefConfig.writeLoginStatus(false);
        prefConfig.writeEmail("User");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LoginFragment()).commit();
    }
}

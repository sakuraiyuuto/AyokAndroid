package com.brillion.rachman.ayokandroid;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    private TextView RegText;
    private EditText UserEmail,UserPassword;
    private Button LoginBn;

    OnLoginFormActivityListener loginFormActivityListener;

    public interface OnLoginFormActivityListener
    {
        public void performRegister();
        public void performLogin(String email);
    }


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        RegText = view.findViewById(R.id.register_txt);
        UserEmail = view.findViewById(R.id.user_email);
        UserPassword = view.findViewById(R.id.user_pass);
        LoginBn = view.findViewById(R.id.login_bn);

        LoginBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        RegText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFormActivityListener.performRegister();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        loginFormActivityListener = (OnLoginFormActivityListener) activity;

    }

    private void performLogin()
    {
        String email = UserEmail.getText().toString();
        String password = UserPassword.getText().toString();

        Call<User> call = MainActivity.apiInterface.performUserLogin(email,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok"))
                {
                    MainActivity.prefConfig.writeLoginStatus(true);
                    loginFormActivityListener.performLogin(response.body().getEmail());
                }
                else if(response.body().getResponse().equals("failed"))
                {
                    MainActivity.prefConfig.displayToast("Login Failed, Please Try Again Later");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        UserEmail.setText("");
        UserPassword.setText("");
    }
}

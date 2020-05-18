package com.brillion.rachman.ayokandroid;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {


    private EditText UserName,UserEmail,UserPhoneNumber,UserPassword,UserPasswordSecond;
    private Button BnRegister;
    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        UserName = view.findViewById(R.id.txt_name);
        UserEmail = view.findViewById(R.id.txt_email);
        UserPhoneNumber = view.findViewById(R.id.txt_phone_number);
        UserPassword = view.findViewById(R.id.txt_password);
        UserPasswordSecond = view.findViewById(R.id.txt_password_second);
        BnRegister = view.findViewById(R.id.bn_register);

        BnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();
            }
        });
        return view;
    }

    public void performRegistration()
    {
        String name = UserName.getText().toString();
        String email = UserEmail.getText().toString();
        String phonenumber = UserPhoneNumber.getText().toString();
        String password = UserPassword.getText().toString();

        Call<User> call = MainActivity.apiInterface.performRegistration(name,email,phonenumber,password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.body().getResponse().equals("ok"))
                {
                    MainActivity.prefConfig.displayToast("Registration Success");
                }
                else if(response.body().getResponse().equals("exist"))
                {
                    MainActivity.prefConfig.displayToast("User Already Exist");
                }
                else if(response.body().getResponse().equals("error"))
                {
                    MainActivity.prefConfig.displayToast("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        UserName.setText("");
        UserEmail.setText("");
        UserPhoneNumber.setText("");
        UserPassword.setText("");
        UserPasswordSecond.setText("");
    }

}

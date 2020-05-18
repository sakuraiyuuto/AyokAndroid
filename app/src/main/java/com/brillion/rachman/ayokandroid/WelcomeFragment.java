package com.brillion.rachman.ayokandroid;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {
    private TextView textView;
    private Button BnLogOut;
    OnLogoutListener logoutListener;
    public interface OnLogoutListener
    {
        public void logoutPerformed();
    }
    public WelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        textView = view.findViewById(R.id.txt_email_info);
        textView.setText("Welcome "+MainActivity.prefConfig.readEmail());
        BnLogOut = view.findViewById(R.id.bn_logout);

        BnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutListener.logoutPerformed();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        logoutListener = (OnLogoutListener) activity;
    }
}

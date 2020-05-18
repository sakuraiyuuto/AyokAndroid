package com.brillion.rachman.ayokandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class PrefConfig
{
    private SharedPreferences sharedPreferences;
    private Context context;

    //Construktor Kelas
    public PrefConfig(Context context)
    {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file),Context.MODE_PRIVATE);
    }

    //Write status login
    public void writeLoginStatus(boolean status)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status),status);
        editor.commit();
    }

    //Read status login
    public boolean readLoginStatus()
    {
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status),false);
    }

    //Menyimpan email setelah login
    public void writeEmail(String email)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_email),email);
        editor.commit();
    }

    //Membaca email setelah login yang tersimpan
    public String readEmail()
    {
        return sharedPreferences.getString(context.getString(R.string.pref_user_email),"User");
    }

    public void displayToast(String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}

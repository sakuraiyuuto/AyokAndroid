package com.brillion.rachman.ayokandroid;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface
{
    @GET("register.php")
    Call<User> performRegistration(@Query("user_name") String UserName,@Query("user_email") String UserEmail, @Query("user_phone_number") String UserPhoneNumber,@Query("user_password") String UserPassword);

    @GET("login.php")
    Call<User> performUserLogin(@Query("user_email") String UserEmail, @Query("user_password") String UserPassword);

}

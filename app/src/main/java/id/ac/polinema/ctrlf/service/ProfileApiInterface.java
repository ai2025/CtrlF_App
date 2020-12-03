package id.ac.polinema.ctrlf.service;

import java.util.HashMap;

import id.ac.polinema.ctrlf.model.Auth.AddCaloriesReq;
import id.ac.polinema.ctrlf.model.Auth.LoginReq;
import id.ac.polinema.ctrlf.model.Auth.ProfileResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProfileApiInterface {
    @POST("/auth/login")
    Call<ProfileResponse> doLogin(@Body LoginReq loginReq);

    @POST("/auth/register")
    Call<ProfileResponse> doRegister(@Body ProfileResponse registerReq);

    @POST("/profile")
    Call<ProfileResponse> doGetProfile(@Body HashMap<String, String> user);

    @POST("/profile/add_calories")
    Call<ProfileResponse> doAddCalories(@Body AddCaloriesReq addCaloriesReq);
}

package id.ac.polinema.ctrlf.service;

import java.util.List;
import java.util.Map;

import id.ac.polinema.ctrlf.model.Recipe;
import id.ac.polinema.ctrlf.model.ResponseRecipe;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @GET("/search")
    Call<ResponseRecipe> getRecipes(@QueryMap Map<String, String> content);

    @GET("/search")
    Call<List<Recipe>> getDetRecipes(@QueryMap Map<String, String> content);

//    @GET("/search")
//    Call<ResponseDetail> getDetRec(@QueryMap Map<String, String> content);
}

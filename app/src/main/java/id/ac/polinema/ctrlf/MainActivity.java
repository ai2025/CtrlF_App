package id.ac.polinema.ctrlf;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import id.ac.polinema.ctrlf.adapter.ListResepAdapter;
import id.ac.polinema.ctrlf.helper.ServiceGeneratorResep;
import id.ac.polinema.ctrlf.model.Recipe;
import id.ac.polinema.ctrlf.model.ResponseRecipe;
import id.ac.polinema.ctrlf.service.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String APP_ID = "325caf67";
    private static final String APP_KEY = "6b700ecdf15d251870d4460eff2b7716";
    private static final String DIET = "low-fat";
    private static final String HEALTH = "alcohol-free";
    private static final String CAL_RANGE = "100-300";

    String q = "";

    ArrayList<Recipe> recipes;
    ListResepAdapter adp;
    String nama, foto;
    double kalori;

    EditText edtSearch;
    ProgressBar loading;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSearch = findViewById(R.id.edtSearchRecipe);
        loading = findViewById(R.id.loading);

        recipes = new ArrayList<>();

        rv = findViewById(R.id.rv_resep);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        adp = new ListResepAdapter(recipes);
        rv.setAdapter(adp);

        loading.setVisibility(View.VISIBLE);

        edtSearch.setText("");

        getRecipeData("soup");

//        RecyclerView rv = findViewById(R.id.rv_resep);
//
//        List<Resep> resepList = new ArrayList<>();
//        resepList.add(new Resep("https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Liverpool_FC.svg/360px-Liverpool_FC.svg.png", "Burger banyak kalorinyaa", "1234"));
//        resepList.add(new Resep("https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Liverpool_FC.svg/360px-Liverpool_FC.svg.png", "Burger banyak kalorinyaa", "1234"));
//        resepList.add(new Resep("https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Liverpool_FC.svg/360px-Liverpool_FC.svg.png", "Burger banyak kalorinyaa", "1234"));
//        resepList.add(new Resep("https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Liverpool_FC.svg/360px-Liverpool_FC.svg.png", "Burger banyak kalorinyaa", "1234"));
//
//        ListResepAdapter adp = new ListResepAdapter(this, resepList);
//        rv.setAdapter(adp);
//
//        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
//        rv.setLayoutManager(lm);
    }

    private void getRecipeData(String tq) {
        if (!tq.isEmpty()) {
            q = tq;
        }
        Map<String, String> data = new HashMap<>();
        data.put("app_id", APP_ID);
        data.put("app_key", APP_KEY);
        data.put("q", q);
        data.put("diet", DIET);
        data.put("health", HEALTH);
        data.put("calories", CAL_RANGE);

        ApiInterface service = ServiceGeneratorResep.createService(ApiInterface.class);
//        Call<ResponseRecipe>
        Call<ResponseRecipe> call = service.getRecipes(data);
        call.enqueue(new Callback<ResponseRecipe>() {
            @Override
            public void onResponse(Call<ResponseRecipe> call, Response<ResponseRecipe> response) {
                if (response.isSuccessful()) {
                    int a = 0;
                    //                        ArrayList<Hit> hits = (ArrayList<Hit>) response.body().getHits();
//                        ResponseRecipe
                    for (int i = 0; i < response.body().getHits().size(); i++) {
                        nama = response.body().getHits().get(i).getRecipe().getLabel();
                        kalori = response.body().getHits().get(i).getRecipe().getCalories();
                        foto = response.body().getHits().get(i).getRecipe().getImage();
                        a = (int) Math.round(kalori);
                        recipes.add(new Recipe(nama, a, foto));
                        adp.notifyDataSetChanged();
                    }

                    final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Notification")
                            .setMessage("Your data have been succesfully loaded")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    alert.setCancelable(true);
                                    loading.setVisibility(View.GONE);
                                }
                            })
                            .setCancelable(false);
                    alert.show();
                }
            }

            @Override
            public void onFailure(Call<ResponseRecipe> call, Throwable t) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Notification")
                        .setMessage("Check your internet connection!")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alert.setCancelable(true);
                            }
                        })
                        .setCancelable(false);
                alert.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_act, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tbProfile:
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(i);
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }

    public void onSearch(View view) {
        String tq = edtSearch.getText().toString();
        recipes.clear();
        getRecipeData(tq);
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menus, menu);
//    }
}

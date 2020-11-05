package id.ac.polinema.ctrlf;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.ac.polinema.ctrlf.helper.ServiceGeneratorResep;
import id.ac.polinema.ctrlf.model.Recipe;
import id.ac.polinema.ctrlf.model.TotalNutrients;
import id.ac.polinema.ctrlf.service.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailResepActivity extends AppCompatActivity {

    private static final String APP_ID = "325caf67";
    private static final String APP_KEY = "6b700ecdf15d251870d4460eff2b7716";
    //    Recipe r;
    String url;
    ProgressBar loadingDetail;
    TextView tvNamaDetResep, tvKaloriDetResep, tvNutrisiDetResep, tvBahanDetResep;
    ImageView ivFoto;
    Button btnCook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_resep);

        loadingDetail = findViewById(R.id.loadingDetail);
        tvNamaDetResep = findViewById(R.id.tvNamaDetResep);
        tvKaloriDetResep = findViewById(R.id.tvKaloriDetResep);
        tvNutrisiDetResep = findViewById(R.id.tvNutrisiDetResep);
        tvBahanDetResep = findViewById(R.id.tvBahanDetResep);
        ivFoto = findViewById(R.id.ivFotoDetResep);
        btnCook = findViewById(R.id.btnLinkInstruksi);

        tvNamaDetResep.setText("");
        tvKaloriDetResep.setText("");
        tvNutrisiDetResep.setText("");
        tvBahanDetResep.setText("");
        url = "";

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeButtonEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
//            ab.setTitle("Pancake enyaaakk");
        }

//        Intent i = getIntent();
//        Recipe recipe = (Recipe) getIntent().getSerializableExtra("urii");
        String recipe = (String) getIntent().getSerializableExtra("urii");
//        r = recipe;
        getRecipeDetails(recipe);
    }

    private void getRecipeDetails(String rr) {
//        String rt = String.valueOf(rr);
        Map<String, String> data = new HashMap<>();
        data.put("app_id", APP_ID);
        data.put("app_key", APP_KEY);
        data.put("r", rr);

        ApiInterface service = ServiceGeneratorResep.createService(ApiInterface.class);
        Call<List<Recipe>> call = service.getDetRecipes(data);
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (response.isSuccessful()) {
                    Recipe re = response.body().get(0);
                    url = re.getUrl();
                    tvNamaDetResep.setText(re.getLabel());
                    tvKaloriDetResep.setText(String.valueOf(Math.round(re.getCalories())));
                    Picasso.get().load(re.getImage()).into(ivFoto);

                    String nutrition, energy, fat, carbs, fiber, sugar, protein, cholesterol, calcium, water;
                    //                        ArrayList<Hit> hits = (ArrayList<Hit>) response.body().getHits();
//                        ResponseRecipe
                    TotalNutrients tn = re.getTotalNutrients();
                    energy = tn.getENERCKCAL().getLabel() + ": "
                            + Math.round(tn.getENERCKCAL().getQuantity())
                            + tn.getENERCKCAL().getUnit();
                    fat = tn.getFAT().getLabel() + ": "
                            + Math.round(tn.getFAT().getQuantity())
                            + tn.getFAT().getUnit();
                    carbs = tn.getCHOCDF().getLabel() + ": "
                            + Math.round(tn.getCHOCDF().getQuantity())
                            + tn.getCHOCDF().getUnit();
                    fiber = tn.getFIBTG().getLabel() + ": "
                            + Math.round(tn.getFIBTG().getQuantity())
                            + tn.getFIBTG().getUnit();
                    sugar = tn.getSUGAR().getLabel() + ": "
                            + Math.round(tn.getSUGAR().getQuantity())
                            + tn.getSUGAR().getUnit();
                    protein = tn.getPROCNT().getLabel() + ": "
                            + Math.round(tn.getPROCNT().getQuantity())
                            + tn.getPROCNT().getUnit();
                    cholesterol = tn.getCHOLE().getLabel() + ": "
                            + Math.round(tn.getCHOLE().getQuantity())
                            + tn.getCHOLE().getUnit();
                    calcium = tn.getCA().getLabel() + ": "
                            + Math.round(tn.getCA().getQuantity())
                            + tn.getCA().getUnit();
                    water = tn.getWATER().getLabel() + ": "
                            + Math.round(tn.getWATER().getQuantity())
                            + tn.getWATER().getUnit();

                    nutrition = "- " + energy + "\n"
                            + "- " + fat + "\n"
                            + "- " + carbs + "\n"
                            + "- " + fiber + "\n"
                            + "- " + sugar + "\n"
                            + "- " + protein + "\n"
                            + "- " + cholesterol + "\n"
                            + "- " + calcium + "\n"
                            + "- " + water + "\n";
                    tvNutrisiDetResep.setText(nutrition);

                    StringBuilder temp = new StringBuilder();
                    for (int i = 0; i < re.getIngredientLines().size(); i++) {
                        temp.append("- ").append(re.getIngredientLines().get(i)).append("\n");
                    }
                    tvBahanDetResep.setText(temp);

                    final AlertDialog.Builder alert = new AlertDialog.Builder(DetailResepActivity.this);
                    alert.setTitle("Notification")
                            .setMessage("Your data have been succesfully loaded")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    alert.setCancelable(true);
                                    loadingDetail.setVisibility(View.GONE);
                                }
                            })
                            .setCancelable(false);
                    alert.show();

                    btnCook.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Uri uri = Uri.parse(url);
                            Intent in = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(in);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                t.getMessage();
                System.out.println("Error");
                Log.d("retrofit", "error", t);
//                final AlertDialog.Builder alert = new AlertDialog.Builder(DetailResepActivity.this);
//                alert.setTitle("Notification")
//                        .setMessage("Check your internet connection!")
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                alert.setCancelable(true);
//                                loadingDetail.setVisibility(View.GONE);
//                            }
//                        })
//                        .setCancelable(false);
//                alert.show();
            }
        });
    }
}

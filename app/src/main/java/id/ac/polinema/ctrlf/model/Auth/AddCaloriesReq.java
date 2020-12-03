package id.ac.polinema.ctrlf.model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddCaloriesReq {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("nama_makanan")
    @Expose
    private String namaMakanan;
    @SerializedName("kalori_makanan")
    @Expose
    private Integer kaloriMakanan;
    @SerializedName("link")
    @Expose
    private String link;

    public AddCaloriesReq(String email, String namaMakanan, Integer kaloriMakanan, String link) {
        this.email = email;
        this.namaMakanan = namaMakanan;
        this.kaloriMakanan = kaloriMakanan;
        this.link = link;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
    }

    public Integer getKaloriMakanan() {
        return kaloriMakanan;
    }

    public void setKaloriMakanan(Integer kaloriMakanan) {
        this.kaloriMakanan = kaloriMakanan;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

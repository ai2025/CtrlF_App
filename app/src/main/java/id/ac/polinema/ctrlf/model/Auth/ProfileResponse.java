package id.ac.polinema.ctrlf.model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileResponse {
    @SerializedName("nama_lengkap")
    @Expose
    private String nama_lengkap;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String pass;
    @SerializedName("umur")
    @Expose
    private Integer umur;
    @SerializedName("tinggi_badan")
    @Expose
    private Integer tinggiBadan;
    @SerializedName("berat_badan")
    @Expose
    private double beratBadan;
    @SerializedName("kalori_user")
    @Expose
    private double kaloriUser;
    @SerializedName("aktifitas_harian")
    @Expose
    private String activity;
    @SerializedName("aktivitas_harian")
    @Expose
    private String aktivitasHarian;
    @SerializedName("kalori_makanan")
    @Expose
    private List<KaloriMakanan> kaloriMakanan = null;

    public ProfileResponse(String nama_lengkap, String email, String pass, Integer umur,
                           Integer tinggiBadan, double beratBadan, double kaloriUser, String aktivitasHarian) {
        this.nama_lengkap = nama_lengkap;
        this.email = email;
        this.pass = pass;
        this.umur = umur;
        this.tinggiBadan = tinggiBadan;
        this.beratBadan = beratBadan;
        this.kaloriUser = kaloriUser;
        this.aktivitasHarian = aktivitasHarian;
    }

    public String getAktivitasHarian() {
        return activity;
    }

    public void setAktivitasHarian(String aktivitasHarian) {
        this.aktivitasHarian = aktivitasHarian;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUmur() {
        return umur;
    }

    public void setUmur(Integer umur) {
        this.umur = umur;
    }

    public Integer getTinggiBadan() {
        return tinggiBadan;
    }

    public void setTinggiBadan(Integer tinggiBadan) {
        this.tinggiBadan = tinggiBadan;
    }

    public double getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(Integer beratBadan) {
        this.beratBadan = beratBadan;
    }

    public double getKaloriUser() {
        return kaloriUser;
    }

    public void setKaloriUser(double kaloriUser) {
        this.kaloriUser = kaloriUser;
    }

    public List<KaloriMakanan> getKaloriMakanan() {
        return kaloriMakanan;
    }

    public void setKaloriMakanan(List<KaloriMakanan> kaloriMakanan) {
        this.kaloriMakanan = kaloriMakanan;
    }
}

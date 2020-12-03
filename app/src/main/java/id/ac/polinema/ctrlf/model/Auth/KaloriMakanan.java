package id.ac.polinema.ctrlf.model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KaloriMakanan {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("nama_makanan")
    @Expose
    private String namaMakanan;
    @SerializedName("kalori_makanan")
    @Expose
    private Integer kaloriMakanan;
    @SerializedName("link")
    @Expose
    private String link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
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

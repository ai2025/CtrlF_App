package id.ac.polinema.ctrlf.model;

public class Resep {
    public String imgResep;
    public String namaResep;
    public String kaloriResep;
    public String bahanResep;
    public String langkahResep;

    public Resep(String imgResep, String namaResep, String kaloriResep) {
        this.imgResep = imgResep;
        this.namaResep = namaResep;
        this.kaloriResep = kaloriResep;
    }

    public String getImgResep() {
        return imgResep;
    }

    public void setImgResep(String imgResep) {
        this.imgResep = imgResep;
    }

    public String getNamaResep() {
        return namaResep;
    }

    public void setNamaResep(String namaResep) {
        this.namaResep = namaResep;
    }

    public String getKaloriResep() {
        return kaloriResep;
    }

    public void setKaloriResep(String kaloriResep) {
        this.kaloriResep = kaloriResep;
    }
}

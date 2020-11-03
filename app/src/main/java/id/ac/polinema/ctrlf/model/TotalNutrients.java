package id.ac.polinema.ctrlf.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalNutrients {

    @SerializedName("ENERC_KCAL")
    @Expose
    private ENERCKCAL eNERCKCAL;
    @SerializedName("FAT")
    @Expose
    private FAT fAT;
    @SerializedName("CHOCDF")
    @Expose
    private CHOCDF cHOCDF;
    @SerializedName("FIBTG")
    @Expose
    private FIBTG fIBTG;
    @SerializedName("SUGAR")
    @Expose
    private SUGAR sUGAR;
    @SerializedName("PROCNT")
    @Expose
    private PROCNT pROCNT;
    @SerializedName("CHOLE")
    @Expose
    private CHOLE cHOLE;
    @SerializedName("CA")
    @Expose
    private CA cA;
    @SerializedName("WATER")
    @Expose
    private WATER wATER;

    public ENERCKCAL getENERCKCAL() {
        return eNERCKCAL;
    }

    public void setENERCKCAL(ENERCKCAL eNERCKCAL) {
        this.eNERCKCAL = eNERCKCAL;
    }

    public FAT getFAT() {
        return fAT;
    }

    public void setFAT(FAT fAT) {
        this.fAT = fAT;
    }

    public CHOCDF getCHOCDF() {
        return cHOCDF;
    }

    public void setCHOCDF(CHOCDF cHOCDF) {
        this.cHOCDF = cHOCDF;
    }

    public FIBTG getFIBTG() {
        return fIBTG;
    }

    public void setFIBTG(FIBTG fIBTG) {
        this.fIBTG = fIBTG;
    }

    public SUGAR getSUGAR() {
        return sUGAR;
    }

    public void setSUGAR(SUGAR sUGAR) {
        this.sUGAR = sUGAR;
    }

    public PROCNT getPROCNT() {
        return pROCNT;
    }

    public void setPROCNT(PROCNT pROCNT) {
        this.pROCNT = pROCNT;
    }

    public CHOLE getCHOLE() {
        return cHOLE;
    }

    public void setCHOLE(CHOLE cHOLE) {
        this.cHOLE = cHOLE;
    }

    public CA getCA() {
        return cA;
    }

    public void setCA(CA cA) {
        this.cA = cA;
    }

    public WATER getWATER() {
        return wATER;
    }

    public void setWATER(WATER wATER) {
        this.wATER = wATER;
    }

}
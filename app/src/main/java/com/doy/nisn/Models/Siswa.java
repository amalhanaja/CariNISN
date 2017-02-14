
package com.doy.nisn.Models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Siswa {

    @SerializedName("jenis_kelamin")
    private String mJenisKelamin;
    @SerializedName("nama")
    private String mNama;
    @SerializedName("nisn")
    private String mNisn;
    @SerializedName("tanggal_lahir")
    private String mTanggalLahir;
    @SerializedName("tempat_lahir")
    private String mTempatLahir;

    public String getJenisKelamin() {
        return mJenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        mJenisKelamin = jenisKelamin;
    }

    public String getNama() {
        return mNama;
    }

    public void setNama(String nama) {
        mNama = nama;
    }

    public String getNisn() {
        return mNisn;
    }

    public void setNisn(String nisn) {
        mNisn = nisn;
    }

    public String getTanggalLahir() {
        return mTanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        mTanggalLahir = tanggalLahir;
    }

    public String getTempatLahir() {
        return mTempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        mTempatLahir = tempatLahir;
    }

}

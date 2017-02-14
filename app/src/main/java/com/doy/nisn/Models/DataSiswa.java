
package com.doy.nisn.Models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DataSiswa {

    @SerializedName("siswa")
    private List<Siswa> mSiswa;
    @SerializedName("status")
    private String mStatus;

    public List<Siswa> getSiswa() {
        return mSiswa;
    }

    public void setSiswa(List<Siswa> siswa) {
        mSiswa = siswa;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}

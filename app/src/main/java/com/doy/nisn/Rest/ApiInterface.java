package com.doy.nisn.Rest;

import com.doy.nisn.Models.DataSiswa;
import com.doy.nisn.Models.Siswa;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by doy on 2/14/17.
 */

public interface ApiInterface {
    //http://ibacor.com/api/data-siswa?nisn=9985456034&k=459d54c52c55f588d23fd40abcd5784c
    @GET("api/data-siswa")
    Call<DataSiswa> getDataSiswa(@Query("nisn") String nisn, @Query("k") String Apikey);

}

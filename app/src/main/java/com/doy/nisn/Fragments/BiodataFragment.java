package com.doy.nisn.Fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.doy.nisn.Biodata.BioResponse;
import com.doy.nisn.Biodata.Siswa;
import com.doy.nisn.R;
import com.doy.nisn.Rest.ApiClient;
import com.doy.nisn.Rest.BiodataInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BiodataFragment extends Fragment {

    private final static String API_KEY = "459d54c52c55f588d23fd40abcd5784c";

    private TextView noNisn, nama, jenisKelamin, tempatLahir, tanggalLahir;
    private TextInputLayout inputNamaLayout, inputTempatLayout, inputTanggalLayout;
    private EditText inputNama, inputTempat, inputTanggal;
    private ActionProcessButton biodataSearchBtn;
    private CardView dataCv;

    private boolean isNamaValid(){
        if(inputNama.getText().toString().trim().isEmpty()){
            inputNamaLayout.setError("Masukkan Nama Anda!");
            return false;
        }else {
            inputNamaLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean isTempatValid(){
        if(inputTempat.getText().toString().toString().isEmpty()){
            inputTempatLayout.setError("Masukkan Tempat Lahir Anda!");
            return false;
        }
        else{
            inputTempatLayout.setErrorEnabled(false);
        }
        return true;
    }

    public BiodataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_biodata, container, false);
        noNisn = (TextView)rootView.findViewById(R.id.biodata_nisn_value);
        nama = (TextView)rootView.findViewById(R.id.biodata_nama_value);
        jenisKelamin = (TextView)rootView.findViewById(R.id.biodata_jk_value);
        tempatLahir = (TextView)rootView.findViewById(R.id.biodata_tempat_value);
        tanggalLahir = (TextView)rootView.findViewById(R.id.biodata_tanggal_value);

        inputNamaLayout = (TextInputLayout)rootView.findViewById(R.id.input_layout_nama);
        inputTanggalLayout = (TextInputLayout)rootView.findViewById(R.id.input_layout_tanggal);
        inputTempatLayout = (TextInputLayout)rootView.findViewById(R.id.input_layout_tempat);

        inputNama = (EditText)rootView.findViewById(R.id.input_nama);
        inputTanggal = (EditText)rootView.findViewById(R.id.input_tanggal);
        inputTempat = (EditText)rootView.findViewById(R.id.input_tempat);

        biodataSearchBtn = (ActionProcessButton)rootView.findViewById(R.id.biodata_cari_btn);

        dataCv = (CardView)rootView.findViewById(R.id.biodata_card_view);

        biodataSearchBtn.setMode(ActionProcessButton.Mode.ENDLESS);
        biodataSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biodataSearchBtn.setProgress(1);
                saerchNISN();
            }
        });

        return rootView;
    }

    private void saerchNISN() {
        if(!isNamaValid() && !isTempatValid()){
            biodataSearchBtn.setProgress(0);
            return;
        }
        //Restful API with Retrofit
        BiodataInterface biodataInterface = ApiClient.getClient().create(BiodataInterface.class);
        Call<BioResponse> bioResponseCall = biodataInterface.getDataNISN(
                inputNama.getText().toString(),
                inputTempat.getText().toString(),
                inputTanggal.getText().toString(),
                API_KEY
        );
        bioResponseCall.enqueue(new Callback<BioResponse>() {
            @Override
            public void onResponse(Call<BioResponse> call, Response<BioResponse> response) {
                List<Siswa> siswaList = response.body().getSiswa();
                if(response.body().getStatus().equals("success")){
                    nama.setText(siswaList.get(0).getNama());
                    noNisn.setText(siswaList.get(0).getNisn());
                    jenisKelamin.setText(siswaList.get(0).getJenisKelamin());
                    tempatLahir.setText(siswaList.get(0).getTempatLahir());
                    tanggalLahir.setText(siswaList.get(0).getTanggalLahir());

                    biodataSearchBtn.setProgress(100);
                }
                else{
                    biodataSearchBtn.setProgress(-1);
                }
            }

            @Override
            public void onFailure(Call<BioResponse> call, Throwable t) {
                t.printStackTrace();
                biodataSearchBtn.setProgress(-1);
            }

        });
    }

}

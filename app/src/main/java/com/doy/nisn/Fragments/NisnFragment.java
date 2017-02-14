package com.doy.nisn.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.doy.nisn.Models.DataSiswa;
import com.doy.nisn.Models.Siswa;
import com.doy.nisn.R;
import com.doy.nisn.Rest.ApiClient;
import com.doy.nisn.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NisnFragment extends Fragment {

    private final static String API_KEY = "459d54c52c55f588d23fd40abcd5784c";

    private TextView nisnTv, namaTv, jenisKelaminTv, tanggalLahirTv, tempatLahirTv;
    private EditText inputNisn;
    private ActionProcessButton searchBtn;
    private TextInputLayout nisnInputLayout;
    private boolean isNisnValid(){
      if(inputNisn.getText().toString().length() != 10){
          nisnInputLayout.setError("Masukkan NISN dengan Benar");
          requestFocus(inputNisn);
          return false;
      }
      else{
          nisnInputLayout.setErrorEnabled(false);
          return true;
      }
    }

    private void requestFocus(View view) {
        if(view.requestFocus()){
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public NisnFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_nisn, container, false);

        nisnTv = (TextView)rootView.findViewById(R.id.nisn_nisn_value);
        namaTv = (TextView)rootView.findViewById(R.id.nisn_nama_value);
        jenisKelaminTv = (TextView)rootView.findViewById(R.id.nisn_jk_value);
        tempatLahirTv = (TextView)rootView.findViewById(R.id.nisn_tempat_value);
        tanggalLahirTv = (TextView)rootView.findViewById(R.id.nisn_tanggal_value);

        inputNisn = (EditText)rootView.findViewById(R.id.input_nisn);
        searchBtn = (ActionProcessButton) rootView.findViewById(R.id.btn_cari_nisn);
        nisnInputLayout = (TextInputLayout)rootView.findViewById(R.id.input_layout_nisn);

        searchBtn.setMode(ActionProcessButton.Mode.ENDLESS);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBtn.setProgress(1);
                searhNow();
            }
        });

        return rootView;
    }

    private void searhNow() {
        if(!isNisnValid()){
            searchBtn.setProgress(0);
            return;
        }
        //RESTful API with Retrofit
        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<DataSiswa> dataSiswaCall = mApiInterface.getDataSiswa(inputNisn.getText().toString(), API_KEY);
        dataSiswaCall.enqueue(new Callback<DataSiswa>() {
            @Override
            public void onResponse(Call<DataSiswa> call, Response<DataSiswa> response) {
                List<Siswa> siswaList = response.body().getSiswa();
                String status = response.body().getStatus();
                Toast.makeText(getContext(), "STATUS : " + status, Toast.LENGTH_SHORT).show();
                if(status.equals("success")){
                    namaTv.setText(siswaList.get(0).getNama());
                    nisnTv.setText(siswaList.get(0).getNisn());
                    jenisKelaminTv.setText(siswaList.get(0).getJenisKelamin());
                    tanggalLahirTv.setText(siswaList.get(0).getTanggalLahir());
                    tempatLahirTv.setText(siswaList.get(0).getTempatLahir());
                    searchBtn.setProgress(100);
                }
                else{
                    Toast.makeText(getContext(), "Nisn Tidak Ditemukan", Toast.LENGTH_SHORT).show();
                    searchBtn.setProgress(-1);
                }

            }

            @Override
            public void onFailure(Call<DataSiswa> call, Throwable t) {
                t.printStackTrace();
                searchBtn.setProgress(-1);
            }
        });
        //RESTful API with Retrofit

    }

    private void hideKeyboard(){
        View view = getActivity().getCurrentFocus();
        if(view != null){
            ((InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
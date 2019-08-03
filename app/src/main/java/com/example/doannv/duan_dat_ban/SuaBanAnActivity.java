package com.example.doannv.duan_dat_ban;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SuaBanAnActivity extends AppCompatActivity {
    private Toolbar toolbarSuaBA;
    private ImageView imgAnhNHSBA;
    private TextView tvNameNHSBA;
    private TextInputLayout tiSoban;
    private TextInputEditText edSuaSoban;
    private TextInputLayout tiSonguoi;
    private TextInputEditText edSuaSonguoi;
    private RadioButton radioSuaBuatrua;
    private RadioButton radioSuaBuatoi;
    private RadioButton radiaControng;
    private RadioButton radiaDaDat;
    private Button btnSuaBan;
    private RelativeLayout relativeLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_ban_an);
        AnhXa();
        ActionBar();
        GetThongtin();
    }

    private void ActionBar() {
        setSupportActionBar(toolbarSuaBA);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarSuaBA.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetThongtin() {

    }

    private void AnhXa() {
        toolbarSuaBA = (Toolbar) findViewById(R.id.toolbarSuaBA);
        imgAnhNHSBA = (ImageView) findViewById(R.id.imgAnhNHSBA);
        tvNameNHSBA = (TextView) findViewById(R.id.tvNameNHSBA);
        tiSoban = (TextInputLayout) findViewById(R.id.tiSoban);
        edSuaSoban = (TextInputEditText) findViewById(R.id.edSuaSoban);
        tiSonguoi = (TextInputLayout) findViewById(R.id.tiSonguoi);
        edSuaSonguoi = (TextInputEditText) findViewById(R.id.edSuaSonguoi);
        radioSuaBuatrua = (RadioButton) findViewById(R.id.radioSuaBuatrua);
        radioSuaBuatoi = (RadioButton) findViewById(R.id.radioSuaBuatoi);
        radiaControng = (RadioButton) findViewById(R.id.radiaControng);
        radiaDaDat = (RadioButton) findViewById(R.id.radiaDaDat);
        btnSuaBan = (Button) findViewById(R.id.btnSuaBan);
        relativeLayout = (RelativeLayout) findViewById(R.id.menu_progressbarSuaBan);
    }
}

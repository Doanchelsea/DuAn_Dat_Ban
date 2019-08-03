package com.example.doannv.duan_dat_ban.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doannv.duan_dat_ban.LichSuActivity;
import com.example.doannv.duan_dat_ban.Main2Activity;
import com.example.doannv.duan_dat_ban.MainActivity;
import com.example.doannv.duan_dat_ban.R;
import com.example.doannv.duan_dat_ban.SuaBanAnActivity;
import com.example.doannv.duan_dat_ban.adapter.BuaAnAdapter;
import com.example.doannv.duan_dat_ban.adapter.QLBuaAnAdapter;
import com.example.doannv.duan_dat_ban.model.BanAn;
import com.example.doannv.duan_dat_ban.model.MyItemDatBan;
import com.example.doannv.duan_dat_ban.model.MySuaBan;
import com.example.doannv.duan_dat_ban.model.MyXoaBA;
import com.example.doannv.duan_dat_ban.unti.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QLBuatruaFragment extends Fragment {
    private int idBuaAn = 1;
    private  String IDTK;
    private   String IDNH,IMGNH;
    private  RecyclerView recyBuaTrua;
    private  ArrayList<BanAn> arrayList;
    private  LinearLayoutManager manager;
    private QLBuaAnAdapter giaReAdapter;
    private  RelativeLayout menu_progressbarBtrua;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ql_bua_trua,container,false);
        menu_progressbarBtrua = view.findViewById(R.id.menu_progressbarQLBtrua);
        recyBuaTrua = view.findViewById(R.id.recyQLBuaTrua);
        arrayList = new ArrayList<>();
        manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        giaReAdapter = new QLBuaAnAdapter(getActivity(),arrayList);
        recyBuaTrua.setHasFixedSize(true);
//        recyBuaTrua.setLayoutManager(manager);
        recyBuaTrua.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyBuaTrua.setAdapter(giaReAdapter);
        IDTK = MainActivity.ID;
        Bundle bundle = getArguments();
        if (bundle != null) {
            IDNH = Main2Activity.IDNH;
            IMGNH = Main2Activity.IMGNH;
        }
        giaReAdapter.setMyXoaBA(new MyXoaBA() {
            @Override
            public void OnClick(final BanAn banAn) {
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanxoaban, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        arrayList.clear();
                        GetData();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put("idbanan",""+banAn.getiD());
                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
        GetData();
        giaReAdapter.setMySuaBan(new MySuaBan() {
            @Override
            public void Onclick(BanAn banAn) {
                Intent intent = new Intent(getActivity(), SuaBanAnActivity.class);

                startActivity(intent);
            }
        });
        return view;
    }

    private void GetData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanbuaan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int iD;
                int iDNhaHang;
                int buaAn;
                int soNguoi;
                int trangThai;
                int soBan;
                String nameNH;
                String imgBuaAn;
                if (response != null ) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            iD = jsonObject.getInt("id");
                            iDNhaHang = jsonObject.getInt("idnhahang");
                            buaAn = jsonObject.getInt("buaan");
                            soNguoi = jsonObject.getInt("songuoi");
                            trangThai = jsonObject.getInt("trangthai");
                            soBan = jsonObject.getInt("soban");
                            nameNH = jsonObject.getString("namenh");
                            imgBuaAn = jsonObject.getString("imgbanan");
                            arrayList.add(new BanAn(iD,iDNhaHang,buaAn,soNguoi,trangThai,soBan,nameNH,imgBuaAn));
                            giaReAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("idbuaan",""+idBuaAn);
                hashMap.put("idnhahang",""+IDNH);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}

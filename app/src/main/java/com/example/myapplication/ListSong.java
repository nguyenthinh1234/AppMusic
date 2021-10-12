package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ListSong extends AppCompatActivity {
    private RecyclerView rcv_View;
    private List<Song>mSong;
    private MusicAdapter musicAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);
        rcv_View = findViewById(R.id.rcv_List);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        musicAdapter = new MusicAdapter(getList(), ListSong.this);
        rcv_View.setLayoutManager(layoutManager);
        rcv_View.setAdapter(musicAdapter);
    }

    private List<Song> getList() {
        mSong = new ArrayList<>();
        mSong.add(new Song(R.drawable.jin_tuan_nam,R.raw.buon_hay_mua, "Buồn... Hay Mưa", "Jin Tuấn Nam"));
        mSong.add(new Song(R.drawable.phan_duy_lo_lang_phat_huy_truzg, R.raw.phan_duyen_lo_lang, "Phận duyên lỡ làng","Phát Huy x TRUZG"));
        mSong.add(new Song(R.drawable.caothaison, R.raw.con_dg_mua, "Con đường mưa","Cao Thái Sơn"));
        mSong.add(new Song(R.drawable.if_tu_vi, R.raw.if_if,"if","Tư Vi"));
        mSong.add(new Song(R.drawable.khanhphong ,R.raw.doan_duong_vang, "Đoạn đường vắng","Khánh Phong"));
        mSong.add(new Song(R.drawable.avici,R.raw.the_night, "The night","Avici"));
        mSong.add(new Song(R.drawable.sai_gon_dau_long_qua_hoangduyen,  R.raw.sai_gon_dau_long_qua_toan_ky_niem_chung_ta_hua_kim_tuyen_x_hoang_duyen_official_mv,"Sài gòn đau lòng quá","Hứa Kim Tuyền x Hoàng Duyên"));
    return  mSong;

    }
}
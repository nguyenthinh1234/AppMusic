package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {
    private List<Song> mSong;
    private Context context;

    public MusicAdapter(List<Song> mSong, Context context) {
        this.mSong = mSong;
        this.context = context;
        notifyDataSetChanged();;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        Song song  = mSong.get(position);
        holder.imgSong.setImageResource(song.getImgSong());
        holder.tv_SongName.setText(song.getSongName());
        holder.tv_SingerName.setText(song.getSingerName());
        holder.imgSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("song", song);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSong.size();
    }

    public class MusicViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_SongName, tv_SingerName;
        private CircleImageView imgSong;
        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_SingerName = itemView.findViewById(R.id.tv_SingerName);
            tv_SongName = itemView.findViewById(R.id.tv_SongName);
            imgSong = itemView.findViewById(R.id.imgSong);
        }
    }
}

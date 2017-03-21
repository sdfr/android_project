package fr.esilv.s8.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.esilv.s8.myapplication.R;
import fr.esilv.s8.myapplication.interfaces.OnVideoSelectedListener;
import fr.esilv.s8.myapplication.models.Videos;
import fr.esilv.s8.myapplication.viewholders.VideosViewHolder;

public class VideosAdapter extends RecyclerView.Adapter<VideosViewHolder> {

    private final Videos videos;
    private OnVideoSelectedListener onVideoSelectedListener;

    public VideosAdapter(Videos videos) {
        this.videos = videos;
    }

    @Override
    public VideosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_videos, parent, false);
        return new VideosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideosViewHolder holder, int position) {
        holder.setOnVideoSelectedListener(onVideoSelectedListener);
        holder.bind(videos.getItems().get(position));
    }

    @Override
    public int getItemCount() {
        return videos.getItems() != null ? videos.getItems().size() : 0;
    }

    public void setOnVideoSelectedListener(OnVideoSelectedListener onVideoSelectedListener) {
        this.onVideoSelectedListener = onVideoSelectedListener;
    }
}

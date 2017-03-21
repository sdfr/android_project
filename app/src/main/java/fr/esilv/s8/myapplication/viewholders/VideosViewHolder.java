package fr.esilv.s8.myapplication.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fr.esilv.s8.myapplication.R;
import fr.esilv.s8.myapplication.interfaces.OnVideoSelectedListener;
import fr.esilv.s8.myapplication.models.Videos;

public class VideosViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private TextView url_address;
    private OnVideoSelectedListener onVideoSelectedListener;

    public VideosViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        url_address = (TextView) itemView.findViewById(R.id.url_address);
    }

    public void bind(final Videos.ItemsBean video) {
        title.setText(video.getSnippet().getTitle());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onVideoSelectedListener == null) {
                    return;
                }
                onVideoSelectedListener.onVideoSelected(video);
            }
        });
    }

    public void setOnVideoSelectedListener(OnVideoSelectedListener onVideoSelectedListener) {
        this.onVideoSelectedListener = onVideoSelectedListener;
    }
}

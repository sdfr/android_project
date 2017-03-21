package fr.esilv.s8.myapplication.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fr.esilv.s8.myapplication.R;
import fr.esilv.s8.myapplication.interfaces.OnVideoSelectedListener;
import fr.esilv.s8.myapplication.models.Videos;

public class VideosViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private TextView description;
    private TextView publicationDate;
    private TextView author;

    private OnVideoSelectedListener onVideoSelectedListener;

    public VideosViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        publicationDate = (TextView) itemView.findViewById(R.id.publicationDate);
        author = (TextView) itemView.findViewById(R.id.channel);
    }

    public void bind(final Videos.ItemsBean video) {
        title.setText(video.getSnippet().getTitle());
        description.setText(video.getSnippet().getDescription());
        publicationDate.setText(video.getSnippet().getPublishedAt());
        author.setText(video.getSnippet().getChannelTitle());
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

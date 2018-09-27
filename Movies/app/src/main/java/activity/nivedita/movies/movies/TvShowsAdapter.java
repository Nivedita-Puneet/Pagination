package activity.nivedita.movies.movies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;

import activity.nivedita.movies.model.tvshows.PopularTvShows;
import activity.nivedita.movies.networkutils.ConstantsUtil;

/**
 * The adapter used to bind the data of tv shows into fragment.
 */

public class TvShowsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    List<PopularTvShows> tvShowsResults;

    private static final int ITEM = 0;
    private static final int LOADING = 1;


    public TvShowsAdapter(Context context) {
        this.context = context;
        tvShowsResults = new LinkedList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder recyclerViewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                recyclerViewHolder = getViewHolder(parent, layoutInflater);
                break;
            case LOADING:
                // TODO: Implement the loading functionality
                break;
        }
        return recyclerViewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.tv_show_item, parent, false);
        viewHolder = new TVShowsVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        PopularTvShows popularTvShows = tvShowsResults.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                final TVShowsVH tvShowsVH = (TVShowsVH) holder;
                Glide.with(context)
                        .load(ConstantsUtil.TV_SHOWS_POSTER_PATH + popularTvShows.getPosterPath())
                        .fitCenter()
                        .into(tvShowsVH.tvImageView);
                tvShowsVH.tvShowTitle.setText(popularTvShows.getOriginalName());
                tvShowsVH.ratingView.setText(popularTvShows.getVoteCount());
                //TODO: Need to identify the genre ID and set it appropriately.
                tvShowsVH.tvGenres.setText(popularTvShows.getGenreIds().get(0));

        }
    }

    @Override
    public int getItemCount() {
        return tvShowsResults.size();
    }


    protected class TVShowsVH extends RecyclerView.ViewHolder {

        private ImageView tvImageView;
        private TextView ratingView;
        private TextView tvShowTitle;
        private TextView tvGenres;

        public TVShowsVH(View itemView) {
            super(itemView);

            tvImageView = (ImageView) itemView.findViewById(R.id.tv_image);
            ratingView = (TextView) itemView.findViewById(R.id.rating_circle);
            tvShowTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvGenres = (TextView) itemView.findViewById(R.id.tv_genres);

        }
    }

    /*Add the helper methods to bind the data to the adapter for tv shows*/
    public void add(PopularTvShows r) {
        tvShowsResults.add(r);
        notifyItemInserted(tvShowsResults.size() - 1);
    }

    public void addAll(List<PopularTvShows> moveResults) {
        for (PopularTvShows result : moveResults) {
            add(result);
        }
    }

}

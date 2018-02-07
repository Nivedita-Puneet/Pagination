package org.nivi.mydailynews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.nivi.mydailynews.R;
import org.nivi.mydailynews.model.Category;

import java.util.List;

/**
 * Created by NEETU on 07-02-2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsAdapterViewHolder> {

    private List<Category> categories;
    private Context context;

    public NewsAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    public interface MovieAdapterOnclickHandler {

        void listItemClickListener(String id);
    }

    public class NewsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView description;
        ImageView thumbnail;

        public NewsAdapterViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.news_title);
            description = (TextView) view.findViewById(R.id.news_desc);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            //mOnclickHandler.listItemClickListener(movies.get(getAdapterPosition()).getImdbID());
        }

    }

    @Override
    public NewsAdapter.NewsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int id = R.layout.news_item;
        boolean shouldAttachToParentImmediately = false;
        View view = LayoutInflater.from(context).inflate(id, parent, shouldAttachToParentImmediately);
        return new NewsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.NewsAdapterViewHolder holder, int position) {
        holder.title.setText(categories.get(position).getTitle());
        String desc = categories.get(position).getDescription() == null ? context.getString(R.string.no_desc) : categories.get(position).getDescription();
        holder.description.setText(desc);
        Picasso.with(this.context).load(categories.get(position).getImageHref())
                .placeholder(R.drawable.no_image)
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {

        if (null == categories)
            return 0;
        else
            return categories.size();
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }
}

package vk.com.imagegallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private List<String> list =new ArrayList<>();
    private final Picasso picasso;

    public GalleryAdapter(Picasso picasso) {
        this.picasso = picasso;
    }


    public void setDataSource(List<String> list) {
        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (FrameLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Context context = holder.imageView.getContext();
        picasso
                .load(list.get(position)) //
                .placeholder(android.R.color.darker_gray) //
                .error(android.R.color.holo_red_dark) //
                .fit()
                .centerCrop()
                .tag(context) //
                .into(holder.imageView);
        String[] splits = list.get(position).split("/");
        holder.textView.setText(splits[splits.length-1]);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            textView = (TextView) itemView.findViewById(R.id.text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int item = getAdapterPosition();
            if(item != RecyclerView.NO_POSITION) {
                Toast.makeText(v.getContext(), "Clicked on View at " + item, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

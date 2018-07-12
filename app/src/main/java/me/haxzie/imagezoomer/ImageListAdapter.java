package me.haxzie.imagezoomer;

import android.app.FragmentTransaction;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageHolder> {

    private ArrayList<ImageData> imageList;
    private Context mContext;

    public ImageListAdapter( Context mContext, ArrayList<ImageData> imageList) {
        this.imageList = imageList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.image_list_item, parent, false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {

        ImageData data = imageList.get(position);
        final String url = data.getUrl();

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FullScreenDialog dialog = new FullScreenDialog(url);
                FragmentTransaction ft = ((FragmentActivity) mContext).getFragmentManager().beginTransaction();
                dialog.show(ft, FullScreenDialog.TAG);
            }
        });

        Glide.with(mContext)
                .load(data.getUrl())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder {
        private ImageView image;

        public ImageHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }

    }
}

package com.example.tadeu17.dronetrack;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.List;


public class downloadedAdapter extends RecyclerView.Adapter<downloadedAdapter.ViewHolder>{

    private static final String TAG = "downloadedAdapter";

    //  private List<listItem> listPhotos;

    private List<listItem> items;
    private Context context;


    public downloadedAdapter(List<listItem> items, Context context) {
        this.items = items;
        this.context = context;

    }

    @Override
    public downloadedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.downloaded_layout,parent , false);
        downloadedAdapter.ViewHolder holder = new downloadedAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final downloadedAdapter.ViewHolder viewHolder, final int i) {
        final listItem listItem = items.get(i);

        byte[] pictureImage = listItem.getImgB();
        Glide.with(context).asBitmap().load(pictureImage).into(viewHolder.imageView);

       // viewHolder.imageView.setImageBitmap(bitmap);



        /*listItem.setImg(bitmap);*/

        viewHolder.userImage.setText(listItem.getUserLI());
        viewHolder.locationImage.setText(listItem.getLocationLI());

       viewHolder.descriptionImage.setText(listItem.getDescriptionLI());





        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + items.get(i));

                Toast.makeText(context, listItem.getUserLI() , Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView userImage;
        TextView locationImage;
        TextView descriptionImage;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_downloaded);
            userImage = itemView.findViewById(R.id.user_downloaded);
            locationImage = itemView.findViewById(R.id.location_downloaded);
            descriptionImage = itemView.findViewById(R.id.description_downloaded);
            parentLayout = itemView.findViewById(R.id.parent_layout_downloaded);



        }
    }



}



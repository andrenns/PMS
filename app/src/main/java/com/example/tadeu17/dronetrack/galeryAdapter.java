package com.example.tadeu17.dronetrack;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class galeryAdapter extends RecyclerView.Adapter<galeryAdapter.ViewHolder>{

    private static final String TAG = "galeryAdapter";
    
  //  private List<listItem> listPhotos;

    private List<listItem> items;
    private Context context;
    private SparseBooleanArray itemStateArray= new SparseBooleanArray();

    public galeryAdapter(List<listItem> items, Context context) {
        this.items = items;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listlayout,parent , false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final listItem listItem = items.get(i);

        Log.d(TAG, "onBindViewHolder: called.");
        Picasso.with(context)
                .load(listItem.getImgUrl())
                .resize(0,500)
                .into(viewHolder.imageView);

        viewHolder.userImage.setText(listItem.getUserLI());
        viewHolder.locationImage.setText(listItem.getLocationLI());

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
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_galery);
            userImage = itemView.findViewById(R.id.user_galery);
            locationImage = itemView.findViewById(R.id.location_galery);
            parentLayout = itemView.findViewById(R.id.parent_layout);



        }
    }
    


}

package com.british.sheep.breeds;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {
    private static final String TAG = "RecyclerViewAdapter";

    ArrayList<Images> filteredList;
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mImageTypes = new ArrayList<>();
    private ArrayList<String> mImageEstablisheds = new ArrayList<>();
    private ArrayList<String> mImageHandles = new ArrayList<>();
    private ArrayList<String> mImageColours = new ArrayList<>();
    private ArrayList<String> mImageFleeces = new ArrayList<>();
    private ArrayList<String> mImageStaples = new ArrayList<>();
    private ArrayList<String> mImageMicrons = new ArrayList<>();
    private ArrayList<String> mImageGallerys = new ArrayList<>();
    ArrayList<Images> imagesArrayList;
    ArrayList<Images> imagesArrayListFull;
    private Context mContext;
    private ArrayList<String> copyImageNames;

    public RecyclerViewAdapter(Context context, ArrayList<Images> imagesArrayList) {
        mContext = context;
        this.imagesArrayList = imagesArrayList;
        imagesArrayListFull = new ArrayList<>(imagesArrayList);
    }

    public RecyclerViewAdapter(Context context, ArrayList<String> imageNames, ArrayList<String> images, ArrayList<String> types, ArrayList<String> establisheds, ArrayList<String> handles, ArrayList<String> colours, ArrayList<String> fleeces, ArrayList<String> staples, ArrayList<String> microns, ArrayList<String> gallerys) {
        mImageNames = imageNames;
        mImages = images;
        mImageTypes = types;
        mImageEstablisheds = establisheds;
        mImageHandles = handles;
        mImageColours = colours;
        mImageFleeces = fleeces;
        mImageStaples = staples;
        mImageMicrons = microns;
        mImageGallerys = gallerys;
        mContext = context;
        copyImageNames = new ArrayList<>(imageNames);

    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(imagesArrayListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Images item : imagesArrayListFull) {

                    if (item.getmImageNames().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);

                    }

                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            imagesArrayList.clear();
            imagesArrayList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


    @Override
    public Filter getFilter() {
        // TODO Auto-generated method stub
        return filter;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        Glide.with(mContext)
                .asBitmap()
                .load(imagesArrayList.get(position).getmImages())
                .into(holder.image);

        holder.imageName.setText(imagesArrayList.get(position).getmImageNames());


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filteredList != null) {
                    //return the adapter position
                    int pos = holder.getAdapterPosition();

                    Images image = filteredList.get(pos);
                    //itertate whole imagesArrayListFull list
                    //and get position in pos variable
                    // whose name matches the name of filtered list
                    for (int i = 0; i < imagesArrayListFull.size(); i++) {

                        if (image.getmImageNames().equals(imagesArrayListFull.get(i).getmImageNames())) {
                            pos = i;
                            break;
                        }
                    }

                    Intent intent = new Intent(mContext, GalleryActivity.class);

                    Images images = new Images(imagesArrayListFull.get(pos).getmImageNames()
                            , imagesArrayListFull.get(pos).getmImages(),
                            imagesArrayListFull.get(pos).getmImageTypes()
                            , imagesArrayListFull.get(pos).getmImageEstablisheds()
                            , imagesArrayListFull.get(pos).getmImageHandles()
                            , imagesArrayListFull.get(pos).getmImageColours()
                            , imagesArrayListFull.get(pos).getmImageFleeces()
                            , imagesArrayListFull.get(pos).getmImageStaples()
                            , imagesArrayListFull.get(pos).getmImageMicrons()
                            , imagesArrayListFull.get(pos).getmImageGallerys());

                    intent.putExtra("Images", images);
//                    intent.putExtra("image_url", mImages.get(pos));
//                    intent.putExtra("image_name", mImageNames.get(position));
//                    intent.putExtra("image_type", mImageTypes.get(pos));
//                    intent.putExtra("image_established", mImageEstablisheds.get(pos));
//                    intent.putExtra("image_handle", mImageHandles.get(pos));
//                    intent.putExtra("image_colour", mImageColours.get(pos));
//                    intent.putExtra("image_fleece", mImageFleeces.get(pos));
//                    intent.putExtra("image_staple", mImageStaples.get(pos));
//                    intent.putExtra("image_micron", mImageMicrons.get(pos));
//                    intent.putExtra("image_gallery", mImageGallerys.get(pos));

                    mContext.startActivity(intent);
                } else {

                    Intent intent = new Intent(mContext, GalleryActivity.class);
                    Images images = new Images(imagesArrayListFull.get(position).getmImageNames()
                            , imagesArrayListFull.get(position).getmImages(),
                            imagesArrayListFull.get(position).getmImageTypes()
                            , imagesArrayListFull.get(position).getmImageEstablisheds()
                            , imagesArrayListFull.get(position).getmImageHandles()
                            , imagesArrayListFull.get(position).getmImageColours()
                            , imagesArrayListFull.get(position).getmImageFleeces()
                            , imagesArrayListFull.get(position).getmImageStaples()
                            , imagesArrayListFull.get(position).getmImageMicrons()
                            , imagesArrayListFull.get(position).getmImageGallerys());
                    //put whole object as intent extra
                    intent.putExtra("Images", images);


//                    intent.putExtra("image_url", mImages.get(position));
//                    intent.putExtra("image_name", mImageNames.get(position));
//                    intent.putExtra("image_type", mImageTypes.get(position));
//                    intent.putExtra("image_established", mImageEstablisheds.get(position));
//                    intent.putExtra("image_handle", mImageHandles.get(position));
//                    intent.putExtra("image_colour", mImageColours.get(position));
//                    intent.putExtra("image_fleece", mImageFleeces.get(position));
//                    intent.putExtra("image_staple", mImageStaples.get(position));
//                    intent.putExtra("image_micron", mImageMicrons.get(position));
//                    intent.putExtra("image_gallery", mImageGallerys.get(position));

                    mContext.startActivity(intent);
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return imagesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView imageName;
        TextView imageType;
        TextView imageEstablished;
        TextView imageHandle;
        TextView imageColour;
        TextView imageFleece;
        TextView imageStaple;
        TextView imageMicron;
        ImageView imageGallery;
        RelativeLayout parentLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            imageType = itemView.findViewById(R.id.image_type);
            imageEstablished = itemView.findViewById(R.id.image_established);
            imageHandle = itemView.findViewById(R.id.image_handle);
            imageColour = itemView.findViewById(R.id.image_colour);
            imageFleece = itemView.findViewById(R.id.image_fleece);
            imageStaple = itemView.findViewById(R.id.image_staple);
            imageMicron = itemView.findViewById(R.id.image_micron);
            imageGallery = itemView.findViewById(R.id.image_gallery);


        }

    }


}
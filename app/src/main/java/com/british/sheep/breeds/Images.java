package com.british.sheep.breeds;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

//implementing serializeable to put Image object as an intent's extra
public class Images implements Serializable {
    private String mImageNames ;
    private String mImages ;
    private String mImageTypes ;
    private String mImageEstablisheds;
    private String mImageHandles ;
    private String mImageColours ;
    private String mImageFleeces ;
    private String mImageStaples ;
    private String mImageMicrons ;
    private String mImageGallerys;



    public String getmImageNames() {
        return mImageNames;
    }

    public void setmImageNames(String mImageNames) {
        this.mImageNames = mImageNames;
    }

    public String getmImages() {
        return mImages;
    }

    public void setmImages(String mImages) {
        this.mImages = mImages;
    }

    public String getmImageTypes() {
        return mImageTypes;
    }

    public void setmImageTypes(String mImageTypes) {
        this.mImageTypes = mImageTypes;
    }

    public String getmImageEstablisheds() {
        return mImageEstablisheds;
    }

    public void setmImageEstablisheds(String mImageEstablisheds) {
        this.mImageEstablisheds = mImageEstablisheds;
    }

    public String getmImageHandles() {
        return mImageHandles;
    }

    public void setmImageHandles(String mImageHandles) {
        this.mImageHandles = mImageHandles;
    }

    public String getmImageColours() {
        return mImageColours;
    }

    public void setmImageColours(String mImageColours) {
        this.mImageColours = mImageColours;
    }

    public String getmImageFleeces() {
        return mImageFleeces;
    }

    public void setmImageFleeces(String mImageFleeces) {
        this.mImageFleeces = mImageFleeces;
    }

    public String getmImageStaples() {
        return mImageStaples;
    }

    public void setmImageStaples(String mImageStaples) {
        this.mImageStaples = mImageStaples;
    }

    public String getmImageMicrons() {
        return mImageMicrons;
    }

    public void setmImageMicrons(String mImageMicrons) {
        this.mImageMicrons = mImageMicrons;
    }

    public String getmImageGallerys() {
        return mImageGallerys;
    }

    public void setmImageGallerys(String mImageGallerys) {
        this.mImageGallerys = mImageGallerys;
    }

    public Images(String mImageNames, String mImages, String mImageTypes, String mImageEstablisheds, String mImageHandles, String mImageColours, String mImageFleeces, String mImageStaples, String mImageMicrons, String mImageGallerys) {
        this.mImageNames = mImageNames;
        this.mImages = mImages;
        this.mImageTypes = mImageTypes;
        this.mImageEstablisheds = mImageEstablisheds;
        this.mImageHandles = mImageHandles;
        this.mImageColours = mImageColours;
        this.mImageFleeces = mImageFleeces;
        this.mImageStaples = mImageStaples;
        this.mImageMicrons = mImageMicrons;
        this.mImageGallerys = mImageGallerys;
    }
}

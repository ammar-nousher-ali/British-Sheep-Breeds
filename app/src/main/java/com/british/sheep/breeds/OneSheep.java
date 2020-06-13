package com.british.sheep.breeds;

public class OneSheep {
    private String mFleeces ;
    private String mStaples ;
    private String mMicrons ;
    private String mGalleryUrls;
    private String mNames;
    private String mImageUrls ;
    private String mTypes;
    private String mEstablishedsist;
    private String mHandles ;
    private String mColours ;

    public OneSheep(String names, String imageUrls, String types, String establishedsist, String handles, String colours, String fleeces, String staples, String microns, String galleryUrls) {
        mNames = names;
        mImageUrls = imageUrls;
        mTypes = types;
        mEstablishedsist = establishedsist;
        mHandles = handles;
        mColours = colours;
        mFleeces = fleeces;
        mStaples = staples;
        mMicrons = microns;
        mGalleryUrls = galleryUrls;
    }

    public String getNames() {
        return mNames;
    }

    public String getImageUrls() {
        return mImageUrls;
    }

    public String getTypes() {
        return mTypes;
    }

    public String getEstablishedsist() {
        return mEstablishedsist;
    }

    public String getHandles() {
        return mHandles;
    }

    public String getColours() {
        return mColours;
    }

    public String getFleeces() {
        return mFleeces;
    }


    public String getStaples() {
        return mStaples;
    }

    public String getMicrons() {
        return mMicrons;
    }

    public String getGalleryUrls() {
        return mGalleryUrls;
    }
}
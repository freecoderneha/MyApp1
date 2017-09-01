package com.example.android.myapp1;

public class Word {

    private int mGradientResourceId;
    private int mHeadingId;
    private int mImageResourceId;

    public Word(int gradientResourceId,int headingId, int imageResourceId ){
        mGradientResourceId=gradientResourceId;
        mHeadingId=headingId;
        mImageResourceId=imageResourceId;
    }

    public int getGradientResourceId(){
        return mGradientResourceId;
    }

    public int getHeadingId()
    {
        return mHeadingId;
    }
    public int getImageResourceId(){
        return mImageResourceId;
    }
}

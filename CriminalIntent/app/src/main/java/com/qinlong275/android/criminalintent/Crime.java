package com.qinlong275.android.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by 秦龙 on 2017/9/4.
 */

public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private String mSuspect;

    public Crime(){
       this(UUID.randomUUID());
    }

    public Crime(UUID id){
        mId=id;
        mDate=new Date();
    }

    public String getPhotoFilename(){
        return "IMG_"+getId().toString()+".jpg";
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }
}

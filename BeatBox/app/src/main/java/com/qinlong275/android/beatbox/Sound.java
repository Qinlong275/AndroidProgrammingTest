package com.qinlong275.android.beatbox;

/**
 * Created by 秦龙 on 2017/9/20.
 */

public class Sound {

    private String mAssetPath;
    private String mName;
    private Integer soundId;

    public Sound(String assetPath){
        mAssetPath=assetPath;
        String[]compoents=assetPath.split("/");
        String filename=compoents[compoents.length-1];
        mName=filename.replace(".wav","");
    }

    public String getName() {
        return mName;
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public Integer getSoundId() {
        return soundId;
    }

    public void setSoundId(Integer soundId) {
        this.soundId = soundId;
    }
}

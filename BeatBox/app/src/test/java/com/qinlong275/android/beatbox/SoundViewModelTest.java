package com.qinlong275.android.beatbox;

import org.junit.Before;
import org.junit.Test;

import java.net.SocketPermission;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by 秦龙 on 2017/9/20.
 */
public class SoundViewModelTest {
    private BeatBox mBeatBox;
    private Sound mSound;
    private SoundViewModel mSubject;

    @Before
    public void setUp() throws Exception {
        mBeatBox=mock(BeatBox.class);
        mSound=new Sound("assetPath");
        mSubject=new SoundViewModel(mBeatBox);
        mSubject.setSound(mSound);
    }

    @Test
    public void exposeSoundNameAsTitle(){
        assertThat(mSubject.getTitle(),is(mSound.getName()));
    }

    @Test
    public void callsBeatBoxPlayOnButtonClicked(){
        mSubject.onButtonClicked();
        verify(mBeatBox).play(mSound);
    }
}
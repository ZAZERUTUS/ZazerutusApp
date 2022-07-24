package com.example.appmetanit.helpers;

import android.content.Context;
import android.media.MediaPlayer;

public class PlaySound {

    public static void Play_Sound(Context context, int uri) {
        MediaPlayer player = MediaPlayer.create(context, uri);
        player.start();
    }
}

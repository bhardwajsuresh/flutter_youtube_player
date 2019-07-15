package com.sureshb.flutter_youtube_player;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.HashMap;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

public class FlutterYoutubePlayerFactory extends PlatformViewFactory {
    private final BinaryMessenger messenger;
    private final Activity _activity;

    public FlutterYoutubePlayerFactory(BinaryMessenger messenger, Activity activity) {
        super(StandardMessageCodec.INSTANCE);
        this.messenger = messenger;
        this._activity = activity;

    }

    @Override @SuppressWarnings("unchecked")
    public PlatformView create(Context context, int id, Object o) {
        Log.d("COM.SURESHB.FLUTTERY...", "inside PlatformView create " + o.toString());
        return new FlutterYoutubePlayer(context, messenger, id, (HashMap<String, Object>)o, _activity);
    }
}
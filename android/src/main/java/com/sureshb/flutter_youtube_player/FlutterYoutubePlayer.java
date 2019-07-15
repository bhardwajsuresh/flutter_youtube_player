package com.sureshb.flutter_youtube_player;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import static io.flutter.plugin.common.MethodChannel.MethodCallHandler;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.platform.PlatformView;

import com.google.android.youtube.player.OnYoutubePlayerInitializedSucess;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayerViewUtils;

import java.util.HashMap;

public class FlutterYoutubePlayer implements PlatformView, MethodCallHandler, OnYoutubePlayerInitializedSucess {
    private final YouTubePlayerView youTubePlayerView;
    private final MethodChannel methodChannel;
    private final HashMap<String, Object> settings;
    private YouTubePlayer player;

    FlutterYoutubePlayer(Context context, BinaryMessenger messenger, int id, HashMap<String, Object> s, Activity activity) {
        //Log.d("COM.SURESHB.FLUTTERY...", "inside FlutterYoutubePlayer: " + messenger.toString());
        //Log.d("COM.SURESHB.FLUTTERY...", "inside FlutterYoutubePlayer: " + s.toString());
        settings = s;
        player = null;
        youTubePlayerView = YouTubePlayerViewUtils.createYouTubePlayerView(context, (String) settings.get("apiKey"), this , activity);
        methodChannel = new MethodChannel(messenger, "com.sureshb.flutteryoutubeplugin" );
        methodChannel.setMethodCallHandler(this);
    }

    @Override
    public View getView() {
        return youTubePlayerView;
    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        switch (methodCall.method) {
//            case "setText":
//                setText(methodCall, result);
//                break;
            default:
                result.notImplemented();
        }
    }

//    private void setText(MethodCall methodCall, Result result) {
//        String text = (String) methodCall.arguments;
//        youTubePlayerView.setText(text);
//        result.success(null);
//    }

    @Override
    public void dispose() {
        if(player!= null)
        {
            player.pause();
        }
    }

    @Override
    public void OnYoutubePlayerInitializedSucessEvent(YouTubePlayer player) {
        Log.d("COM.SURESHB.FLUTTERY...", "inside OnYoutubePlayerInitializedSucessEvent" );

        this.player = player;

        boolean autoPlay = (boolean)settings.get("autoPlay");
        if(autoPlay == true) {
            player.loadVideo((String)settings.get("videoId"));
        }
        else
        {
            player.cueVideo((String)settings.get("videoId"));
        }
    }
}
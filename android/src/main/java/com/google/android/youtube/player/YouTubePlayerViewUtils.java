package com.google.android.youtube.player;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


public class YouTubePlayerViewUtils {

    public static YouTubePlayerView createYouTubePlayerView(final Context context, String api_key, final OnYoutubePlayerInitializedSucess listener, final Activity activity) {
        // create YoutubePlayerView by private-package constructor



        //final Activity aa = new Activity();
        final YouTubePlayerView view = new YouTubePlayerView(context, /*AttributeSet*/ null, /*defStyleAttr*/ 0,
                new YouTubePlayerView.b() {
                    @Override
                    public void a(YouTubePlayerView view,
                                  String apiKey,
                                  YouTubePlayer.OnInitializedListener listener) {
                        view.a(activity, view, apiKey, listener, null);
                    }

                    @Override
                    public void a(YouTubePlayerView view) {
                        // Do Nothing
                    }
                });

        OnInitializedListener mOnYoutubePlayerInitializedListener = new OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer player,
                                                boolean wasRestored) {
                if (player == null) {
                    return;
                }

                //ViewGroup viewgroup=(ViewGroup)view.getParent().getParent();

                show_children((View) view.getParent().getParent());

                //Log.d("ANDROID", "view group children : " + viewgroup.getChildCount() );


                listener.OnYoutubePlayerInitializedSucessEvent(player);
                //player.cueVideo(mYoutubeVideoId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult result) {
                // error handling
            }

            private void show_children(View v) {
                ViewGroup viewgroup=(ViewGroup)v;
                for (int i=0;i<viewgroup.getChildCount();i++) {
                    View v1=viewgroup.getChildAt(i);
                    String view_name = v1.toString();
                    Log.d("COM.SURESHB.FLUTTERY...",view_name);

                    if(view_name.contains("io.flutter.plugin.platform.SingleViewPresentation$FakeWindowViewGroup"))
                    {
                        v1.setVisibility(View.GONE);
                    }
                    else {
                        if (v1 instanceof ViewGroup) {
                            show_children(v1);
                        }
                    }

                }
            }
        };

        view.initialize(api_key, mOnYoutubePlayerInitializedListener);

        // initialize YoutubePlayerView with API Key and OnInitializedListener
       // view.initialize(DeveloperKey.DEVELOPER_KEY, initializedListener);

        return view;
    }

//    public static Bundle getPlayerState(YouTubePlayerView view) {
//        return view.e();
//    }

    /**
     * The following methods sync from YouTubePlayerFragment and YouTubePlayerActivity
     * We need to call these method by Fragment's lifecycle
     */
    public static void start(YouTubePlayerView view) {
        view.a();
    }

    public static void resume(YouTubePlayerView view) {
        view.b();
    }

    public static void pause(YouTubePlayerView view) {
        view.c();
    }

    public static void stop(YouTubePlayerView view) {
        view.d();
    }

    public static void destroyView(YouTubePlayerView view, boolean isFinishing) {
        view.c(isFinishing);
    }

    public static void destroy(YouTubePlayerView view, boolean isFinishing) {
        view.b(isFinishing);
    }
}

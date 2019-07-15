package com.sureshb.flutter_youtube_player;
import io.flutter.plugin.common.PluginRegistry.Registrar;

public class FlutterYoutubePlayerPlugin {
  public static void registerWith(Registrar registrar) {
    registrar
            .platformViewRegistry()
            .registerViewFactory(
                    "com.sureshb.flutteryoutubeplugin", new FlutterYoutubePlayerFactory(registrar.messenger(), registrar.activity()));

  }
}
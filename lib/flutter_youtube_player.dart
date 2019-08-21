import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class FlutterYoutubePlayer extends StatefulWidget {
  final String videoId;
  final String apiKey;
  final bool autoPlay;
  const FlutterYoutubePlayer({
    Key key,
    @required this.apiKey,
    @required this.videoId,
    this.autoPlay = true
  }) : super(key: key);

//
//  static Future<String> get platformVersion async {
//    final String version = await _channel.invokeMethod('getPlatformVersion');
//    return version;
//  }

  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return new _FlutterYoutubePlayerState();
  }
}

class _FlutterYoutubePlayerState extends State<FlutterYoutubePlayer>  with WidgetsBindingObserver {
  MethodChannel _channel;


  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addObserver(this);
  }

  @override
  void dispose() {
    WidgetsBinding.instance.removeObserver(this);
    super.dispose();
  }

  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    super.didChangeAppLifecycleState(state);
    switch(state){
      case AppLifecycleState.paused:
        print('paused state');
        if(_channel != null)
        {
          _channel.invokeMapMethod("pause");
        }
        break;
      case AppLifecycleState.resumed:
        print('resumed state');
        break;
      case AppLifecycleState.inactive:
        print('inactive state');
        break;
      case AppLifecycleState.suspending:
        print('suspending state');
        break;
    }
  }

  @override
  Widget build(BuildContext context) {
    if (defaultTargetPlatform == TargetPlatform.android) {

      Map <String, dynamic> settings = {
        "apiKey" : widget.apiKey,
        "videoId" : widget.videoId,
        "autoPlay" : widget.autoPlay,
      };

      return AspectRatio(
        aspectRatio: 16 / 9,
        child: AndroidView(
          viewType: 'com.sureshb.flutteryoutubeplugin',
          onPlatformViewCreated: _onPlatformViewCreated,
          creationParams: settings,
          creationParamsCodec: StandardMessageCodec(),
        ),
      );
    }
    return Text('$defaultTargetPlatform is not yet supported by the text_view plugin');
  }

  void _onPlatformViewCreated(int id) {
    print(id);
    _channel = new MethodChannel('com.sureshb.flutteryoutubeplugin/$id');
  }
}

import 'package:flutter/material.dart';
import 'package:flutter_youtube_player/flutter_youtube_player.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Column(
          children: <Widget>[
            Expanded(
              child: FlutterYoutubePlayer(
                apiKey: "api_key",
                videoId: "video_id",
              ),
            ),
            Expanded(
                child: Center(
              child: Text('Description of the Video'),
            )),
          ],
        ),
      ),
    );
  }
}

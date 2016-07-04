package codeblox.dropoff;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up video feed
        VideoView vidView = (VideoView)findViewById(R.id.myVideo);
        String vidAddress = "rtsp://admin:antigrav@192.168.1.107/profile5/media.smp";
        Uri vidUri = Uri.parse(vidAddress);


        //Controlls
        MediaController vidControl = new MediaController(this);
        vidControl.setAnchorView(vidView);
        vidView.setMediaController(vidControl);

        //Start Stream
        vidView.setVideoURI(vidUri);
        vidView.start();
    }
}

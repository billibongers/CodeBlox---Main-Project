package codeblox.dropoff;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    String vidAddress = "rtsp://admin:antigrav@192.168.1.107/profile3/media.smp";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up video feed
        VideoView vidView = (VideoView) findViewById(R.id.myVideo);
        Uri vidUri = Uri.parse(vidAddress);


        //Controlls
        MediaController vidControl = new MediaController(this);
        vidControl.setAnchorView(vidView);
        vidView.setMediaController(vidControl);

        //Start Stream
        vidView.setVideoURI(vidUri);
        vidView.start();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.max://Setting video to profile 1 which shouold be the best quality
                System.out.println("Max Enabled");
                vidAddress = "rtsp://admin:antigrav@192.168.1.107/profile1/media.smp";
                return true;

            case R.id.min://Setting video to profile 6 which shouold be the lowest quality
                System.out.println("Min Enabled");
                vidAddress = "rtsp://admin:antigrav@192.168.1.107/profile6/media.smp";
                return true;

            case R.id.open:
                // This will send a messages to the raspberry pi to open the gate
                //
                // todo

                try {
                    Socket socket = new Socket("192.168.1.102", 4444);//Start A Connection
                    DataOutputStream streamOut = new DataOutputStream(socket.getOutputStream());
                    streamOut.writeUTF("Testing :P If this message arrives it works");//Send Message
                    streamOut.flush();
                    streamOut.close();//Close Everything
                    socket.close();
                }catch(UnknownHostException err) {  System.out.println("Host unknown: " + err.getMessage()); }//Catch errors
                catch(IOException err){  System.out.println("Unexpected exception: " + err.getMessage());}
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://codeblox.dropoff/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://codeblox.dropoff/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

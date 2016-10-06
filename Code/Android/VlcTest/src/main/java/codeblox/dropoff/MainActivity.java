package codeblox.dropoff;

import android.app.Dialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {
    String movieurl = "";
    String password = "";
    String serverip = "10.8.0.1";
    String username = "";
    String pinDia = "";
    Dialog PinDialog;
    int p = 0;
    String tempIn = "N/a";
    Client myClient;
    Uri vidUri;
    VideoView vidView;
    MediaPlayer mediaPlayer;
    SurfaceHolder surfaceHolder;
    SurfaceView playerSurfaceView;
    String videoSrc = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";//"http://10.8.0.6:8090/";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    //Connection to server that runs when the application is started
    public MainActivity() {
        // setUp();
        myClient = new Client(serverip, 6663);
        myClient.setClientCallback(new Client.ClientCallback() {
            @Override
            public void onMessage(String message) {
                tempIn = message;
                myClient.send('0');// send message as i recieve a message ... restful i guess :P
            }

            @Override
            public void onConnect(Socket socket) {
                myClient.send('1');

            }

            @Override
            public void onDisconnect(Socket socket, String message) {
                // myClient.send("KTNKSBAI\n");
            }

            @Override
            public void onConnectError(Socket socket, String message) {
            }
        });

        myClient.connect();
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // onCreateee(savedInstanceState);
        super.onStart();
        setUp();
        setPin();//For the pop up
        getPin();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    private void setPin() {
        PinDialog = new Dialog(this);
        PinDialog.setContentView(R.layout.pin);
        PinDialog.setTitle("Pin");

        Button button = (Button) PinDialog.findViewById(R.id.OkayPin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PinDialog.dismiss();
            }
        });
    }


    private void getPin() {//Gets pin from RBP
        URL url = null;
        try {
           // url = new URL(piIP + ":55555");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            int statusCode = urlConnection.getResponseCode();
            InputStream it = new BufferedInputStream(urlConnection.getInputStream());
            InputStreamReader read = new InputStreamReader(it);
            BufferedReader buff = new BufferedReader(read);
            StringBuilder dta = new StringBuilder();
            String chunks;
            while ((chunks = buff.readLine()) != null) {
                dta.append(chunks);
            }
            pinDia = "One Time Pin is " + chunks;
        } catch (Exception e) {
            if (p == 0) {
               // pinDia = "One Time Pin is " + piIP;
                p = 1;
            } else pinDia = "Pin Could not be generated";

        }
    }

    public void setUp() {
        //Currently just for testing to get IP address of camera and pi
        //ToDo Allow camer to steram throgh the pi and the pi will connect to the master server at later stage
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("SetUp");

        Button b = (Button) findViewById(R.id.play_video);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText e = (EditText) findViewById(R.id.video_url);
                String url = e.getText().toString();

                Intent intent = new Intent(getApplicationContext(),codeblox.dropoff.VideoVLCActivity.class);
                intent.putExtra("videoUrl", url);
                startActivity(intent);
            }
        });

        Button button = (Button) dialog.findViewById(R.id.Button01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                //Get TExt
                EditText IP = (EditText) dialog.findViewById(R.id.IP);
                EditText RBPIP = (EditText) dialog.findViewById(R.id.RBPIP);
                username = IP.getText().toString().trim();
                password = RBPIP.getText().toString().trim();

                System.out.println("RBP " + RBPIP.getText().toString().trim() + "   Cam " + IP.getText().toString().trim());
                //  serverip=vidAddress ;
              //  movieurl = "http://10.8.0.6:8090/" ;//"https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";// "rtsp://192.168.1.145:8554/";//"https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";

                //Set up video feed
                //vidUri = Uri.parse(movieurl);
                //vidView = (VideoView) findViewById(R.id.myVideo);
                //Start Stream
                //   vidView.setVideoURI(movieurl);
                //vidView.setVideoURI(Uri.parse(movieurl));
                //vidView.start();

                //    vidView.start();
                //   vidView.requestFocus();
                // if (movieurl.startsWith("rtsp://")) {
                // Intent intent = new Intent(Intent.ACTION_VIEW, vidUri);
                //startActivity(intent);
                //    }


            }
        });

        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//Menu button
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
        switch (item.getItemId()) {
            case R.id.max://Setting video to profile 1 which shouold be the best quality
                //  System.out.println("Connect");
                myClient.send('2');// send a message to the server for them to process this was the max video button
                return true;
            case R.id.close://Setting video to profile 1 which shouold be the best quality
                //  System.out.println("Connect");
                myClient.send('3');// send a message to the server for them to process this was the max video button
                return true;

            case R.id.min://Setting video to profile 6 which shouold be the lowest quality
                //  System.out.println("Min Enabled");
                myClient.disconnect();//disconnect from server
                //  myClient.disconnect();
                return true;

            case R.id.pinBtn://Showing the pin
                //  System.out.println("Pin Please");
                TextView pin = (TextView) PinDialog.findViewById(R.id.pin);
                pin.setText(tempIn);// DISPLAY the message sent from the node server ... this was the get pin button
                //pin.setText(pinDia);
                PinDialog.show();
                //vidAddress = piIP;
                return true;


            case R.id.open:
                // This will send a messages to the raspberry pi to open the gate
                //
                // todo Do someting with this i guess it may help in the future or be useful in a distant time :P
                myClient.connect();
                //          try {
//                    Socket socket = new Socket("192.168.1.102", 4444);//Start A Connection
                //                   DataOutputStream streamOut = new DataOutputStream(socket.getOutputStream());
                //                 streamOut.writeUTF("Testing :P If this message arrives it works");//Send Message
                //                   streamOut.flush();
                //                  streamOut.close();//Close Everything
                //            socket.close();
                //         }catch(Exception e) {   }//Catch errors
                return true;
            default:
                return super.onOptionsItemSelected(item);*/
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
        }



    public void doIt() {
        //  System.out.println("Made it to the doIt() function");


        //final Client myClient = new Client("192.168.0.8", 1234);

        //  socket.setClientCallback(new Client.ClientCallback () {
        //         @Override
        //       public void onMessage(String message) {
        //        }

        //       @Override
        //       public void onConnect(Socket socket1) {
        //   socket.send("Hello World!\n");
        //    socket.disconnect();
        //       }

        //       @Override
        ///       public void onDisconnect(Socket socket, String message) {
        //     }
//
        //     @Override
        //      public void onConnectError(Socket socket, String message) {
        //       }
        //   });

        // socket.connect();
    }


    public void onCreateee(Bundle savedInstanceState) {
        // super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);
        playerSurfaceView = (SurfaceView) findViewById(R.id.surfView);

        surfaceHolder = playerSurfaceView.getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDisplay(surfaceHolder);
                mediaPlayer.setDataSource(videoSrc);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }




}

package codeblox.dropoff;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class MainActivity extends AppCompatActivity {
    String movieurl = "";
    String imageUrl = "http://10.8.0.6/cam.jpg";
    String serverip = "10.8.0.1";
    String piIP = "";
    String pinDia = "";
    Dialog PinDialog;
    int p = 0;
    String tempIn = "N/a";
    Client myClient;
    Uri vidUri;
    VideoView vidView;
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

        super.onStart();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
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
            url = new URL(piIP + ":55555");
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
                pinDia = "One Time Pin is " + piIP;
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

        Button button = (Button) dialog.findViewById(R.id.Button01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                //Get TExt
                EditText IP = (EditText) dialog.findViewById(R.id.IP);
                EditText RBPIP = (EditText) dialog.findViewById(R.id.RBPIP);
                movieurl = IP.getText().toString().trim();
                piIP = RBPIP.getText().toString().trim();

                System.out.println("RBP " + RBPIP.getText().toString().trim() + "   Cam " + IP.getText().toString().trim());
                //  serverip=vidAddress ;
                //  movieurl ="rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov";// "rtsp://192.168.1.145:8554/";//"https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";

                //Set up video feed
                //   vidUri = Uri.parse(movieurl);
                //   vidView = (VideoView)findViewById(R.id.myVideo);
                //Start Stream
                //   vidView.setVideoURI(vidUri);
                //    vidView.start();

                work();
              //  };

                //    vidView.setVideoURI(Uri.parse(vidAddress));

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


    private Drawable LoadImageFromWebOperations(String url) {

        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            System.out.println("Exc=" + e);
            return null;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//Menu button
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.max://Setting video to profile 1 which shouold be the best quality
                //  System.out.println("Connect");
                myClient.send('2');// send a message to the server for them to process this was the max video button
                Thread thread = new Thread() {
                    @Override
                    public void run() {int y =0; while (y<10) { try { Thread.sleep(2000);
                        work(); y++;} catch (InterruptedException e) {
                        System.out.println(""+ e);  }}
                    }
                };

                thread.start();
                return true;
            case R.id.close://Setting video to profile 1 which shouold be the best quality
                //  System.out.println("Connect");
                myClient.send('3');// send a message to the server for them to process this was the max video button
                Thread thread3 = new Thread() {
                    @Override
                    public void run() {int y =0; while (y<10) { try { Thread.sleep(2000);
                        work(); y++;} catch (InterruptedException e) {
                        System.out.println(""+ e);  }}
                    }
                };

                thread3.start();
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
                Thread thread2 = new Thread() {
                @Override
                public void run() {int y =0; while (y<10) { try { Thread.sleep(2000);
                work(); y++;} catch (InterruptedException e) {
                System.out.println(""+ e);  }}
                }
            };

                thread2.start();
                //vidAddress = piIP;
                return true;


            case R.id.open:
                // This will send a messages to the raspberry pi to open the gate
                //
                // todo Do someting with this i guess it may help in the future or be useful in a distant time :P
                myClient.connect();
                Thread thread4 = new Thread() {
                    @Override
                    public void run() {int y =0; while (y<10) { try { Thread.sleep(2000);
                        work(); y++;} catch (InterruptedException e) {
                        System.out.println(""+ e);  }}
                    }
                };

                thread4.start();
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
                return super.onOptionsItemSelected(item);
        }
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

public void work()
{

    runOnUiThread(new Runnable() {
        @Override
        public void run() {

            //     }

            //  Thread thread = new Thread() {
            //     @Override
            //public void run() {

            //  while(true) {
            //
            try {
               // Thread.sleep(1000);
                ImageView i = (ImageView) findViewById(R.id.imagein);
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(imageUrl).getContent());
                //Drawable drawable = LoadImageFromWebOperations(imageUrl);
                i.setImageBitmap(bitmap);
            } catch (Exception e) {
                System.out.println("Exc=" + e);
                // return null;
            }


        }});
}

}

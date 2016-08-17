package codeblox.dropoff;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String vidAddress = "";
    String camIP="";
    String piIP="";
    String pinDia="";
    Dialog PinDialog ;
    int p=0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        super.onStart();
        setUp();
        setPin();//For the pop up
        getPin();

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
            url = new URL(piIP+":55555");
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.setRequestMethod("GET");
        int statusCode = urlConnection.getResponseCode();
            InputStream it = new BufferedInputStream(urlConnection.getInputStream());
            InputStreamReader read = new InputStreamReader(it);
            BufferedReader buff = new BufferedReader(read);
            StringBuilder dta = new StringBuilder();
            String chunks ;
            while((chunks = buff.readLine()) != null)
            {
                dta.append(chunks);
            }
            pinDia="One Time Pin is "+chunks;
        } catch (Exception e) {
            if (p==0) {
                pinDia = "One Time Pin is " + piIP;
                p=1;
            }
                else     pinDia="Pin Could not be generated";

        }
    }

    public void setUp()
    { //Currently just for testing to get IP address of camera and pi
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
                EditText IP = (EditText)dialog.findViewById(R.id.IP);
                EditText RBPIP = (EditText)dialog.findViewById(R.id.RBPIP);
                vidAddress = IP.getText().toString().trim();
                 piIP = RBPIP.getText().toString().trim();

                System.out.println("RBP "+RBPIP.getText().toString().trim()+"   Cam "+ IP.getText().toString().trim());
               camIP=vidAddress ;

                //Set up video feed
                VideoView vidView = (VideoView) findViewById(R.id.myVideo);
                Uri vidUri = Uri.parse(vidAddress);

                //Start Stream
                vidView.setVideoURI(vidUri);
                vidView.start();
                // ATTENTION: This was auto-generated to implement the App Indexing API.
                // See https://g.co/AppIndexing/AndroidStudio for more information.



            }
        });

        dialog.show();
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
                vidAddress = camIP;
                return true;

            case R.id.min://Setting video to profile 6 which shouold be the lowest quality
                System.out.println("Min Enabled");
                vidAddress = camIP;
                return true;

            case R.id.pinBtn://Showing the pin
                System.out.println("Pin Please");
                TextView pin = (TextView)PinDialog.findViewById(R.id.pin);
               // pin.setText("The Curent Pin is "+ piIP);
                pin.setText(pinDia);
                PinDialog.show();
                vidAddress = camIP;
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
                }catch(Exception e) {   }//Catch errors
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

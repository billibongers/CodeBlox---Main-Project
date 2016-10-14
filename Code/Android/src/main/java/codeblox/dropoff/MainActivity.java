//Created By Lorenzo Spazzoli and The Codeblox COS301 Team
package codeblox.dropoff;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.InputStream;
import java.net.Socket;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    //GLOBAL VARIABLES
    String imageUrl = "http://10.8.0.6/cam.jpg";//Location on the pi that the ip pic is pulled from TODO Ask server to change this?
    String serverip = "10.8.0.1";int port=6663;
    String username, password;Dialog msgDialog;
    String tempIn = "N/a";//Used to a the message from the server to display it
    Client myClient;

    public MainActivity() {//Connection to server that runs when the application is started
        myClient = new Client(serverip, port);
        myClient.setClientCallback(new Client.ClientCallback() {
            @Override
            public void onMessage(String message) {
                tempIn = message;
                myClient.send('0');// send message as i recieve a message ... restful i guess :P
            }
            @Override
            public void onConnect(Socket socket) { myClient.send('1');}
            @Override
            public void onDisconnect(Socket socket, String message) { }
            @Override
            public void onConnectError(Socket socket, String message) { }
        });
        myClient.connect();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        super.onStart();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setUp();
        setPin();
    }

    private void setPin() {//The Message From the Server to be displayed...Can dispaly error codes
        msgDialog = new Dialog(this);
        msgDialog.setContentView(R.layout.pin);
        msgDialog.setTitle("Message From Server");

        Button button = (Button) msgDialog.findViewById(R.id.okBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msgDialog.dismiss(); }
        });
    }

    public void setUp() {//This is the Login Dialog

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Login");
        Button button = (Button) dialog.findViewById(R.id.Button01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                //Get TExt
                EditText user = (EditText) dialog.findViewById(R.id.user);
                EditText pass = (EditText) dialog.findViewById(R.id.pass);
                username = pass.getText().toString().trim();
                password = pass.getText().toString().trim();
                //TODO On click validate the login details and show loding ?
                work();//Load Image
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
        switch (item.getItemId()) {
            case R.id.open:
                myClient.send('2');// send an open message to the server
                loadImages(); //Reload Image
                return true;
            case R.id.close:
                myClient.send('3');// send a message to the server this is the close message
                loadImages(); //Reload Image
                return true;

            case R.id.dc://disconnect from server
                myClient.disconnect();
                return true;

            case R.id.msgBtn://Showing the Message
               TextView msg = (TextView) msgDialog.findViewById(R.id.msg);
                msg.setText(tempIn);// DISPLAY the message sent from the node server ... this could be used for the pin system
                msgDialog.show();
               loadImages(); //Reload Image
                return true;
            case R.id.reconnect: // Reconnect to the server

                myClient.connect();
                loadImages();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadImages() {//USed to load the images using a thread
        Thread thread = new Thread() {
            @Override
            public void run() {int y =0; while (y<10) { try { Thread.sleep(2000);
                work(); y++;} catch (InterruptedException e) {
                System.out.println(""+ e);  }}
            }
        };
        thread.start();
    }

    public void work(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    ImageView i = (ImageView) findViewById(R.id.imagein);
                    Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(imageUrl).getContent());
                    i.setImageBitmap(bitmap);
                } catch (Exception e) { System.out.println("Exc=" + e);  }
            }});
    }

}

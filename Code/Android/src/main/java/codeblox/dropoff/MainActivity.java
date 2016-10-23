//Created By Lorenzo Spazzoli and The Codeblox COS301 Team
package codeblox.dropoff;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
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
    Dialog msgDialog;
  //  TextView msg;
    int[] Buttonstate={0,0,0,0,0,0,0,0,0,0};
    String username, password;
    String tempIn = "N/a";//Used to a the message from the server to display it
    Client myClient;
    NotificationManager manager;
    Notification myNotication;
    //MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        super.onStart();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setUp();//This is the Login Dialog used to connect to different server and pi conbinations of ip address
        //loading////////////////

        try {
            ImageView i = (ImageView) findViewById(R.id.imagein);//https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSt-tm41LicaVb8TM0_kGzxV9l6VMwqTXr1Q7whE5Zoxnlbo4iReQ
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSt-tm41LicaVb8TM0_kGzxV9l6VMwqTXr1Q7whE5Zoxnlbo4iReQ").getContent());
            i.setImageBitmap(bitmap);
        } catch (Exception e) { System.out.println("Exc=" + e);  }
        ///////////////////////////////////
        setPin();//The Message From the Server to be displayed...Can dispaly error codes
    }

    private void setPin() {//The Message From the Server to be displayed...Can dispaly error codes
        msgDialog = new Dialog(this);
        msgDialog.setContentView(R.layout.pin);
        msgDialog.setTitle("Message From Server");

        Button button = (Button) msgDialog.findViewById(R.id.okBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msgDialog.dismiss();
                manager.cancel(11);
            }
        });
      // TextView msg  = (TextView) msgDialog.findViewById(R.id.msg);
    }

    public void setUp() {//This is the Login Dialog

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Where?");
        Button button = (Button) dialog.findViewById(R.id.Button01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                //Get TExt
                EditText user = (EditText) dialog.findViewById(R.id.serve);
                EditText pass = (EditText) dialog.findViewById(R.id.pi);
                username = user.getText().toString().trim();
                password = pass.getText().toString().trim();
                //TODO On click validate the login details and show loding ?
                imageUrl = password;
                serverip=username;
                ConnectToserver();
                loadImages(); //Load Image
            }
        });
        dialog.show();
    }


    public void ConnectToserver() {//Connection to server that runs when the application is started
        myClient = new Client(serverip, port);
        myClient.setClientCallback(new Client.ClientCallback() {
            @Override
            public void onMessage(String message) {
                tempIn = message;
                NotifiyOnStatusOfServer(tempIn);
                myClient.send('0');// send message as i recieve a message ... restful i guess :
                work2();
              //  msgDialog.show();
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
    public boolean onCreateOptionsMenu(Menu menu) {//Menu button
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ioGate:
                if (Buttonstate[0]==0)
                {
                myClient.send('a');// send an open message to the server
                    item.setTitle("Close Gate");
                    Buttonstate[0]=1;
                     }
                else {
                    myClient.send('b');// send a message to the server this is the close message
                    item.setTitle("Open Gate");
                    Buttonstate[0]=0;
                }
                return true;
            case R.id.ioSwitch1:
                if (Buttonstate[1]==0)
                {
                    myClient.send('c');// send an open message to the server
                    item.setTitle("Switch 1 0ff");
                    Buttonstate[1]=1;
                }
                else {
                    myClient.send('d');// send a message to the server this is the close message
                    item.setTitle("Switch 1 On");
                    Buttonstate[1]=0;
                }
                return true;
            case R.id.ioSwitch2:
                if (Buttonstate[2]==0)
                {
                    myClient.send('e');// send an open message to the server
                    item.setTitle("Switch 2 0ff");
                    Buttonstate[2]=1;
                }
                else {
                    myClient.send('f');// send a message to the server this is the close message
                    item.setTitle("Switch 2 On");
                    Buttonstate[2]=0;
                }
                return true;
            case R.id.ioSwitch3:
                if (Buttonstate[3]==0)
                {
                    myClient.send('g');// send an open message to the server
                    item.setTitle("Switch 3 0ff");
                    Buttonstate[3]=1;
                }
                else {
                    myClient.send('h');// send a message to the server this is the close message
                    item.setTitle("Switch 3 On");
                    Buttonstate[3]=0;
                }
                return true;
            case R.id.ioSwitch4:
                if (Buttonstate[4]==0)
                {
                    myClient.send('i');// send an on message to the server
                    item.setTitle("Switch 4 0ff");
                    Buttonstate[4]=1;
                }
                else {
                    myClient.send('j');// send a message to the server
                    item.setTitle("Switch 4 On");
                    Buttonstate[4]=0;
                }
                return true;
            case R.id.ioSwitch5:
                if (Buttonstate[5]==0)
                {
                    int x=13;
                    myClient.send('k');// send a message to the server
                    item.setTitle("Switch 5 0ff");
                    Buttonstate[5]=1;
                }
                else {
                    int x=12;
                    myClient.send('l');// send a message to the server
                    item.setTitle("Switch 5 On");
                    Buttonstate[5]=0;
                }
                return true;
            case R.id.ioSwitch6:
                if (Buttonstate[6]==0)
                {
                    int x=15;
                    myClient.send('m');// send a message to the server
                    item.setTitle("Switch 6 0ff");
                    Buttonstate[6]=1;
                }
                else {
                    myClient.send('n');// send a message to the server
                    item.setTitle("Switch 6 On");
                    Buttonstate[6]=0;
                }
                return true;
            case R.id.ioSwitch7:
                if (Buttonstate[7]==0)
                {
                    myClient.send('o');// send a message to the server
                    item.setTitle("Switch 7 0ff");
                    Buttonstate[7]=1;
                }
                else {

                    myClient.send('p');// send a message to the server
                    item.setTitle("Switch 7 On");
                    Buttonstate[7]=0;
                }
                return true;
            case R.id.ioSwitch8:
                if (Buttonstate[8]==0)
                {
                    myClient.send('q');// send a message to the server
                    item.setTitle("Switch 8 0ff");
                    Buttonstate[8]=1;
                }
                else {
                    myClient.send('r');// send a message to the server
                    item.setTitle("Switch 8 On");
                    Buttonstate[8]=0;
                }
                return true;

            case R.id.dc://Showing the Message

                if (Buttonstate[9]==0)
                {
                    myClient.disconnect();
                    item.setTitle("Connect");
                    Buttonstate[9]=1;
                }
                else {

                    myClient.connect();
                    item.setTitle("Disconnect");
                    Buttonstate[9]=0;
                }
                return true;
            case R.id.lastNotifi: // Reconnect to the server
                TextView msg  = (TextView) msgDialog.findViewById(R.id.msg);
                manager.cancel(11);
                msg.setText(tempIn);// DISPLAY the message sent from the node server ... this could be used for the pin system
                msgDialog.show();
                //   loadImages(); //Reload Image
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadImages() {//USed to load the images using a thread
        Thread thread = new Thread() {
            @Override
            public void run() {int y =0; while (y<10000) { try { Thread.sleep(3000);
                work(); y++;} catch (InterruptedException e) {
                System.out.println("Temp"+ e);  }}
            }
        };
        thread.start();
    }
   // private void dindong()//Used to make a notification noise
  //  {//USed to load the images using a thread
    //    Thread thread = new Thread() {
          //  @Override
      //      public void run() {
    //            try {
    //           mp.reset();
    //                mp.setDataSource("http://www.orangefreesounds.com/wp-content/uploads/2015/08/Doorbell-ding-dong-sound-effect.mp3");
    //                mp.prepare();
           //     mp.start();
    //           // Thread.sleep(15000);
   //         } catch (Exception e) { System.out.println("Exc=" + e);  }
    //        }
    //    };
   //     thread.start();
 //   }

    public void work(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    ImageView i = (ImageView) findViewById(R.id.imagein);//https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSt-tm41LicaVb8TM0_kGzxV9l6VMwqTXr1Q7whE5Zoxnlbo4iReQ
                    Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(imageUrl).getContent());
                    i.setImageBitmap(bitmap);
                } catch (Exception e) { System.out.println("Exc=" + e);  }
            }});
    }

    public void work2(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(150);
                   //used to avoid dead lock with the menu closing
                  //
                    TextView msg  = (TextView) msgDialog.findViewById(R.id.msg);
                    msg.setText(tempIn);// DISPLAY the message sent from the node server ... this could be used for the pin system
                    msgDialog.show();
                   // Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                   //long[] pattern = {0,600, 400, 200, 400,600};
                    // Vibrate for 500 milliseconds
                   // v.vibrate(500);
                  //  v.vibrate(pattern, -1);
                  // dindong(); --------------------------------ALllow for sounds ////////////=-------------------------------------------------------------------------------------------------------------
                 //
                   // mp.stop();
                } catch (Exception e) { System.out.println("Exc=" + e);  }
            }});
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void NotifiyOnStatusOfServer(String msg) {
        //API level 11
        Intent intent = new Intent("com.rj.notitfications.SECACTIVITY");

        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 1, intent, 0);

        Notification.Builder builder = new Notification.Builder(MainActivity.this);

        builder.setAutoCancel(false);
        builder.setTicker("this is ticker text");
        builder.setContentTitle("Home Notification");
        builder.setContentText(msg);
        builder.setSmallIcon(R.drawable.abc);
        builder.setContentIntent(pendingIntent);
        builder.setOngoing(true);
       // builder.setSubText("You home is on fire .. Oh no");   //API level 16 Only if needed
        builder.setNumber(100);
        builder.build();


        myNotication = builder.getNotification();
        manager.notify(11, myNotication);



                /*
                //API level 8
                Notification myNotification8 = new Notification(R.drawable.ic_launcher, "this is ticker text 8", System.currentTimeMillis());

                Intent intent2 = new Intent(MainActivity.this, SecActivity.class);
                PendingIntent pendingIntent2 = PendingIntent.getActivity(getApplicationContext(), 2, intent2, 0);
                myNotification8.setLatestEventInfo(getApplicationContext(), "API level 8", "this is api 8 msg", pendingIntent2);
                manager.notify(11, myNotification8);
                */

    }

}


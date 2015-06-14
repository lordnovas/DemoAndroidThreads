package com.demo.joel.demoandroidthreads;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Title: DemoThreads Practice project
 * Purpose: Practice Threads, Runnable, and Handlers 
 */

public class MainActivity extends Activity
{
    private Bitmap mBitmap;
    private ImageView mImageView;
    private int mDelay = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView)findViewById(R.id.iv_smileFace);

        final Button bt_loadIV =(Button) findViewById(R.id.bt_loadImg);
        bt_loadIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImg();
            }
        });

        final Button bt_pressMe = (Button)findViewById(R.id.bt_pressMe);
        bt_pressMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "I'm Working",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private  void loadImg()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(mDelay);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //Get the Img
                mBitmap = BitmapFactory.decodeResource(getResources(),
                        R.drawable.smileimg);

                //Post a new Runnable(some work) to the UI thread

                mImageView.post(new Runnable() {
                    @Override
                    public void run() {
                        mImageView.setImageBitmap(mBitmap);
                    }
                });

            }
        }).start();

    }

}

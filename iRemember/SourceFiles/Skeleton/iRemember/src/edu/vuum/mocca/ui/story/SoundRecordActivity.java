/*
The iRemember source code (henceforth referred to as "iRemember") is
copyrighted by Mike Walker, Adam Porter, Doug Schmidt, and Jules White
at Vanderbilt University and the University of Maryland, Copyright (c)
2014, all rights reserved.  Since iRemember is open-source, freely
available software, you are free to use, modify, copy, and
distribute--perpetually and irrevocably--the source code and object code
produced from the source, as well as copy and distribute modified
versions of this software. You must, however, include this copyright
statement along with any code built using iRemember that you release. No
copyright statement needs to be provided if you just ship binary
executables of your software products.

You can use iRemember software in commercial and/or binary software
releases and are under no obligation to redistribute any of your source
code that is built using the software. Note, however, that you may not
misappropriate the iRemember code, such as copyrighting it yourself or
claiming authorship of the iRemember software code, in a way that will
prevent the software from being distributed freely using an open-source
development model. You needn't inform anyone that you're using iRemember
software in your software, though we encourage you to let us know so we
can promote your project in our success stories.

iRemember is provided as is with no warranties of any kind, including
the warranties of design, merchantability, and fitness for a particular
purpose, noninfringement, or arising from a course of dealing, usage or
trade practice.  Vanderbilt University and University of Maryland, their
employees, and students shall have no liability with respect to the
infringement of copyrights, trade secrets or any patents by DOC software
or any part thereof.  Moreover, in no event will Vanderbilt University,
University of Maryland, their employees, or students be liable for any
lost revenue or profits or other special, indirect and consequential
damages.

iRemember is provided with no support and without any obligation on the
part of Vanderbilt University and University of Maryland, their
employees, or students to assist in its use, correction, modification,
or enhancement.

The names Vanderbilt University and University of Maryland may not be
used to endorse or promote products or services derived from this source
without express written permission from Vanderbilt University or
University of Maryland. This license grants no permission to call
products or services derived from the iRemember source, nor does it
grant permission for the name Vanderbilt University or
University of Maryland to appear in their names.
 */

package edu.vuum.mocca.ui.story;

import java.io.IOException;

import edu.vanderbilt.mooc.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

// http://developer.android.com/guide/topics/media/audio-capture.html
/**
 * Activity to capture Audio
 * 
 * @author Scott
 * @see <a href=
 *      'http://developer.android.com/guide/topics/media/audio-capture.html'>http://developer.android.com/guide/topics/media/audio-capture.ht
 *      m l < / a >
 */
public class SoundRecordActivity extends Activity {

    private static final String LOG_TAG = SoundRecordActivity.class.getName();
    public static final String EXTRA_OUTPUT = "OUTPUT_FILENAME";
    private static String mFileName = null;
    
    private boolean recorded = false;

    boolean mStartRecording = true;
    boolean mStartPlaying = true;
    private Button mRecordButton = null;
    private MediaRecorder mRecorder = null;

    private Button mPlayButton = null;
    private MediaPlayer mPlayer = null;

    private  void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        recorded = true;
    }
/*
    // The Record Button & its logic
    static public class RecordButton extends Button {
        boolean mStartRecording = true;

        

        public RecordButton(Context ctx) {
            super(ctx);
            setText("Start recording");
            setOnClickListener(clicker);
        };
        
        public RecordButton (Context ctx, AttributeSet attrs) {
            super(ctx, attrs);
            // TODO Auto-generated constructor stub
            setText("Start recording");
            setOnClickListener(clicker);
        };

        public RecordButton (Context ctx, AttributeSet attrs, int defStyle) {
            super(ctx, attrs, defStyle);
            // TODO Auto-generated constructor stub
            setText("Start recording");
            setOnClickListener(clicker);
            };
            
        OnClickListener clicker = new OnClickListener() {
                public void onClick(View v) {
             //       onRecord(mStartRecording);
                    if (mStartRecording) {
                        setText("Stop recording");
                    } else {
                        Intent data = new Intent();
                        data.putExtra("data", mFileName);
               //         setResult(RESULT_OK, data);
                        setText("Start recording");
                    }
                    mStartRecording = !mStartRecording;
                }
            };
    }

    // The Play button & its logic
   static public class PlayButton extends Button {
        

        

        public PlayButton(Context ctx) {
            super(ctx);
            setText("Start playing");
            setOnClickListener(clicker);
        }
        
        public PlayButton (Context ctx, AttributeSet attrs) {
            super(ctx, attrs);
            // TODO Auto-generated constructor stub
            setText("Start playing");
            setOnClickListener(clicker);
        }

        public PlayButton (Context ctx, AttributeSet attrs, int defStyle) {
            super(ctx, attrs, defStyle);
            // TODO Auto-generated constructor stub
            setText("Start playing");
            setOnClickListener(clicker);
            }
        OnClickListener clicker = new OnClickListener() {
            public void onClick(View v) {
              //  onPlay(mStartPlaying);
                if (mStartPlaying) {
                    setText("Stop playing");
                } else {
                    setText("Start playing");
                }
                mStartPlaying = !mStartPlaying;
            }
        };
    }
*/
    @Override
    /**
     * Sets up Activity's View
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_sound_record);
       // LinearLayout ll = new LinearLayout(this);
        mRecordButton = (Button) findViewById(
				R.id.story_record_button); //= new RecordButton(this);
        //ll.addView(mRecordButton, new LinearLayout.LayoutParams(
        //        ViewGroup.LayoutParams.WRAP_CONTENT,
        //        ViewGroup.LayoutParams.WRAP_CONTENT, 0));
        
        mRecordButton.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v) {
            onRecord(mStartRecording);
            if (mStartRecording) {
            	mRecordButton.setText("Stop recording");
            } else {
                Intent data = new Intent();
                data.putExtra("data", mFileName);
                setResult(RESULT_OK, data);
                mRecordButton.setText("Start recording");
            }
            mStartRecording = !mStartRecording;
        }}
        );
        
        mPlayButton = (Button) findViewById(
				R.id.story_play_button);;
       // ll.addView(mPlayButton, new LinearLayout.LayoutParams(
         //       ViewGroup.LayoutParams.WRAP_CONTENT,
       //         ViewGroup.LayoutParams.WRAP_CONTENT, 0));
       // setContentView(ll);
		mPlayButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                  onPlay(mStartPlaying);
                  if (mStartPlaying) {
                	  mPlayButton.setText("Stop playing");
                  } else {
                	  mPlayButton.setText("Start playing");
                  }
                  mStartPlaying = !mStartPlaying;
              }
          
		});
        

    	Intent caller = getIntent();
    	mFileName = caller.getStringExtra(EXTRA_OUTPUT);
    	
		Log.i("zzz", mFileName);
    }

    @Override
    /**
     * Handle onPause to release the media Recorder and Player instances.
     * @see android.app.Activity#onPause()
     */
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    @Override
    /**
     * @see android.app.Activity#onStop()
     */
    public void onStop() {
        super.onStop();
    }
    
    @Override
    public void onBackPressed() {
    	if(recorded == false){
    		setResult(RESULT_CANCELED, null);
    	}
    	super.onBackPressed();
    }
}

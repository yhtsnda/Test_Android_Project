package com.anzh.mybutton;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends Activity {
	private SlidingButton mainbutton;
	private boolean isPaly;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mainbutton = (SlidingButton) findViewById(R.id.mainview_button);
        SoundPlayer.getInstance().loadSound(this);
    }
    @Override
	public boolean onTouchEvent(MotionEvent event) {
		if(mainbutton.handleActivityEvent(event)){
			if(!isPaly){
				isPaly = true;
				SoundPlayer.getInstance().play(SoundPlayer.LOOP);
			}else{
				isPaly = false;
				SoundPlayer.getInstance().stop();
			}
			
		}
		return true;
	}

}
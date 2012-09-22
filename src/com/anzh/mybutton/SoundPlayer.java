package com.anzh.mybutton;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {
	public static int BTN;
	public static int LOOP;

	private SoundPool soundPool;
	private int loopStreamId;

	private static SoundPlayer instance;

	public static SoundPlayer getInstance() {
		synchronized (SoundPlayer.class) {
			if (instance == null) {
				instance = new SoundPlayer();
			}
		}
		return instance;
	}

	// 单例
	private SoundPlayer() {
		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
	}

	public void loadSound(Context context) {
		BTN = soundPool.load(context, R.raw.button, 1);
		LOOP = soundPool.load(context, R.raw.loop, 1);
	}

	public void play(int soundId) {
		if (soundId == LOOP) {
			// 循环播放
			soundPool.stop(loopStreamId);
			loopStreamId = soundPool.play(soundId, 1f, 1f, 1, -1, 1f);
		} else {
			// 只播放一次
			soundPool.play(soundId, 1f, 1f, 1, 0, 1f);
		}
	}
	
	public void stop() {
		soundPool.stop(loopStreamId);
	}

}

package com.anzh.mybutton;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class SlidingButton extends Button {

	float offset;

	public SlidingButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SlidingButton(Context context) {
		super(context);
	}

	private boolean isMe = false;
	private int limit = 236;

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public boolean isMe() {
		return isMe;
	}

	public void setMe(boolean isMe) {
		this.isMe = isMe;
	}

	public SlidingButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			offset = event.getX();
			System.out.println("OnTouch offset:"+offset);
			isMe = true;
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			isMe = false;
		}
		return false;
	}

	public boolean handleActivityEvent(MotionEvent activityEvent) {
		boolean result = false;
		if (isMe()) {
			if (activityEvent.getAction() == MotionEvent.ACTION_UP) {
               
				int newX = (int) activityEvent.getX();
				System.out.println("handleActivityEvent newX:"+newX);
				if (newX < getLimit() && newX > 50) {
					LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) getLayoutParams();
					TranslateAnimation trans = new TranslateAnimation(
							Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE,
							-lp.leftMargin, Animation.RELATIVE_TO_SELF, 0,
							Animation.RELATIVE_TO_SELF, 0);
					trans.setStartOffset(0);
					trans.setDuration(500);
					trans.setFillBefore(true);
					trans.setAnimationListener(new SlidingAnimationListener(
							this));
					startAnimation(trans);
				} else if (newX >= getLimit()) {
					//SoundPlayer.getInstance().play(SoundPlayer.SLIDE);
					LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) getLayoutParams();
					lp.leftMargin = 0;
					setLayoutParams(lp);
					isMe = false;
					//用户完成了选择动作
					result = true;
				}
			} else {
				// 还在拖动
				LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) getLayoutParams();
				lp.leftMargin = (int) (activityEvent.getX() - offset-5);
				System.out.println("lp.leftMargin:"+lp.leftMargin);
//				if (lp.leftMargin > 0 && lp.leftMargin < (367-149)) {
//					setLayoutParams(lp);
//				}
				if (lp.leftMargin > 0 && lp.leftMargin < (198)) {
				setLayoutParams(lp);
			}
				
			}
		}
		return result;

	}

	private static class SlidingAnimationListener implements AnimationListener {

		private SlidingButton but;

		public SlidingAnimationListener(SlidingButton button) {
			this.but = button;
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			rePosition();
			but.setMe(false);
		}

		private void rePosition() {
			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) but
					.getLayoutParams();
			lp.leftMargin = 0;
			but.setLayoutParams(lp);
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub

		}

	}

}

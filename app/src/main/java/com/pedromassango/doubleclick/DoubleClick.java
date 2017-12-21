package com.pedromassango.doubleclick;

import android.os.Handler;
import android.view.View;

/**
 * Created by pedromassango on 12/20/17.
 */

public class DoubleClick implements View.OnClickListener {

    private int clicks;
    private boolean isBussy = false;
    private final DoubleClickListener doubleClickListener;
    Handler mHandler = new Handler();
    public DoubleClick( DoubleClickListener doubleClickListener) {
        this.doubleClickListener = doubleClickListener;
    }

    @Override
    public void onClick( final View v) {

        if(!isBussy) {
            //  Prevent multiple click in this short time
            isBussy = true;

            // Increase clicks count
            this.clicks++;
            long DOUBLE_CLICK_INTERVAL = 250L;  // Time to wait the second click.
            mHandler.postDelayed(new Runnable() {
                public final void run() {

                    if (clicks >= 2) {  // Double tap.
                        doubleClickListener.onDoubleClick(v);
                    }

                    if (clicks == 1) {  // Single tap
                        doubleClickListener.onSingleClick(v);
                    }

                    // we need to  restore clicks count
                    clicks = 0;
                    isBussy = false;
                }
            }, DOUBLE_CLICK_INTERVAL);
        }
    }
}

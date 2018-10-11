package com.mitrais.innovation.cryptbase.utility.font;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class FontQuicksandMedium extends AppCompatTextView {

    /**
     * Define constructor to set font asset.
     * @param context: application contex.
     * @param attributeSet: font attribute
     */
    public FontQuicksandMedium(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/Quicksand-Medium.ttf"));
    }
}

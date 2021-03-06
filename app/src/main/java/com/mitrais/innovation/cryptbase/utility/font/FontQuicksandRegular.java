package com.mitrais.innovation.cryptbase.utility.font;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class FontQuicksandRegular extends AppCompatTextView {

    /**
     * Define constructor to set font asset.
     * @param context: application contex.
     * @param attributeSet: font attribute
     */
    public FontQuicksandRegular(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/Quicksand-Regular.ttf"));
    }
}

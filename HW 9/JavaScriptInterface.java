package com.example.kinglunau.hw9;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by kinglunau on 11/30/17.
 */

public class JavaScriptInterface {
    Context mContext;

    /** Instantiate the interface and set the context
     * @param c*/
    JavaScriptInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public String getValue(){
        return MainActivity.stockSymbol;
    }
}


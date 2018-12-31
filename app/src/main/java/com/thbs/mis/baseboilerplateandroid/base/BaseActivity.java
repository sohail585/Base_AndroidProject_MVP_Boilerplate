package com.thbs.mis.baseboilerplateandroid.base;

/**
 * Created by muhammed_suhail on 12/26/2017.
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private boolean isActivityActive = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        this.progressDialog = new ProgressDialog(this);
        isActivityActive = true;

    }

    protected abstract int getLayout();

    protected void showShortToast(String message) {
        if(isActivityActive) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    protected void showLongToast(String message) {
        if(isActivityActive) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

    //for checking the network connection
    protected boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    //generic method for displaying the snackbar
    protected void showSnackBar(View view, String snackBarMessage) {
        if(isActivityActive) {
            Snackbar snackbar = Snackbar
                    .make(view, snackBarMessage, Snackbar.LENGTH_LONG);
            View yourSnackBarView = snackbar.getView(); //get your snackbar view
            TextView textView = yourSnackBarView.findViewById(android.support.design.R.id.snackbar_text); //Get reference of snackbar textview
            textView.setMaxLines(3);
            snackbar.show();
        }
    }

    protected void showProgressDialog(String message) {
        if(isActivityActive) {
            if (progressDialog.isShowing()) {

            } else {

                progressDialog.setCancelable(true);
                progressDialog.setMessage(message);
                progressDialog.show();
            }
        }
    }

    protected void showNonCancelableProgressDialog(String message) {
        if (progressDialog.isShowing()) {

        } else {
            progressDialog.setCancelable(false);
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }

    protected void dismissProgressDialog() {
        if (this.progressDialog == null)
            return;
        if (this.progressDialog.isShowing())
            this.progressDialog.dismiss();
    }

    protected void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    public void showAlertDialog(String title, String message, final Activity activity, final Boolean shouldFinishActivity) {
        if(isActivityActive) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle(title);
            builder.setMessage(message);

            builder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            if (shouldFinishActivity) {
                                //activity.finish();
                            }
                        }
                    });
            builder.create().show();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        isActivityActive = false;
        super.onDestroy();
    }
}

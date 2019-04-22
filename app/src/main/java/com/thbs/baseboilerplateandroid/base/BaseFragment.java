package com.thbs.baseboilerplateandroid.base;

/**
 * Created by muhammed_suhail on 1/4/2018.
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
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

public abstract class BaseFragment extends Fragment {


    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.progressDialog = new ProgressDialog(getActivity());
    }

    protected abstract int getLayout();
    protected abstract void setListeners();
    protected abstract void initViews();

    public void showShortToast(String message) {
        if (isAdded()) {
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }

    public void showLongToast(String message) {
        if (isAdded()) {
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        }
    }

    //for checking the network connection
    protected boolean isNetworkAvailable() {
        if (isAdded()) {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo;
            if (connectivityManager != null) {
                activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo != null && activeNetworkInfo.isConnected();
            }
        }
        return false;
    }

    //generic method for displaying the snackbar
    public void showSnackBar(View view, String snackBarMessage) {
        Snackbar snackbar = Snackbar
                .make(view, snackBarMessage, Snackbar.LENGTH_LONG);
        View yourSnackBarView = snackbar.getView(); //get your snackbar view
        TextView textView = yourSnackBarView.findViewById(android.support.design.R.id.snackbar_text); //Get reference of snackbar textview
        textView.setMaxLines(3);
        snackbar.show();
    }

    public void showProgressDialog(String message) {

        if (progressDialog.isShowing()) {

        } else {
            progressDialog.setCancelable(true);
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }

    public void showNonCancelableProgressDialog(String message) {
        if (progressDialog.isShowing()) {

        } else {
            progressDialog.setCancelable(false);
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }

    public void dismissProgressDialog() {
        if (this.progressDialog == null)
            return;
        if (this.progressDialog.isShowing())
            this.progressDialog.dismiss();
    }

    public void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    protected void showAlertDialog(String title, String message, final Activity activity, final Boolean shouldFinishActivity) {
        if (isAdded()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
                getActivity().checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

}

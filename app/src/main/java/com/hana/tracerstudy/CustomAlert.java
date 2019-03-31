package com.hana.tracerstudy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class CustomAlert {

    public void succes(Context context, String judul, String isi) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.succesDialogTheme);
        builder.setTitle(judul);
        builder.setMessage(isi);

        String positiveText = "OK";
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                    }
                });


        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void error(Context context, String judul, String isi) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.errorDialogTheme);
        builder.setTitle(judul);
        builder.setMessage(isi);

        String positiveText = "OK";
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                    }
                });


        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

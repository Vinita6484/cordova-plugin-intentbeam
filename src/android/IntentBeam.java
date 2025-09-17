package com.intentbeam;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class IntentBeam extends CordovaPlugin {

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    if ("launchIntent".equals(action)) {
      String url = args.getString(0);
      showAlert("Launching: " + url); // Optional alert for debugging
      launchIntent(url, callbackContext);
      return true;
    }
    return false;
  }

  private void launchIntent(String url, CallbackContext callbackContext) {
    try {
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setData(Uri.parse(url));
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      Intent chooser = Intent.createChooser(intent, "Open with");
      this.cordova.getActivity().startActivity(chooser);
      callbackContext.success();
    } catch (Exception e) {
      showAlert("Error: " + e.getMessage());
      callbackContext.error("Failed to launch intent: " + e.getMessage());
    }
  }

  private void showAlert(final String message) {
    final CordovaInterface cordovaInterface = this.cordova;
    cordova.getActivity().runOnUiThread(new Runnable() {
      public void run() {
        AlertDialog.Builder builder = new AlertDialog.Builder(cordovaInterface.getActivity());
        builder.setTitle("IntentBeam")
               .setMessage(message)
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int id) {
                   dialog.dismiss();
                 }
               });
        AlertDialog dialog = builder.create();
        dialog.show();
      }
    });
  }
}

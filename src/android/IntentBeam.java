package com.vinita.intentbeam;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import android.content.Intent;
import android.net.Uri;

public class IntentBeam extends CordovaPlugin {
  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    if ("launchIntent".equals(action)) {
      String url = args.getString(0);
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setData(Uri.parse(url));
      this.cordova.getActivity().startActivity(intent);
      callbackContext.success();
      return true;
    }
    return false;
  }
}

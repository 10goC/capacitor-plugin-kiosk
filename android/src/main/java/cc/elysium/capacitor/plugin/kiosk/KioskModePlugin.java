package cc.elysium.capacitor.plugin.kiosk;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "KioskMode")
public class KioskModePlugin extends Plugin {
	private String TAG = "Capacitor Kiosk Plugin";

	@PluginMethod
	private boolean isInKioskMode() {
		ActivityManager activityManager = (ActivityManager) getActivity().getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
		if (Build.VERSION.SDK_INT < 23) {
			return activityManager.isInLockTaskMode();
		} else {
			Log.i(TAG, "Task Mode: " + Integer.toString(activityManager.getLockTaskModeState()));
			return activityManager.getLockTaskModeState() > ActivityManager.LOCK_TASK_MODE_NONE;
		}
	}

	@PluginMethod
	public void isInKioskMode(PluginCall call) {
		JSObject response = new JSObject();
		response.put("value", isInKioskMode());
		call.resolve(response);
	}

	@PluginMethod
	public void toggleKioskMode(PluginCall call) {
		if (isInKioskMode()) {
			exitKioskMode(call);
		} else {
			enterKioskMode(call);
		}
		call.resolve();
	}

	@PluginMethod
	public void enterKioskMode(PluginCall call) {
		Log.i(TAG, "Entering Kiosk Mode");
		getActivity().startLockTask();
		call.resolve();
	}

	@PluginMethod
	public void exitKioskMode(PluginCall call) {
		Log.i(TAG, "Exit Kiosk Mode");
		getActivity().stopLockTask();
		call.resolve();
	}
}

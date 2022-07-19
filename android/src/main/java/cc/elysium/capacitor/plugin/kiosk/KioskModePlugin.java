package cc.elysium.capacitor.plugin.kiosk;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "KioskMode")
public class KioskModePlugin extends Plugin {

	public boolean isInKioskMode() {
		ActivityManager activityManager = (ActivityManager) getActivity().getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
		if (Build.VERSION.SDK_INT < 23) {
			return activityManager.isInLockTaskMode();
		} else {
			return activityManager.getLockTaskModeState() == ActivityManager.LOCK_TASK_MODE_LOCKED;
		}
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
		Log.i("Capacitor Kiosk Plugin", "Entering Kiosk Mode");
		getActivity().startLockTask();
		call.resolve();
	}

	@PluginMethod
	public void exitKioskMode(PluginCall call) {
		Log.i("Capacitor Kiosk Plugin", "Exit Kiosk Mode");
		getActivity().stopLockTask();
		call.resolve();
	}
}

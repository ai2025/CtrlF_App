package id.ac.polinema.ctrlf.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    public static final String USER_KEY = "user";
    public static final String FIRST_KEY = "key";
    private SharedPreferences spf;
    private boolean firstTime = true;

    public Session(Context context) {
        this.spf = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setLoginInfo(String user) {
        spf.edit().putString(USER_KEY, user).apply();
    }

    public String getLoginInfo(){
        return spf.getString(USER_KEY, null);
    }

    public boolean isFirstTime(){
        return spf.getBoolean(FIRST_KEY, firstTime);
    }

    public void setFirstTime(){
        spf.edit().putBoolean(FIRST_KEY, false).apply();
        firstTime = false;
    }

    public boolean isLoggedIn() {
        String check = spf.getString(USER_KEY, null);
        return (check != null);
    }

    public void doLogout(){
        spf.edit().remove(USER_KEY).apply();
    }

}

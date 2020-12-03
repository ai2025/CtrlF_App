package id.ac.polinema.ctrlf;

import id.ac.polinema.ctrlf.model.Session;

public class Application extends android.app.Application {
    private static Session session;

    @Override
    public void onCreate() {
        super.onCreate();
        session = new Session(this);
    }

    public static Session getSession(){ return session; }
}

package id.ac.polinema.ctrlf.helper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {

    private String authToken;

    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}

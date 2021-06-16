package portal.retrofit;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

public class RetrofitErrorInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (response.code() >= 400) {
            Logger logger = LoggerFactory.getLogger(RetrofitErrorInterceptor.class);
            logger.error("response status code: " + response.code() + " error: " + response.body().string());
            throw new NullPointerException();
        }
        return response;
    }


}


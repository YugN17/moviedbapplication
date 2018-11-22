package com.soulkitchen.serifenuruysal.movieapp.api;

import com.soulkitchen.serifenuruysal.movieapp.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by S.Nur Uysal on 20.11.2018.
 */
public class ApiClient {
    public enum Status {
        RUNNING,
        SUCCESS,
        FAILED
    }

    private static final String TAG = "ApiClient";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final OkHttpClient client;
    private static ApiService mService;
    static {

        client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new AuthInterceptor())
                .build();



    }
    public static ApiService getInstance() {
        synchronized (new Object()) {
            if (mService == null) {
                mService = getRetrofitInstance().create(ApiService.class);
            }
            return mService;
        }
    }

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public ApiService getService() {
        return mService;
    }

    public static class AuthInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();

            HttpUrl url = request.url().newBuilder()
                    .addQueryParameter("api_key", BuildConfig.API_KEY)
//                    .addQueryParameter("language", "en-US")
//                    .addQueryParameter("region", "en-US")
                    .build();

            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        }


    }

    public static class NetworkState {

        public static final NetworkState LOADED = new NetworkState(Status.SUCCESS, null);
        public static final NetworkState LOADING = new NetworkState(Status.RUNNING, null);
        private Status status;
        private String msg;



        private NetworkState(Status status, String msg) {
            this.status = status;
            this.msg = msg;
        }

        public  NetworkState error(String msg) {
            return new NetworkState(Status.FAILED, msg);
        }

        public Status getStatus() {
            return status;
        }

        public String getMsg() {
            return msg;
        }
    }
}

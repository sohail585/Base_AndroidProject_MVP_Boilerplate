package com.thbs.baseboilerplateandroid.networking.base;

/**
 * Created by muhammed_suhail on 1/4/2018.
 */

import android.util.Base64;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * ApiHelper class initialize Retrofit object by adding base url of api,
 * OkHttpClient used for send http request and read response,
 * Gson converter factory for serialization and deserialization,
 * and RxJava2CallAdapterFactory used for return an Observable,
 * Flowable, Single, Completable or Maybe from service methods.
 */
public class RetrofitBuilderHelper {

    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS);
    private Retrofit.Builder builder
            = new Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(new AnnotatedConverterFactory.Builder()
                    .add(AnnotatedConverterFactory.Xml.class, SimpleXmlConverterFactory.createNonStrict(new Persister(new AnnotationStrategy())))
                    .add(AnnotatedConverterFactory.Json.class, GsonConverterFactory.create()).build())
            //.addConverterFactory(new XmlOrJsonConverterFactory())
            //.addConverterFactory(SimpleXmlConverterFactory.createNonStrict(
            //       new Persister(new AnnotationStrategy()))) // important part!
            //.addConverterFactory(SimpleXmlConverterFactory.create())
            //.addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    public static RetrofitBuilderHelper getInstance() {
        return new RetrofitBuilderHelper();
    }

    public void setBaseUrl(String baseURL) {
        builder.baseUrl(baseURL);
    }


    //Create Service with basicAuth
    public <S> S createServiceWithBasicAuth(Class<S> serviceClass, String username, String password) {

        if (username != null && password != null) {
            String credentials = username + ":" + password;
            final String basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            httpClient.cookieJar(new JavaNetCookieJar(cookieManager));


            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder();
                    requestBuilder.header("Authorization", basic);
                    requestBuilder.method(original.method(), original.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }


    //Create Service with basicAuth
    public <S> S createServiceWithoutBasicAuth(Class<S> serviceClass) {


        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        httpClient.cookieJar(new JavaNetCookieJar(cookieManager));


        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder();
                requestBuilder.method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(logging);

        OkHttpClient client = httpClient.build();
        //Retrofit retrofit = builder.client(getUnsafeOkHttpClient()).build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

    public <S> S createServiceWithoutBasicAuthAndUnsafeOkhttp(Class<S> serviceClass) {

        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        httpClient.cookieJar(new JavaNetCookieJar(cookieManager));


        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder();
                requestBuilder.method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(logging);

        Retrofit retrofit = builder.client(getUnsafeOkHttpClient().build()).build();
        return retrofit.create(serviceClass);

    }


    public <S> S createServiceWithBasicAuthAndUnsafeOkhttp(Class<S> serviceClass, String username, String password) {

        if (username != null && password != null) {
            String credentials = username + ":" + password;
            final String basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            httpClient.cookieJar(new JavaNetCookieJar(cookieManager));


            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder();
                    requestBuilder.header("Authorization", basic);
                    requestBuilder.method(original.method(), original.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }

        Retrofit retrofit = builder.client(getUnsafeOkHttpClient().build()).build();
        return retrofit.create(serviceClass);
    }

    private OkHttpClient.Builder getUnsafeOkHttpClient() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}



package com.ojiofong.androidsamples.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.retrofit.GitResult;
import com.ojiofong.androidsamples.retrofit.RestClient;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {
    private static final String TAG = RxJavaActivity.class.getSimpleName();
    Subscription subscription;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe(); // don't forget to avoid memory leaks
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
        setTitle(TAG);
        getRetro();

       subscription =  getStringObservable()
                .subscribeOn(Schedulers.io()) // do work in background
                .observeOn(AndroidSchedulers.mainThread()) // return in main thread
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage(), e);
                    }

                    @Override
                    public void onNext(String s) {
                        ((TextView) findViewById(R.id.tvHelloWorld)).setText(s);
                    }
                });
    }


    private String getWeb() throws IOException {

        String url = "https://api.github.com/search/users?q=tom";


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    String postWeb() throws IOException {

      //  String url = "http://ojiofong.com/test/welcome.php";

        String url = "http://requestb.in/1a4120f1";

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("name", "Mary Doe")
                .add("email", "maryjizzle@email.com")
                .build();


        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    public String getRetro(){

        //String baseUrl = "https://api.github.com/search/users?q=tom";
//        String baseUrl = "https://api.github.com";
//
//
//        Retrofit client = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

        RestClient.GitApiInterface service = RestClient.getClient();
        Call<GitResult> call = service.getUsersNamedTom("Tom");
        call.enqueue(new Callback<GitResult>() {
            @Override
            public void onResponse(Call<GitResult> call, retrofit2.Response<GitResult> response) {
                if (response.isSuccessful()){
                    GitResult result = response.body();

                   // Log.d(TAG, "response = " + new Gson().toJson(result));
                    //Log.d(TAG, result.getItems().get(0).getAvatarUrl().toString());
                   // Log.d(TAG, response.body().toString());
                }else {
                    //Handle errors
                }
            }

            @Override
            public void onFailure(Call<GitResult> call, Throwable t) {

            }
        });




        return "No retro";

    }

    public rx.Observable<String> getStringObservable(){
        //return rx.Observable.just(getWeb()); // will run right away
        // defer until subscribed
        return rx.Observable.defer(new Func0<rx.Observable<String>>() {
            @Override
            public rx.Observable<String> call() {
                try {
                    return rx.Observable.just(getWeb());
                } catch (IOException e) {
                    e.printStackTrace();
                    return  null;
                }
            }
        });
    }

}

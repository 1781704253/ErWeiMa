package zhangyanran201800907.bwie.com.day08;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 匹诺曹 on 2018/9/7.
 */

class AccountM {

    private OkHttpClient client;

    public AccountM(){
        client = new OkHttpClient
                .Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .build();
    }

    public void login(String mName, String mPwd, final AccountCallack callack) {
        FormBody formBody = new FormBody
                .Builder()
                .add("mobile",mName)
                .add("password",mPwd)
                .build();
        Request request = new Request
                .Builder()
                .url("https://www.zhaoapi.cn/user/login")
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callack.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callack.onSuccess(response.body().string());
            }
        });
    }

    public void rag(String mName, String mPwd, final AccountCallack callack) {
        FormBody formBody = new FormBody
                .Builder()
                .add("mobile",mName)
                .add("password",mPwd)
                .build();
        Request request = new Request
                .Builder()
                .url("https://www.zhaoapi.cn/user/reg")
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callack.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callack.onSuccess(response.body().string());
            }
        });
    }

    public interface AccountCallack{
        void onSuccess(String msg);
        void onError(String errorMsg);
    }
}

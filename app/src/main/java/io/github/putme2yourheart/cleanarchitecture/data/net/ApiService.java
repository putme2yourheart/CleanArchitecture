package io.github.putme2yourheart.cleanarchitecture.data.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

class ApiService {
    // base url
    private String url;

    private ApiService(String url) {
        this.url = url;
    }

    // 地址的base url
    static ApiService create(String url) {
        return new ApiService(url);
    }

    private Retrofit getDefault() {
        // 设置超时
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS);

        return new Retrofit.Builder().baseUrl(this.url).client(builder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                //.addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * Retorfit生成 T 接口的实现
     *
     * @param service http api 接口
     * @param <T>     类名
     * @return T 接口的实现
     */
    <T> T create(final Class<T> service) {
        return getDefault().create(service);
    }
}

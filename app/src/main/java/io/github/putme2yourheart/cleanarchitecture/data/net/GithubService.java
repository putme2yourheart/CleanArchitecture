package io.github.putme2yourheart.cleanarchitecture.data.net;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GithubService {
    /**
     * 根据用户名获取用户在github上的信息
     *
     * @param user 用户名
     * @return 用户信息
     */
    @GET("/users/{user}")
    Call<String> getUserEntity(@Path("user") String user);
}

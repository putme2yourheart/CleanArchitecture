package io.github.putme2yourheart.cleanarchitecture.data.net;

import java.util.List;

import io.github.putme2yourheart.cleanarchitecture.data.entity.RepoEntity;
import io.github.putme2yourheart.cleanarchitecture.data.entity.UserEntity;
import io.reactivex.Observable;

public interface RestApi {
    String GITHUB_RUL = "https://api.github.com/";

    /**
     * 获取用户信息
     *
     * @param user 用户名
     * @return 用户信息实体
     */
    Observable<UserEntity> userEntity(String user);

    /**
     * 获取用户的仓库
     *
     * @param user 用户名
     * @return 仓库列表
     */
    Observable<List<RepoEntity>> getRepoList(String user);
}

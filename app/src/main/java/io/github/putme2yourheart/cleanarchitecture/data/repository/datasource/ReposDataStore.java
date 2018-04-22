package io.github.putme2yourheart.cleanarchitecture.data.repository.datasource;

import java.util.List;

import io.github.putme2yourheart.cleanarchitecture.data.entity.RepoEntity;
import io.reactivex.Observable;

public interface ReposDataStore {
    /**
     * 通过用户名获取用户仓库信息列表
     *
     * @param user 用户名
     */
    Observable<List<RepoEntity>> repoEntityList(String user);
}

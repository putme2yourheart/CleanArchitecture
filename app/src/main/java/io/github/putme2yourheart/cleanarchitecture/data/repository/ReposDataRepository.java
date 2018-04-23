package io.github.putme2yourheart.cleanarchitecture.data.repository;

import io.github.putme2yourheart.cleanarchitecture.data.entity.RepoEntity;
import io.github.putme2yourheart.cleanarchitecture.data.mapper.RepoEntityJsonMapper;
import io.github.putme2yourheart.cleanarchitecture.data.mapper.UserEntityJsonMapper;
import io.github.putme2yourheart.cleanarchitecture.data.net.RestApi;
import io.github.putme2yourheart.cleanarchitecture.data.net.RestApiImpl;
import io.github.putme2yourheart.cleanarchitecture.data.repository.datasource.CloudReposDataStore;
import io.github.putme2yourheart.cleanarchitecture.domain.repository.ReposRepository;
import io.reactivex.Observable;
import java.util.List;

public class ReposDataRepository implements ReposRepository {
  private UserEntityJsonMapper mUserEntityJsonMapper;
  private RepoEntityJsonMapper mRepoEntityJsonMapper;

  public ReposDataRepository(UserEntityJsonMapper userEntityJsonMapper,
      RepoEntityJsonMapper repoEntityJsonMapper) {
    mUserEntityJsonMapper = userEntityJsonMapper;
    mRepoEntityJsonMapper = repoEntityJsonMapper;
  }

  @Override public Observable<List<RepoEntity>> repos(String user) {
    return createCloudReposDataStore(user);
  }

  /**
   * 从网络获取数据
   */
  private Observable<List<RepoEntity>> createCloudReposDataStore(String user) {
    RestApi restApi = new RestApiImpl(mUserEntityJsonMapper, mRepoEntityJsonMapper);
    return new CloudReposDataStore(restApi).repoEntityList(user);
  }
}

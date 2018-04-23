package io.github.putme2yourheart.cleanarchitecture.domain.repository;

import io.github.putme2yourheart.cleanarchitecture.data.entity.RepoEntity;
import io.reactivex.Observable;
import java.util.List;

public interface ReposRepository {
  Observable<List<RepoEntity>> repos(String user);
}

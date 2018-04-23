package io.github.putme2yourheart.cleanarchitecture.domain.interactor;

import io.github.putme2yourheart.cleanarchitecture.data.entity.RepoEntity;
import io.github.putme2yourheart.cleanarchitecture.data.mapper.RepoEntityJsonMapper;
import io.github.putme2yourheart.cleanarchitecture.data.mapper.UserEntityJsonMapper;
import io.github.putme2yourheart.cleanarchitecture.data.repository.ReposDataRepository;
import io.reactivex.Observable;
import java.util.List;

public class RepoListUseCase extends UseCase<List<RepoEntity>, String> {

  @Override Observable<List<RepoEntity>> buildUseCaseObservable(String s) {
    return new ReposDataRepository(new UserEntityJsonMapper(), new RepoEntityJsonMapper()).repos(s);
  }
}

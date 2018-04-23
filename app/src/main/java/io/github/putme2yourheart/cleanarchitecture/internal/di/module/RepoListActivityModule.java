package io.github.putme2yourheart.cleanarchitecture.internal.di.module;

import dagger.Module;
import dagger.Provides;
import io.github.putme2yourheart.cleanarchitecture.domain.interactor.RepoListUseCase;

@Module
public class RepoListActivityModule {
  @Provides RepoListUseCase provideRepoListUseCase() {
    return new RepoListUseCase();
  }
}

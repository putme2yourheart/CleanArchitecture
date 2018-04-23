package io.github.putme2yourheart.cleanarchitecture.internal.di.component;

import dagger.Component;
import io.github.putme2yourheart.cleanarchitecture.internal.di.module.RepoListActivityModule;
import io.github.putme2yourheart.cleanarchitecture.view.activity.RepoListActivity;

@Component(modules = RepoListActivityModule.class)
public interface RepoListActivityComponent {
  void inject(RepoListActivity activity);
}

package io.github.putme2yourheart.cleanarchitecture.internal.di.component;

import dagger.Component;
import io.github.putme2yourheart.cleanarchitecture.internal.di.PerActivity;
import io.github.putme2yourheart.cleanarchitecture.internal.di.module.MainActivityModule;
import io.github.putme2yourheart.cleanarchitecture.view.activity.MainActivity;

@PerActivity
@Component(dependencies = AppComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}

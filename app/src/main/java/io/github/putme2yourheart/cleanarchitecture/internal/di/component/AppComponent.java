package io.github.putme2yourheart.cleanarchitecture.internal.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.github.putme2yourheart.cleanarchitecture.internal.di.module.AppModule;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    /**
     * Context依赖
     * @return Context
     */
    Context context();
}
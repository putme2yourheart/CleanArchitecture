package io.github.putme2yourheart.cleanarchitecture;

import android.app.Application;

import io.github.putme2yourheart.cleanarchitecture.internal.di.component.AppComponent;
import io.github.putme2yourheart.cleanarchitecture.internal.di.component.DaggerAppComponent;
import io.github.putme2yourheart.cleanarchitecture.internal.di.module.AppModule;

public class App extends Application {
    private static AppComponent sAppComponent = null;

    @Override
    public void onCreate() {
        super.onCreate();

        if (sAppComponent == null) {
            sAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        }
    }

    public AppComponent getAppComponent() {
        //向外界的依赖提供这个AppComponent
        return sAppComponent;
    }
}

package io.github.putme2yourheart.cleanarchitecture.presentation.presenter;

public interface Presenter {
    /**
     * Activity 或者 Fragment OnResume时调用
     */
    void resume();

    /**
     * Activity 或者 Fragment OnPause时调用
     */
    void pause();

    /**
     * Activity 或者 Fragment OnDestroy时调用
     */
    void destroy();
}

package io.github.putme2yourheart.cleanarchitecture.presentation.presenter;

import io.github.putme2yourheart.cleanarchitecture.domain.User;
import io.github.putme2yourheart.cleanarchitecture.domain.interactor.UserDetailsUseCase;
import io.github.putme2yourheart.cleanarchitecture.view.UserDetailsView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserDetailsPresenter implements Presenter {
    UserDetailsView userDetailsView;
    UserDetailsUseCase userDetailsUseCase;

    public UserDetailsPresenter(UserDetailsView userDetailsView, UserDetailsUseCase userDetailsUseCase) {
        this.userDetailsView = userDetailsView;
        this.userDetailsUseCase = userDetailsUseCase;
    }

    // 加载数据并控制view显示隐藏
    public void loadUserDetails(String user) {
        userDetailsView.hideLoading();
        userDetailsView.showLoading();
        getUserDetails(user);
    }

    private void getUserDetails(String user) {
        userDetailsUseCase.execute(new UserDetailsObserver(), user);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        if (userDetailsView != null) {
            userDetailsView = null;
        }
    }

    private class UserDetailsObserver implements Observer<User> {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(User user) {
            userDetailsView.showUserDetails(user);
        }

        @Override
        public void onError(Throwable e) {
            userDetailsView.hideLoading();
        }

        @Override
        public void onComplete() {
            userDetailsView.hideLoading();
        }
    }
}

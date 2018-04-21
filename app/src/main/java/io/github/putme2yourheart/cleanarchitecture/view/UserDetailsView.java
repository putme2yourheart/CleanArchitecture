package io.github.putme2yourheart.cleanarchitecture.view;

import io.github.putme2yourheart.cleanarchitecture.domain.User;

public interface UserDetailsView {
    void showLoading();

    void hideLoading();

    void showUserDetails(User user);
}

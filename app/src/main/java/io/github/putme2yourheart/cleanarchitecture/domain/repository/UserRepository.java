package io.github.putme2yourheart.cleanarchitecture.domain.repository;

import io.github.putme2yourheart.cleanarchitecture.domain.User;
import io.reactivex.Observable;

public interface UserRepository {
    Observable<User> user(final String user);
}

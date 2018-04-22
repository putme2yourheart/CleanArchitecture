package io.github.putme2yourheart.cleanarchitecture.domain.interactor;

import javax.inject.Inject;

import io.github.putme2yourheart.cleanarchitecture.domain.User;
import io.github.putme2yourheart.cleanarchitecture.domain.repository.UserRepository;
import io.reactivex.Observable;

public class UserDetailsUseCase extends UseCase<User, String> {
    private UserRepository userRepository;

    @Inject
    public UserDetailsUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    Observable<User> buildUseCaseObservable(String s) {
        return userRepository.user(s);
    }
}

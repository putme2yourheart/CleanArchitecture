package io.github.putme2yourheart.cleanarchitecture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.github.putme2yourheart.cleanarchitecture.data.net.RestApiImpl;
import io.github.putme2yourheart.cleanarchitecture.data.repository.UserDataRepository;
import io.github.putme2yourheart.cleanarchitecture.data.repository.datasource.UserDataStoreFactory;
import io.github.putme2yourheart.cleanarchitecture.domain.User;
import io.github.putme2yourheart.cleanarchitecture.domain.repository.UserRepository;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserRepository userRepository = new UserDataRepository(new UserDataStoreFactory());
        userRepository.user("putme2yourheart")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        Log.e("user", user.getLogin());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

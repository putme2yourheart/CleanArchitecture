package io.github.putme2yourheart.cleanarchitecture.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.putme2yourheart.cleanarchitecture.R;
import io.github.putme2yourheart.cleanarchitecture.data.repository.UserDataRepository;
import io.github.putme2yourheart.cleanarchitecture.data.repository.datasource.UserDataStoreFactory;
import io.github.putme2yourheart.cleanarchitecture.domain.User;
import io.github.putme2yourheart.cleanarchitecture.domain.interactor.UserDetailsUseCase;
import io.github.putme2yourheart.cleanarchitecture.presentation.presenter.UserDetailsPresenter;
import io.github.putme2yourheart.cleanarchitecture.view.UserDetailsView;

public class MainActivity extends AppCompatActivity implements UserDetailsView {
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.tv_user_details)
    TextView tv_user_details;

    UserDetailsPresenter userDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        userDetailsPresenter = new UserDetailsPresenter(this,
                new UserDetailsUseCase(new UserDataRepository(new UserDataStoreFactory())));
        userDetailsPresenter.loadUserDetails("putme2yourheart");
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showUserDetails(User user) {
        tv_user_details.setText(user.getLogin());
    }
}

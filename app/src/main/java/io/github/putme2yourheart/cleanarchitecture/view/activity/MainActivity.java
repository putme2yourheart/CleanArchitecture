package io.github.putme2yourheart.cleanarchitecture.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.putme2yourheart.cleanarchitecture.R;
import io.github.putme2yourheart.cleanarchitecture.data.cache.UserCacheImpl;
import io.github.putme2yourheart.cleanarchitecture.data.cache.serializer.FileManager;
import io.github.putme2yourheart.cleanarchitecture.data.cache.serializer.Serializer;
import io.github.putme2yourheart.cleanarchitecture.data.entity.UserEntity;
import io.github.putme2yourheart.cleanarchitecture.data.mapper.UserEntityJsonMapper;
import io.github.putme2yourheart.cleanarchitecture.data.net.RestApi;
import io.github.putme2yourheart.cleanarchitecture.data.net.RestApiImpl;
import io.github.putme2yourheart.cleanarchitecture.data.repository.UserDataRepository;
import io.github.putme2yourheart.cleanarchitecture.data.repository.datasource.UserDataStoreFactory;
import io.github.putme2yourheart.cleanarchitecture.domain.User;
import io.github.putme2yourheart.cleanarchitecture.domain.interactor.UserDetailsUseCase;
import io.github.putme2yourheart.cleanarchitecture.presentation.presenter.UserDetailsPresenter;
import io.github.putme2yourheart.cleanarchitecture.view.UserDetailsView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements UserDetailsView {
    @BindView(R.id.et_user)
    EditText et_user;
    @BindView(R.id.btn_search)
    Button btn_search;
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

        checkPermission();

        userDetailsPresenter = new UserDetailsPresenter(this,
                new UserDetailsUseCase(new UserDataRepository(new UserDataStoreFactory())));

        // 测试缓存类
        FileManager fileManager = new FileManager();
        final UserCacheImpl userCache = new UserCacheImpl(this, fileManager, new Serializer());
        Gson gson = new Gson();
        String json = "{\"login\":\"putme2yourheart\",\"id\":18267374,\"avatar_url\":\"https://avatars1.githubusercontent.com/u/18267374?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/putme2yourheart\",\"html_url\":\"https://github.com/putme2yourheart\",\"followers_url\":\"https://api.github.com/users/putme2yourheart/followers\",\"following_url\":\"https://api.github.com/users/putme2yourheart/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/putme2yourheart/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/putme2yourheart/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/putme2yourheart/subscriptions\",\"organizations_url\":\"https://api.github.com/users/putme2yourheart/orgs\",\"repos_url\":\"https://api.github.com/users/putme2yourheart/repos\",\"events_url\":\"https://api.github.com/users/putme2yourheart/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/putme2yourheart/received_events\",\"type\":\"User\",\"site_admin\":false,\"name\":\"Frank\",\"company\":\"Googel\",\"blog\":\"\",\"location\":\"Beijing\",\"email\":null,\"hireable\":null,\"bio\":\"hhh\",\"public_repos\":20,\"public_gists\":0,\"followers\":0,\"following\":0,\"created_at\":\"2016-04-04T16:24:40Z\",\"updated_at\":\"2018-04-21T13:41:36Z\"}";
        UserEntity userEntity = gson.fromJson(json, UserEntity.class);
        userCache.put(userEntity);
        userCache.get("putme2yourheart").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UserEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserEntity userEntity) {
                Log.e("get user", userEntity.getLogin());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
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
        tv_user_details.setText(user.toString());
    }

    @OnClick(R.id.btn_search)
    public void searchUserDetails(View view) {
        String user = et_user.getText().toString();
        if (!user.equals("")) {
            userDetailsPresenter.loadUserDetails(user);
        }
    }

    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        } else {
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
            Log.e("log", "checkPermission: 已经授权！");
        }
    }
}

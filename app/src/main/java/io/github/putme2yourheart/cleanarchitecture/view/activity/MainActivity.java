package io.github.putme2yourheart.cleanarchitecture.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.putme2yourheart.cleanarchitecture.App;
import io.github.putme2yourheart.cleanarchitecture.R;
import io.github.putme2yourheart.cleanarchitecture.domain.User;
import io.github.putme2yourheart.cleanarchitecture.internal.di.component.DaggerMainActivityComponent;
import io.github.putme2yourheart.cleanarchitecture.internal.di.module.MainActivityModule;
import io.github.putme2yourheart.cleanarchitecture.presentation.presenter.UserDetailsPresenter;
import io.github.putme2yourheart.cleanarchitecture.view.UserDetailsView;

public class MainActivity extends AppCompatActivity implements UserDetailsView {
    @BindView(R.id.et_user)
    EditText et_user;
    @BindView(R.id.btn_search)
    Button btn_search;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.tv_user_details)
    TextView tv_user_details;

    @Inject
    UserDetailsPresenter userDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        checkPermission();

        DaggerMainActivityComponent.builder()
                .appComponent(((App)getApplication()).getAppComponent())
                .mainActivityModule(new MainActivityModule())
                .build()
                .inject(this);
//        userDetailsPresenter = new UserDetailsPresenter(this,
//                new UserDetailsUseCase(new UserDataRepository(new UserDataStoreFactory(
//                        new UserCacheImpl(this, new FileManager(), new Serializer())))));
        userDetailsPresenter.setView(this);
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

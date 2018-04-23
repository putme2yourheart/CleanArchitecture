package io.github.putme2yourheart.cleanarchitecture.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.putme2yourheart.cleanarchitecture.R;
import io.github.putme2yourheart.cleanarchitecture.data.entity.RepoEntity;
import io.github.putme2yourheart.cleanarchitecture.internal.di.component.DaggerRepoListActivityComponent;
import io.github.putme2yourheart.cleanarchitecture.presentation.presenter.RepoListPresenter;
import io.github.putme2yourheart.cleanarchitecture.view.RepoListView;
import io.github.putme2yourheart.cleanarchitecture.view.activity.adapter.RepoListAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class RepoListActivity extends AppCompatActivity implements RepoListView {
  @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
  @BindView(R.id.progressBar) ProgressBar mProgressBar;

  @Inject
  RepoListPresenter mRepoListPresenter;
  private RepoListAdapter mRepoListAdapter;
  private List<RepoEntity> mRepoEntityList = new ArrayList<>();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_repo_list);

    ButterKnife.bind(this);

    DaggerRepoListActivityComponent.create().inject(this);

    mRepoListPresenter.setView(this);
    mRepoListPresenter.getRepoList("putme2yourheart");

    mRepoListAdapter = new RepoListAdapter(this, mRepoEntityList);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mRecyclerView.setAdapter(mRepoListAdapter);
  }

  public static void startRepoListActivity(Context context, String user) {
    context.startActivity(new Intent(context, RepoListActivity.class));
  }

  @Override public void showLoading() {
    mProgressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    mProgressBar.setVisibility(View.GONE);
  }

  @Override public void loadRepoList(List<RepoEntity> repoEntities) {
    if (repoEntities != null) {
      // 清楚内存的数据集
      mRepoEntityList.clear();
      mRepoEntityList.addAll(repoEntities);
      // 通知adapter更新数据集
      mRepoListAdapter.notifyDataSetChanged();
    }
  }
}

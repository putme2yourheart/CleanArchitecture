package io.github.putme2yourheart.cleanarchitecture.presentation.presenter;

import android.util.Log;
import io.github.putme2yourheart.cleanarchitecture.data.entity.RepoEntity;
import io.github.putme2yourheart.cleanarchitecture.domain.interactor.RepoListUseCase;
import io.github.putme2yourheart.cleanarchitecture.view.RepoListView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.List;
import javax.inject.Inject;

public class RepoListPresenter {
  private RepoListUseCase mRepoListUseCase;
  private RepoListView mRepoListView;

  @Inject public RepoListPresenter(RepoListUseCase repoListUseCase) {
    mRepoListUseCase = repoListUseCase;
  }

  // 绑定view
  public void setView(RepoListView repoListView) {
    this.mRepoListView = repoListView;
  }

  // 获取用户仓库
  public void getRepoList(String user) {
    mRepoListView.hideLoading();
    mRepoListView.showLoading();
    mRepoListUseCase.execute(new RepoListDataObserver(), user);
  }

  private class RepoListDataObserver implements Observer<List<RepoEntity>> {

    @Override public void onSubscribe(Disposable d) {

    }

    @Override public void onNext(List<RepoEntity> repoEntities) {
      mRepoListView.loadRepoList(repoEntities);
    }

    @Override public void onError(Throwable e) {
      mRepoListView.hideLoading();
    }

    @Override public void onComplete() {
      mRepoListView.hideLoading();
    }
  }
}

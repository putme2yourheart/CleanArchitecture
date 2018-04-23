package io.github.putme2yourheart.cleanarchitecture.view;

import io.github.putme2yourheart.cleanarchitecture.data.entity.RepoEntity;
import java.util.List;

public interface RepoListView {

  /**
   * 显示加载图标
   */
  void showLoading();

  /**
   * 隐藏加载图标
   */
  void hideLoading();

  /**
   * 加载仓库数据
   */
  void loadRepoList(List<RepoEntity> repoEntities);
}

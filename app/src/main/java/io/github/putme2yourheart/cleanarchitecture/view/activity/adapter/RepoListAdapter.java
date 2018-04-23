package io.github.putme2yourheart.cleanarchitecture.view.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import io.github.putme2yourheart.cleanarchitecture.R;
import io.github.putme2yourheart.cleanarchitecture.data.entity.RepoEntity;
import java.util.List;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.RepoListViewHolder> {
  private Context mContext;
  private List<RepoEntity> mRepoEntityList;

  public RepoListAdapter(Context context, List<RepoEntity> repoEntityList) {
    mContext = context;
    mRepoEntityList = repoEntityList;
  }

  @Override public RepoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new RepoListViewHolder(View.inflate(mContext, R.layout.item_repo, null));
  }

  @Override public void onBindViewHolder(RepoListViewHolder holder, int position) {
    holder.tv_repoName.setText(mRepoEntityList.get(position).getName());
  }

  @Override public int getItemCount() {
    return mRepoEntityList.size();
  }

  class RepoListViewHolder extends RecyclerView.ViewHolder {
    TextView tv_repoName;

    RepoListViewHolder(View itemView) {
      super(itemView);

      tv_repoName = itemView.findViewById(R.id.tv_repoName);
    }
  }
}

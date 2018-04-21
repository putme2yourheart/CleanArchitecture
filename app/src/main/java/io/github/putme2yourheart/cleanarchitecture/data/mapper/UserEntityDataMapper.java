package io.github.putme2yourheart.cleanarchitecture.data.mapper;

import io.github.putme2yourheart.cleanarchitecture.data.entity.UserEntity;
import io.github.putme2yourheart.cleanarchitecture.domain.User;
import io.reactivex.functions.Function;

public class UserEntityDataMapper implements Function<UserEntity, User> {
    @Override
    public User apply(UserEntity userEntity) throws Exception {
        return transform(userEntity);
    }

    /**
     * UserEntity转换成User
     *
     * @param userEntity UserEntity
     * @return User
     */
    private User transform(UserEntity userEntity) {
        User user = null;
        if (userEntity != null) {
            user = new User();
            user.setLogin(userEntity.getLogin());
            user.setId(userEntity.getId());
            user.setAvatar_url(userEntity.getAvatar_url());
            user.setUrl(userEntity.getUrl());
            user.setHtml_url(userEntity.getHtml_url());
            user.setFollowers_url(userEntity.getFollowers_url());
            user.setFollowing_url(userEntity.getFollowing_url());
            user.setStarred_url(userEntity.getStarred_url());
            user.setRepos_url(userEntity.getRepos_url());
            user.setType(userEntity.getType());
            user.setName(userEntity.getName());
            user.setEmail(userEntity.getEmail());
            user.setPublic_repos(userEntity.getPublic_repos());
            user.setFollowers(userEntity.getFollowers());
            user.setFollowing(userEntity.getFollowing());
            user.setCreated_at(userEntity.getCreated_at());
            user.setUpdated_at(userEntity.getUpdated_at());
        }
        return user;
    }
}

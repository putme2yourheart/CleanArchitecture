package io.github.putme2yourheart.cleanarchitecture.domain;

public class User {
    private String login;
    private int id;
    // 头像地址
    private String avatar_url;
    private String url;
    // github address
    private String html_url;
    // follow 他的人的地址
    private String followers_url;
    private String following_url;
    // started 项目地址
    private String starred_url;
    // 仓库地址
    private String repos_url;
    private String type;
    // name
    private String name;
    // email
    private String email;
    // 公共库数量
    private int public_repos;
    // follow数量
    private int followers;
    private int following;
    // 用户创建时间
    private String created_at;
    // 最后更新时间
    private String updated_at;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getStarred_url() {
        return starred_url;
    }

    public void setStarred_url(String starred_url) {
        this.starred_url = starred_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(int public_repos) {
        this.public_repos = public_repos;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return
                "login='" + login + '\'' + '\n' +
                "id=" + id + '\n' +
                "avatar_url='" + avatar_url + '\'' + '\n' +
                "url='" + url + '\'' + '\n' +
                "html_url='" + html_url + '\'' + '\n' +
                "followers_url='" + followers_url + '\'' + '\n' +
                "following_url='" + following_url + '\'' + '\n' +
                "starred_url='" + starred_url + '\'' + '\n' +
                "repos_url='" + repos_url + '\'' + '\n' +
                "type='" + type + '\'' + '\n' +
                "name='" + name + '\'' + '\n' +
                "email='" + email + '\'' + '\n' +
                "public_repos=" + public_repos + '\n' +
                "followers=" + followers + '\n' +
                "following=" + following + '\n' +
                "created_at='" + created_at + '\'' + '\n' +
                "updated_at='" + updated_at + '\'' + '\n';
    }
}

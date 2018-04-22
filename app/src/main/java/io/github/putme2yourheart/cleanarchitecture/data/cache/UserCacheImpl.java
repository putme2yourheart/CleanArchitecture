package io.github.putme2yourheart.cleanarchitecture.data.cache;

import android.content.Context;

import java.io.File;

import io.github.putme2yourheart.cleanarchitecture.data.cache.serializer.FileManager;
import io.github.putme2yourheart.cleanarchitecture.data.cache.serializer.Serializer;
import io.github.putme2yourheart.cleanarchitecture.data.entity.UserEntity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class UserCacheImpl implements UserCache {
    // 文件的默认开头
    private final String DEFAULT_FILE_NAME = "user_";
    // 文件有效时间默认开头
    private final String DEFAULT_FILE_EXPIRED_NAME = "user_expired_";
    // 过期时间
    private final long EXPIRATION_TIME = 60 * 1000 * 10;

    private final File cacheDir;
    private FileManager fileManager;
    private Serializer serializer;

    public UserCacheImpl(Context context, FileManager fileManager, Serializer serializer) {
        this.fileManager = fileManager;
        this.serializer = serializer;
        this.cacheDir = context.getCacheDir();
    }

    @Override
    public Observable<UserEntity> get(final String user) {
        return Observable.create(new ObservableOnSubscribe<UserEntity>() {
            @Override
            public void subscribe(ObservableEmitter<UserEntity> emitter) throws Exception {
                File file = buildFile(user);
                final String content = fileManager.readFile(file);
                final UserEntity userEntity = serializer.deserialize(content, UserEntity.class);

                if (userEntity != null) {
                    emitter.onNext(userEntity);
                    emitter.onComplete();
                } else {
                    emitter.onError(new Exception());
                }
            }
        });
    }

    @Override
    public void put(UserEntity userEntity) {
        if (userEntity != null) {
            // 获取用户名
            final String user = userEntity.getLogin();
            // 保存用户信息到缓存文件
            File file = buildFile(user);
            String content = serializer.serialize(userEntity, UserEntity.class);
            fileManager.writeToFile(file, content);

            // 保存最后更新时间到缓存文件
            File fileLastCacheUpdateTimeMillis = buildExpiredFile(user);
            fileManager.writeLastCacheUpdateTimeMillis(fileLastCacheUpdateTimeMillis);
        }
    }

    @Override
    public boolean isCached(String user) {
        File file = buildFile(user);
        return fileManager.exists(file);
    }

    @Override
    public boolean isExpired(String user) {
        long currentTimeMillis = System.currentTimeMillis();

        boolean expired = (currentTimeMillis - fileManager.readLastCacheUpdateTimeMillis(buildExpiredFile(user))) > EXPIRATION_TIME;
        return expired;
    }

    /**
     * 在缓存目录下创建文件
     *
     * @param user 用户名，标识
     * @return File
     */
    private File buildFile(String user) {
        return new File(cacheDir.getPath() + File.separator + DEFAULT_FILE_NAME + user);
    }

    /**
     * 在缓存目录下创建保存缓存时间的文件
     *
     * @param user 用户名
     * @return File
     */
    private File buildExpiredFile(String user) {
        return new File(cacheDir.getPath() + File.separator + DEFAULT_FILE_EXPIRED_NAME + user);
    }
}

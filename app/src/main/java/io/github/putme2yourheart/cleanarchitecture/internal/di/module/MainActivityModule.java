package io.github.putme2yourheart.cleanarchitecture.internal.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.github.putme2yourheart.cleanarchitecture.data.cache.UserCache;
import io.github.putme2yourheart.cleanarchitecture.data.cache.UserCacheImpl;
import io.github.putme2yourheart.cleanarchitecture.data.cache.serializer.FileManager;
import io.github.putme2yourheart.cleanarchitecture.data.cache.serializer.Serializer;
import io.github.putme2yourheart.cleanarchitecture.data.mapper.RepoEntityJsonMapper;
import io.github.putme2yourheart.cleanarchitecture.data.mapper.UserEntityJsonMapper;
import io.github.putme2yourheart.cleanarchitecture.data.net.RestApi;
import io.github.putme2yourheart.cleanarchitecture.data.net.RestApiImpl;
import io.github.putme2yourheart.cleanarchitecture.data.repository.UserDataRepository;
import io.github.putme2yourheart.cleanarchitecture.data.repository.datasource.UserDataStoreFactory;
import io.github.putme2yourheart.cleanarchitecture.domain.interactor.UserDetailsUseCase;
import io.github.putme2yourheart.cleanarchitecture.domain.repository.UserRepository;

@Module
public class MainActivityModule {

    @Provides
    public UserDetailsUseCase provideUserDetailsUseCase(UserRepository userRepository) {
        return new UserDetailsUseCase(userRepository);
    }

    @Provides
    public UserRepository provideUserRepository(UserDataStoreFactory userDataStoreFactory) {
        return new UserDataRepository(userDataStoreFactory);
    }

    @Provides
    public UserDataStoreFactory provideUserDataStoreFactory(UserCache userCache, RestApi restApi) {
        return new UserDataStoreFactory(userCache, restApi);
    }

    @Provides
    public UserCache provideUserCache(Context context, FileManager fileManager, Serializer serializer) {
        return new UserCacheImpl(context, fileManager, serializer);
    }

    @Provides RestApi provideRestApi(UserEntityJsonMapper userEntityJsonMapper, RepoEntityJsonMapper repoEntityJsonMapper) {
        return new RestApiImpl(userEntityJsonMapper, repoEntityJsonMapper);
    }

    @Provides
    public FileManager provideFileManager() {
        return new FileManager();
    }

    @Provides
    public Serializer provideSerializer() {
        return new Serializer();
    }

    @Provides UserEntityJsonMapper provideUserEntityJsonMapper() {
        return new UserEntityJsonMapper();
    }

    @Provides RepoEntityJsonMapper provideRepoEntityJsonMapper() {
        return new RepoEntityJsonMapper();
    }

}

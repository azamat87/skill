package skill.azamat.mvpauth.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import skill.azamat.mvpauth.data.managers.DataManager;

/**
 * Created by Asus on 08.04.2018.
 */
@Module
public class ModelModule {

    @Provides
    @Singleton
    DataManager provideDataManager() {
        return new DataManager();
    }
}

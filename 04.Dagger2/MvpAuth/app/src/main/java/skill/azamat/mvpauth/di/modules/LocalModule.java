package skill.azamat.mvpauth.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import skill.azamat.mvpauth.data.managers.PreferencesManager;

/**
 * Created by Asus on 08.04.2018.
 */
@Module
public class LocalModule {

    @Provides
    @Singleton
    PreferencesManager providePreferencesManager(Context context) {
        return new PreferencesManager(context);
    }

}

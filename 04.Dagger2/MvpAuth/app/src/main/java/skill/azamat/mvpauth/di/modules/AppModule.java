package skill.azamat.mvpauth.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Asus on 07.04.2018.
 */
@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }
}

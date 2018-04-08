package skill.azamat.mvpauth.di.modules;

import android.content.Context;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Asus on 08.04.2018.
 */
@Module
public class PicassoCacheModule {

    @Provides
    @Singleton
    Picasso providePicasso(Context context) {
        OkHttp3Downloader okHttp3Downloader = new OkHttp3Downloader(context);
        Picasso picasso = new Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build();
        Picasso.setSingletonInstance(picasso);

        return picasso;
    }
}

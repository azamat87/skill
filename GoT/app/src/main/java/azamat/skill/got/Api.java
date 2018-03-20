package azamat.skill.got;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Asus on 19.03.2018.
 */

public interface Api {

    @GET("/api/houses/{house_id}")
    Call<House> getHouse(@Path("house_id") String id);

    @GET("{id}")
    Call<Character> getCharacter(@Path("id") String id);
}

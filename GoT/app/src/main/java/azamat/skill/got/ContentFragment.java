package azamat.skill.got;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment implements OnItemClickListener {

    public final static String HOUSE_ID = "house_id";
    public static final String CHARACTER = "character";

    private String houseId;
    private AdapterCharacter mAdapter;

    public ContentFragment() {}

    public ContentFragment newInstance(Bundle args) {

        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            houseId = getArguments().getString(HOUSE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_list);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new AdapterCharacter(getContext(), houseId);
        rv.setAdapter(mAdapter);
        mAdapter.setClickListener(this);
        Api service = getRetrofit().create(Api.class);

        Call<House> call = service.getHouse(houseId);
        call.enqueue(new Callback<House>(){

            @Override
            public void onResponse(Call<House> call, Response<House> response) {
                final House house = response.body();
                for (String s : house.getSwornMembers()) {
                    Api service = getCharacterRetrofit(s).create(Api.class);
                    String [] sSplit = s.split("/");
                    String id = sSplit[sSplit.length - 1];
                    Call<Character> call2 = service.getCharacter(id);
                    call2.enqueue(new Callback<Character>() {
                        @Override
                        public void onResponse(Call<Character> call, Response<Character> response) {
                            mAdapter.addItem(response.body());
                        }

                        @Override
                        public void onFailure(Call<Character> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<House> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view, Character c, String houseId) {
        Intent intent = new Intent(getContext(), PersonActivity.class);
        Gson gson = new GsonBuilder().create();
        String strC = gson.toJson(c);

        intent.putExtra(CHARACTER, strC);
        intent.putExtra(HOUSE_ID, houseId);
        startActivity(intent);
    }

    private Retrofit getRetrofit() {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://anapioficeandfire.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

    private Retrofit getCharacterRetrofit(String url) {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://anapioficeandfire.com/api/characters/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }


}

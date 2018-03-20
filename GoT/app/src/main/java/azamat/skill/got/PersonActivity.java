package azamat.skill.got;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonActivity extends AppCompatActivity {


    @BindView(R.id.toolbar_character)
    Toolbar toolbar;
    @BindView(R.id.iv_image)
    ImageView imageView;

    @BindView(R.id.tv_words)
    TextView tvWords;
    @BindView(R.id.tv_born)
    TextView tvBorn;
    @BindView(R.id.tv_titles)
    TextView tvTitles;
    @BindView(R.id.tv_aliases)
    TextView tvAliases;

    private String houseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        initUI();
    }

    private void initUI() {
        String fromIntent = getIntent().getExtras().getString(ContentFragment.CHARACTER);
        houseID = getIntent().getExtras().getString(ContentFragment.HOUSE_ID);
        Gson gson = new GsonBuilder().create();
        Character character = gson.fromJson(fromIntent, Character.class);
        toolbar.setTitle(character.getName());
        int image = 0;
        switch (houseID) {
            case "362":
                image = R.drawable.stark;
                break;
            case "229":
                image = R.drawable.lannister;
                break;
            case "378":
                image = R.drawable.targarien;
                break;
        }
        Picasso.get().load(image).into(imageView);

        tvWords.setText(character.getWords());
        tvBorn.setText(character.getBorn());

        StringBuilder builderTitles = new StringBuilder();
        for (String s : character.getTitles()) {
            builderTitles.append(s + "\n");
        }
        tvTitles.setText(builderTitles);
        StringBuilder builderAliases = new StringBuilder();
        for (String s : character.getAliases()) {
            builderAliases.append(s + "\n");
        }
        tvAliases.setText(builderAliases);
    }
}

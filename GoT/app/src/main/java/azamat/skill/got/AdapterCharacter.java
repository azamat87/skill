package azamat.skill.got;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 19.03.2018.
 */

public class AdapterCharacter extends RecyclerView.Adapter<AdapterCharacter.VHCharacter>{

    private ArrayList<Character> mList;
    private Context mContext;
    private OnItemClickListener clickListener;
    private String houseId;

    public AdapterCharacter(Context context, String houseId) {
        this.mContext = context;
        this.mList = new ArrayList<>();
        this.houseId = houseId;
    }

    @Override
    public VHCharacter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);

        return new VHCharacter(view);
    }

    @Override
    public void onBindViewHolder(VHCharacter holder, int position) {
        Character character = mList.get(position);

        if (character.getName() != null) {
            holder.tvTitleCharacter.setText(character.getName());
        }
        int image = 0;
        switch (houseId) {
            case "362":
                image = R.drawable.stark_icon;
                break;
            case "229":
                image = R.drawable.lanister_icon;
                break;
            case "378":
                image = R.drawable.icon_title;
                break;
        }
        Picasso.get().load(image).into(holder.ivCharacter);

        holder.tvDescCharacter.setText(character.getCulture());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addItem(Character c) {
        mList.add(c);
        notifyItemInserted(mList.size());
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    public class VHCharacter extends RecyclerView.ViewHolder{

        @BindView(R.id.image_house)
        ImageView ivCharacter;
        @BindView(R.id.tv_name_character)
        TextView tvTitleCharacter;
        @BindView(R.id.tv_desc_character)
        TextView tvDescCharacter;

        public VHCharacter(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(v, mList.get(getAdapterPosition()), houseId);
                }
            });
        }
    }

}

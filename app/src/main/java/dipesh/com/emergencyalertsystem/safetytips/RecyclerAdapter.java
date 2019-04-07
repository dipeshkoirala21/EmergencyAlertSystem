package dipesh.com.emergencyalertsystem.safetytips;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dipesh.com.emergencyalertsystem.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.TipsViewHolder> {
    private Context mContext;
    private List<TipsData> TipsList;

    RecyclerAdapter(Context mContext, List<TipsData> TipsList) {
        this.mContext = mContext;
        this.TipsList = TipsList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.TipsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.safety_cardview, parent, false);
        return new RecyclerAdapter.TipsViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapter.TipsViewHolder holder, int position) {
        holder.Title.setText(TipsList.get(position).getTipsName());
//        holder.Desc.setText(TipsList.get(position).getTipsDesc());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mIntent = new Intent(mContext, DetailActivity.class);
                mIntent.putExtra("Title", TipsList.get(holder.getAdapterPosition()).getTipsName());
                mIntent.putExtra("Description", TipsList.get(holder.getAdapterPosition()).getTipsDesc());
                mContext.startActivity(mIntent);

    }
        });
    }

    @Override
    public int getItemCount() {
        return TipsList.size();
    }

    class TipsViewHolder extends RecyclerView.ViewHolder {


        TextView Title;
        TextView Desc;
        CardView cardView;

        TipsViewHolder(View itemView) {
            super(itemView);

//            mImage = itemView.findViewById(R.id.feature_icon);
            Title = itemView.findViewById(R.id.safety_feature_name);
            cardView = itemView.findViewById(R.id.safetycardview);
        }
    }
}

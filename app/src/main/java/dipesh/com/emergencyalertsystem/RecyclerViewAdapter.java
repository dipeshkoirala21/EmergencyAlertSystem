package dipesh.com.emergencyalertsystem;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dipesh.com.emergencyalertsystem.bor.UserLoginActivity;
import dipesh.com.emergencyalertsystem.emrCall.EmergencyCall;
import dipesh.com.emergencyalertsystem.feedback.FeedbackActivity;
import dipesh.com.emergencyalertsystem.hk.HotkeyNavigation;
import dipesh.com.emergencyalertsystem.safetytips.SafetyTips;
import dipesh.com.emergencyalertsystem.vr.ViolenceReport;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.FeatureViewHolder> {

    private Context mContext;
    private List<FeatureData> mFeatureList;

    RecyclerViewAdapter(Context mContext, List<FeatureData> mFeatureList) {
        this.mContext = mContext;
        this.mFeatureList = mFeatureList;
    }

    @NonNull
    @Override
    public FeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new FeatureViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FeatureViewHolder holder, int position) {
        holder.mImage.setImageResource(mFeatureList.get(position).getFeatureImage());
        holder.mTitle.setText(mFeatureList.get(position).getFeatureName());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id = holder.getAdapterPosition();
                if (id == 0) {
                    Intent mIntent = new Intent(mContext, HotkeyNavigation.class);
                    mIntent.putExtra("Title", mFeatureList.get(holder.getAdapterPosition()).getFeatureName());
                    mIntent.putExtra("Image", mFeatureList.get(holder.getAdapterPosition()).getFeatureImage());
                    mContext.startActivity(mIntent);
                } else if (id == 1) {
                    Intent mIntent = new Intent(mContext, EmergencyCall.class);
                    mIntent.putExtra("Title", mFeatureList.get(holder.getAdapterPosition()).getFeatureName());
                    mIntent.putExtra("Image", mFeatureList.get(holder.getAdapterPosition()).getFeatureImage());
                    mContext.startActivity(mIntent);
                } else if (id == 2) {
                    Intent mIntent = new Intent(mContext, UserLoginActivity.class);
                    mIntent.putExtra("Title", mFeatureList.get(holder.getAdapterPosition()).getFeatureName());
                    mIntent.putExtra("Image", mFeatureList.get(holder.getAdapterPosition()).getFeatureImage());
                    mContext.startActivity(mIntent);
                } else if (id == 3) {
                    Intent mIntent = new Intent(mContext, ViolenceReport.class);
                    mIntent.putExtra("Title", mFeatureList.get(holder.getAdapterPosition()).getFeatureName());
                    mIntent.putExtra("Image", mFeatureList.get(holder.getAdapterPosition()).getFeatureImage());
                    mContext.startActivity(mIntent);
                } else if (id == 4) {
                    Intent mIntent = new Intent(mContext, SafetyTips.class);
                    mIntent.putExtra("Title", mFeatureList.get(holder.getAdapterPosition()).getFeatureName());
                    mIntent.putExtra("Image", mFeatureList.get(holder.getAdapterPosition()).getFeatureImage());
                    mContext.startActivity(mIntent);
                } else if (id == 5) {
                    Intent mIntent = new Intent(mContext, FeedbackActivity.class);
                    mIntent.putExtra("Title", mFeatureList.get(holder.getAdapterPosition()).getFeatureName());
                    mIntent.putExtra("Image", mFeatureList.get(holder.getAdapterPosition()).getFeatureImage());
                    mContext.startActivity(mIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFeatureList.size();
    }

    static class FeatureViewHolder extends RecyclerView.ViewHolder {

        ImageView mImage;
        TextView mTitle;
        CardView mCardView;

        FeatureViewHolder(View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.feature_icon);
            mTitle = itemView.findViewById(R.id.feature_name);
            mCardView = itemView.findViewById(R.id.cardview);
        }
    }
}


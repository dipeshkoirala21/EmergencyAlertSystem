package dipesh.com.emergencyalertsystem.Call;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import java.util.List;

import dipesh.com.emergencyalertsystem.R;
import dipesh.com.emergencyalertsystem.safetytips.RecyclerAdapter;




public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    View view ;
    private Context mContext ;
    private List<Number> mData ;
    private AdapterCallback adapterCallback;

    RecyclerViewAdapter(Context mContext, List<Number> fmList, AdapterCallback adapterCallback) {
        this.mContext = mContext;
        this.mData = fmList;
        this.adapterCallback = adapterCallback;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.num_card_view,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.name.setText(mData.get(position).getServiceName());
        holder.contact.setText(mData.get(position).getContact());
        holder.address.setText(mData.get(position).getAddress());


        holder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+mData.get(position).getContact()));
                view.getContext().startActivity(callIntent);


            }
        });

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView contact;
        TextView address;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.nameText) ;
            contact = (TextView) itemView.findViewById(R.id.contactText) ;
            address = (TextView) itemView.findViewById(R.id.addressText) ;

            cardView = (CardView) itemView.findViewById(R.id.cardview_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "inside viewholder position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });


        }
    }


}

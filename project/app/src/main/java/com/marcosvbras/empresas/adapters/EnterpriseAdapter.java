package com.marcosvbras.empresas.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.models.EnterpriseModel;

import java.util.List;

public class EnterpriseAdapter extends RecyclerView.Adapter<EnterpriseAdapter.MyViewHolder> {

    private List<EnterpriseModel> listEnterprises;
    private Context context;

    public EnterpriseAdapter(List<EnterpriseModel> listEnterprises, Context context) {
        this.listEnterprises = listEnterprises;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_enterprise, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        if(listEnterprises != null) {
            EnterpriseModel enterprise = listEnterprises.get(position);
            myViewHolder.textViewPosition.setText(String.valueOf(position + 1));
            myViewHolder.textViewName.setText(enterprise.getEnterpriseName());
            myViewHolder.textViewCountry.setText(enterprise.getCountry());
            myViewHolder.textViewType.setText(enterprise.getEnterpriseType().getEnterpriseTypeName());
        }
    }

    @Override
    public int getItemCount() {
        if(listEnterprises != null)
            return listEnterprises.size();

        return 0;
    }

    public void updateItems(List<EnterpriseModel> listEnterprises) {
        this.listEnterprises = listEnterprises;
        this.notifyDataSetChanged();
    }

    public List<EnterpriseModel> getCurrentList() {
        return this.listEnterprises;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewPosition;
        TextView textViewName;
        TextView textViewType;
        TextView textViewCountry;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewPosition = itemView.findViewById(R.id.text_view_position);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewType = itemView.findViewById(R.id.text_view_type);
            textViewCountry = itemView.findViewById(R.id.text_view_country);
        }
    }
}

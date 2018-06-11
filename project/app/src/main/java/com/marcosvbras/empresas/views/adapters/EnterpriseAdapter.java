package com.marcosvbras.empresas.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marcosvbras.empresas.R;
import com.marcosvbras.empresas.models.domain.Enterprise;

import java.util.List;

public class EnterpriseAdapter extends RecyclerView.Adapter<EnterpriseAdapter.EnterpriseViewHolder> {

    private List<Enterprise> listEnterprises;
    private Context context;

    public EnterpriseAdapter(@NonNull List<Enterprise> listEnterprises, @NonNull Context context) {
        this.listEnterprises = listEnterprises;
        this.context = context;
    }

    @NonNull
    @Override
    public EnterpriseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_enterprise, parent, false);
        return new EnterpriseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EnterpriseViewHolder enterpriseViewHolder, int position) {
        if(listEnterprises != null)
            enterpriseViewHolder.setData(listEnterprises.get(position), position);
    }

    @Override
    public int getItemCount() {
        if(listEnterprises != null)
            return listEnterprises.size();

        return 0;
    }

    public void updateItems(@NonNull List<Enterprise> listEnterprises) {
        this.listEnterprises.clear();
        this.listEnterprises.addAll(listEnterprises);
        this.notifyDataSetChanged();
    }

    public List<Enterprise> getCurrentList() {
        return this.listEnterprises;
    }

    public class EnterpriseViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewPosition;
        private TextView textViewName;
        private TextView textViewType;
        private TextView textViewCountry;

        public EnterpriseViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPosition = itemView.findViewById(R.id.text_view_position);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewType = itemView.findViewById(R.id.text_view_type);
            textViewCountry = itemView.findViewById(R.id.text_view_country);
        }

        private void setData(@NonNull Enterprise enterprise, @NonNull int position) {
            textViewPosition.setText("E" + String.valueOf(position + 1));
            textViewName.setText(enterprise.getEnterpriseName());
            textViewCountry.setText(enterprise.getCountry());
            textViewType.setText(enterprise.getEnterpriseType().getEnterpriseTypeName());
        }
    }
}

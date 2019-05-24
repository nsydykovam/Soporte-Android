package com.example.jorgeantoniojuarezleyva.reportedeeventos.extras;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jorgeantoniojuarezleyva.reportedeeventos.dao.ReporteMantenimientoDao;
import com.example.jorgeantoniojuarezleyva.reportedeeventos.Modelo.ReporteMantenimiento;

import java.util.ArrayList;

public class ReporteClickAdapter extends RecyclerView.Adapter<ReporteClickAdapter.ReporteClickViewHolder> {
    View view;

    public class ReporteClickViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView text1, text2;

        ReporteClickViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            itemView.setOnClickListener(this);
            text1 = (TextView) itemView.findViewById(android.R.id.text1);
            text2 = (TextView) itemView.findViewById(android.R.id.text2);
        }

        @Override
        public void onClick(View v) {
            // The user may not set a click listener for list items, in which case our listener
            // will be null, so we need to check for this
            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick(v, getLayoutPosition());
            }
        }
    }

    private ArrayList<ReporteMantenimiento> mReporteMantenimiento;

    public ReporteClickAdapter(ArrayList<ReporteMantenimiento> arrayList) {
        mReporteMantenimiento = arrayList;
    }

    @Override
    public int getItemCount() {
        return mReporteMantenimiento.size();
    }

    @Override
    public ReporteClickViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ReporteClickViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReporteClickViewHolder holder, int position) {
        ReporteMantenimiento reporteMantenimiento = mReporteMantenimiento.get(position);

        ReporteMantenimientoDao reporteMantenimientoDao = new ReporteMantenimientoDao();
        ArrayList<ReporteMantenimiento> reportesMantenimientos = new ArrayList<>();

        // Obtener todos los reportes
        for(ReporteMantenimiento rm : reporteMantenimientoDao.ver(view.getContext()))
            reportesMantenimientos.add(rm);

        String letra;
        if(reporteMantenimiento.isEvento())
            letra = "(E) ";
        else
            letra = "(M) ";
        if(reporteMantenimiento.getAsunto().equals(""))
            holder.text1.setText(letra + "Reporte " + reporteMantenimiento.getIdReporteMantenimiento());
        else
            holder.text1.setText(letra + reporteMantenimiento.getAsunto());
        if(reporteMantenimiento.getFecha() != null)
            holder.text2.setText(reporteMantenimiento.getFecha().toString());
        else
            holder.text2.setText("Sin fecha");
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    private OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }

    public void setOnEntryClickListener(OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }

}

package uts.fernanda.tasks.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import uts.fernanda.tasks.R;
import uts.fernanda.tasks.model.Agenda;

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.AgendaViewHolder> {
    private List<Agenda> agendaList;

    public AgendaAdapter(List<Agenda> agendaList) {
        this.agendaList = agendaList;
    }

    @NonNull
    @Override
    public AgendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_agenda, parent, false);
        return new AgendaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AgendaViewHolder holder, int position) {
        Agenda agenda = agendaList.get(position);
        holder.titleTextView.setText(agenda.getName());
        holder.descriptionTextView.setText(agenda.getDescription());
        holder.statusTextView.setText(agenda.getStatus());

        // Customize status color based on completion status
        if (agenda.getStatus().equalsIgnoreCase("selesai")) {
            holder.statusTextView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.holo_green_dark));
        } else {
            holder.statusTextView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.holo_red_dark));
        }
    }


    @Override
    public int getItemCount() {
        return agendaList.size();
    }

    public void updateData(List<Agenda> newAgendaList) {
        agendaList = newAgendaList;
        notifyDataSetChanged();
    }

    public static class AgendaViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, descriptionTextView, statusTextView;

        public AgendaViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
        }
    }
}

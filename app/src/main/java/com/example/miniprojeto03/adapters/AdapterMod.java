package com.example.miniprojeto03.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojeto03.R;
import com.example.miniprojeto03.modelo.Tarefa;

import java.util.List;

public class AdapterMod extends RecyclerView.Adapter<AdapterMod.MinhaViewHolder> {

    private List<Tarefa> mTarefas;

    public AdapterMod(List<Tarefa> lista){
        mTarefas = lista;
    }

    @NonNull
    @Override
    public MinhaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.elemento_lista,parent,false);

        return new MinhaViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MinhaViewHolder holder, int position) {

        Tarefa tarefa = mTarefas.get(position);
        holder.tvTituloNota.setText(tarefa.getTitulo());

    }

    @Override
    public int getItemCount() {
        return mTarefas.size();
    }

    public class MinhaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        CardView cvTarefa;
        TextView tvTituloNota;

        public MinhaViewHolder(View itemView){
            super(itemView);

            cvTarefa = itemView.findViewById(R.id.cvTarefa);
            tvTituloNota = itemView.findViewById(R.id.tvTituloNota);

            cvTarefa.setOnClickListener(this);
            cvTarefa.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.clicouNaTarefa(getLayoutPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            listener.pressionouTarefa(getLayoutPosition());
            return true;
        }
    }

    public interface AoClicarNoItem{
        public void clicouNaTarefa(int pos);
        public void pressionouTarefa(int pos);
    }

    public AoClicarNoItem listener;

    public void implementaAoClicarNoItem(AoClicarNoItem listener){
        this.listener = listener;
    }
}

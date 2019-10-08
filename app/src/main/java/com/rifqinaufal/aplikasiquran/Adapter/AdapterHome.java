package com.rifqinaufal.aplikasiquran.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.rifqinaufal.aplikasiquran.R;
import com.rifqinaufal.aplikasiquran.modal.ModalAyatHome;

import java.util.ArrayList;

public class AdapterHome extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    View view;
    Context context;
    ArrayList<ModalAyatHome> modalAyatHomes;

    public AdapterHome(Context context, ArrayList<ModalAyatHome> modalAyatHomes) {
        this.context = context;
        this.modalAyatHomes = modalAyatHomes;
    }

    class MyAdapterHome extends RecyclerView.ViewHolder{

        TextView nomor,nama,asma,arti;

        public MyAdapterHome(@NonNull View itemView) {
            super(itemView);

            nomor = itemView.findViewById(R.id.nomor);
            nama = itemView.findViewById(R.id.name);
            asma = itemView.findViewById(R.id.asma);
            arti = itemView.findViewById(R.id.arti);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home,parent,false);
        return  new MyAdapterHome(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int  position) {

        ((MyAdapterHome)holder).nomor.setText(modalAyatHomes.get(position).getNomor());
        ((MyAdapterHome)holder).nama.setText(modalAyatHomes.get(position).getNama());
        ((MyAdapterHome)holder).asma.setText(modalAyatHomes.get(position).getAsma());
        ((MyAdapterHome)holder).arti.setText(modalAyatHomes.get(position).getArti());



    }

    @Override
    public int getItemCount() {
        return modalAyatHomes.size();
    }
}

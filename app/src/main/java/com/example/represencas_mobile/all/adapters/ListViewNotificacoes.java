package com.example.represencas_mobile.all.adapters;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.represencas_mobile.R;
import com.example.represencas_mobile.model.Notificacao;
import com.example.represencas_mobile.model.list_model.TurmasList;
import com.example.represencas_mobile.remote.AttendanceService;
import com.example.represencas_mobile.remote.RetrofitClient;

import java.util.List;

import retrofit2.Call;

public class ListViewNotificacoes extends ArrayAdapter<Notificacao> {

    private Activity context;
    private List<Notificacao> mData;

    public ListViewNotificacoes(Activity context, List<Notificacao> mData) {
        super(context, R.layout.row_notificacao, mData);
        this.context = context;
        this.mData = mData;
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"})
        View view = inflater.inflate(R.layout.row_notificacao, null, true);

        TextView tvTitulo = view.findViewById(R.id.tv_titulo_not);
        TextView tvPreview = view.findViewById(R.id.tv_preview_not);
        TextView tvData = view.findViewById(R.id.tv_data_not);
        TextView tvTurma = view.findViewById(R.id.tv_turma_not);
        ImageView imgPhoto = view.findViewById(R.id.img_photo_not);

        String titulo = mData.get(position).getTitulo();
        String preview = mData.get(position).getMensagem();
        String[] dataArray = mData.get(position).getData().split(" ")[0].split("-");
        String data = dataArray[2] + "/" + dataArray[1] + "/" + dataArray[0];
        String turma = mData.get(position).getDisciplina() + " " + mData.get(position).getAno() + " " + mData.get(position).getRegime();

        tvTitulo.setText(titulo);
        tvPreview.setText(preview);
        tvData.setText(data);
        tvTurma.setText(turma);

        return view;
    }
}

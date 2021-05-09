package com.example.represencas_mobile.estudante_activities.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.represencas_mobile.R;
import com.example.represencas_mobile.all.adapters.ListViewNotificacoes;
import com.example.represencas_mobile.all.storage.LocalStorage;
import com.example.represencas_mobile.model.Notificacao;
import com.example.represencas_mobile.model.list_model.NotificacaoList;
import com.example.represencas_mobile.model.list_model.TurmasList;
import com.example.represencas_mobile.remote.AttendanceService;
import com.example.represencas_mobile.remote.RetrofitClient;

import java.util.LinkedList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Imildo Sitoe
 * @see androidx.fragment.app.Fragment
 * A simple {@link Fragment} subclass.
 */
public class EstPresencasNotificationsFragment extends Fragment {

    private ListView listView;
    private LinkedList<Notificacao> listNotificacoes;
    private LocalStorage localStorage;
    private SwipeRefreshLayout pullToRefresh;

    public EstPresencasNotificationsFragment() {
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_est_presencas_notifications, container, false);

        pullToRefresh = view.findViewById(R.id.pull_to_refres_not);
        listView = view.findViewById(R.id.listview_not);
        listNotificacoes = new LinkedList<>();
        localStorage = new LocalStorage(Objects.requireNonNull(getContext()));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        fillListView();
        listClick();
        pullToRefresh();
    }

    private void fillListView() {
        listNotificacoes.clear();
        AttendanceService attendanceService = RetrofitClient.getClient().create(AttendanceService.class);
        Call<NotificacaoList> call = attendanceService.getNotificacoes(localStorage.getIdUser());
        call.enqueue(new Callback<NotificacaoList>() {
            @Override
            public void onResponse(@NonNull Call<NotificacaoList> call, @NonNull Response<NotificacaoList> response) {
                assert response.body() != null;
                listNotificacoes.addAll(response.body().getNotificacoes());

                ListViewNotificacoes adapter = new ListViewNotificacoes(getActivity(), listNotificacoes);
                listView.setAdapter(adapter);

                pullToRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(@NonNull Call<NotificacaoList> call, @NonNull Throwable t) {

            }
        });
    }

    private void listClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tvPreviewNot = view.findViewById(R.id.tv_preview_not);
                TextView tvTituloNot = view.findViewById(R.id.tv_titulo_not);

                AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
                builder.setTitle(tvTituloNot.getText());
                builder.setMessage(tvPreviewNot.getText());
                builder.setNegativeButton("Fechar ", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void pullToRefresh() {
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullToRefresh.setRefreshing(true);
                fillListView();
            }
        });
    }
}

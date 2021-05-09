package com.example.represencas_mobile.estudante_activities.fragments;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.represencas_mobile.R;
import com.example.represencas_mobile.all.storage.LocalStorage;
import com.example.represencas_mobile.model.fake_model.DoughnutChartFaltas;
import com.example.represencas_mobile.model.fake_model.Turmas;
import com.example.represencas_mobile.model.list_model.FaltasList;
import com.example.represencas_mobile.model.list_model.TurmasList;
import com.example.represencas_mobile.remote.AttendanceService;
import com.example.represencas_mobile.remote.RetrofitClient;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class EstPresencasEstatisticasFragment extends Fragment {

    private PieChart pieChart;
    private TextView tvNome, tvAulasTotal, tvFaltasReman, tvFaltasCom, tvIsParticipante;
    private Button btn1, btn2, btn3, btn4, btn5, btn6;
    private Intent intent;
    private LocalStorage localStorage;
    private List<Turmas> turmasG = new LinkedList<>();
    private int[] cores = new int[]{
            Color.rgb(255, 65, 151),
            Color.rgb(2, 187, 169)
    };
    private SwipeRefreshLayout pullToRefresh;
    private Button buttonG;

    public EstPresencasEstatisticasFragment() {
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_est_presencas_estatisticas, container, false);

        pullToRefresh = view.findViewById(R.id.pull_to_refres_est);
        pieChart = view.findViewById(R.id.pie_presencas_estat);
        tvNome = view.findViewById(R.id.tv_nome_part_est);
        tvAulasTotal = view.findViewById(R.id.tv_aulas_total_est);
        tvFaltasReman = view.findViewById(R.id.tv_faltas_remanescentes_est);
        tvFaltasCom = view.findViewById(R.id.tv_faltas_cometidas_est);
        tvIsParticipante = view.findViewById(R.id.tv_is_participante_est);
        intent = Objects.requireNonNull(getActivity()).getIntent();
        localStorage = new LocalStorage(Objects.requireNonNull(getContext()));
        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);
        btn4 = view.findViewById(R.id.btn4);
        btn5 = view.findViewById(R.id.btn5);
        btn6 = view.findViewById(R.id.btn6);

        buttonsAction();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setLabels();
        pullToRefresh();
    }

    private void    setLabels() {
        String nome = localStorage.getNomeUser() + " " + localStorage.getApelidoUser();
        tvNome.setText(nome);

        AttendanceService attendanceService = RetrofitClient.getClient().create(AttendanceService.class);
        Call<TurmasList> call = attendanceService.getTurmas(localStorage.getIdUser(), 2020);
        call.enqueue(new Callback<TurmasList>() {
            @Override
            public void onResponse(@NonNull Call<TurmasList> call, @NonNull Response<TurmasList> response) {

                assert response.body() != null;
                final List<Turmas> turmas = response.body().getTurmas();
                turmasG = turmas;


                try {
                    String turma0 = turmas.get(0).getDisciplina_sigla() + " " + turmas.get(0).getAno() + " " + turmas.get(0).getRegime();
                    btn1.setText(turma0);
                    btn1.setId(turmas.get(0).getId_alocacao());

                    String turma1 = turmas.get(1).getDisciplina_sigla() + " " + turmas.get(1).getAno() + " " + turmas.get(1).getRegime();
                    btn2.setText(turma1);
                    btn2.setId(turmas.get(1).getId_alocacao());

                    String turma2 = turmas.get(2).getDisciplina_sigla() + " " + turmas.get(2).getAno() + " " + turmas.get(2).getRegime();
                    btn3.setText(turma2);
                    btn3.setId(turmas.get(2).getId_alocacao());

                    String turma3 = turmas.get(3).getDisciplina_sigla() + " " + turmas.get(3).getAno() + " " + turmas.get(3).getRegime();
                    btn4.setText(turma3);
                    btn4.setId(turmas.get(3).getId_alocacao());

                    String turma4 = turmas.get(4).getDisciplina_sigla() + " " + turmas.get(4).getAno() + " " + turmas.get(4).getRegime();
                    btn5.setText(turma4);
                    btn5.setId(turmas.get(4).getId_alocacao());

                    String turma5 = turmas.get(5).getDisciplina_sigla() + " " + turmas.get(5).getAno() + " " + turmas.get(5).getRegime();
                    btn6.setText(turma5);
                    btn6.setId(turmas.get(5).getId_alocacao());
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                }
            }

            @Override
            public void onFailure(@NonNull Call<TurmasList> call, @NonNull Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }

    private void buttonsAction() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButtons(btn1);
                btn1.setBackgroundColor(Color.rgb(215, 27, 96));
                btn2.setBackgroundColor(Color.rgb(187, 132, 150));
                btn3.setBackgroundColor(Color.rgb(187, 132, 150));
                btn4.setBackgroundColor(Color.rgb(187, 132, 150));
                btn5.setBackgroundColor(Color.rgb(187, 132, 150));
                btn6.setBackgroundColor(Color.rgb(187, 132, 150));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButtons(btn2);
                btn2.setBackgroundColor(Color.rgb(215, 27, 96));
                btn1.setBackgroundColor(Color.rgb(187, 132, 150));
                btn3.setBackgroundColor(Color.rgb(187, 132, 150));
                btn4.setBackgroundColor(Color.rgb(187, 132, 150));
                btn5.setBackgroundColor(Color.rgb(187, 132, 150));
                btn6.setBackgroundColor(Color.rgb(187, 132, 150));
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButtons(btn3);
                btn3.setBackgroundColor(Color.rgb(215, 27, 96));
                btn1.setBackgroundColor(Color.rgb(187, 132, 150));
                btn2.setBackgroundColor(Color.rgb(187, 132, 150));
                btn4.setBackgroundColor(Color.rgb(187, 132, 150));
                btn5.setBackgroundColor(Color.rgb(187, 132, 150));
                btn6.setBackgroundColor(Color.rgb(187, 132, 150));
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButtons(btn4);
                btn4.setBackgroundColor(Color.rgb(215, 27, 96));
                btn1.setBackgroundColor(Color.rgb(187, 132, 150));
                btn2.setBackgroundColor(Color.rgb(187, 132, 150));
                btn3.setBackgroundColor(Color.rgb(187, 132, 150));
                btn5.setBackgroundColor(Color.rgb(187, 132, 150));
                btn6.setBackgroundColor(Color.rgb(187, 132, 150));
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButtons(btn5);
                btn5.setBackgroundColor(Color.rgb(215, 27, 96));
                btn1.setBackgroundColor(Color.rgb(187, 132, 150));
                btn2.setBackgroundColor(Color.rgb(187, 132, 150));
                btn3.setBackgroundColor(Color.rgb(187, 132, 150));
                btn4.setBackgroundColor(Color.rgb(187, 132, 150));
                btn6.setBackgroundColor(Color.rgb(187, 132, 150));
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButtons(btn6);
                btn6.setBackgroundColor(Color.rgb(215, 27, 96));
                btn1.setBackgroundColor(Color.rgb(187, 132, 150));
                btn2.setBackgroundColor(Color.rgb(187, 132, 150));
                btn3.setBackgroundColor(Color.rgb(187, 132, 150));
                btn4.setBackgroundColor(Color.rgb(187, 132, 150));
                btn5.setBackgroundColor(Color.rgb(187, 132, 150));
            }
        });
    }

    private void clickButtons(Button btn) {
        buttonG = btn;

        try {
            if (!btn.getText().toString().trim().equals("-")) {
                int idturmaClicada = btn.getId();

                for (Turmas turma : turmasG) {
                    if (idturmaClicada == turma.getId_alocacao()) {

                        if (turma.getIs_excluido() == 1) {
                            tvIsParticipante.setText("Excluido");
                        } else if (turma.getIs_excluido() == 0) {
                            tvIsParticipante.setText("Participante");
                        }

                        AttendanceService attendanceService = RetrofitClient.getClient().create(AttendanceService.class);
                        Call<FaltasList> call = attendanceService.getFaltas(idturmaClicada, turma.getId_inscricao());
                        call.enqueue(new Callback<FaltasList>() {
                            @Override
                            public void onResponse(@NonNull Call<FaltasList> call, @NonNull Response<FaltasList> response) {
                                assert response.body() != null;
                                DoughnutChartFaltas f = response.body().getFaltas().get(0);

                                int aulasTotal = f.getCarga_horaria();
                                int faltas = f.getFaltas();
                                int faltasReman = aulasTotal - faltas;

                                tvAulasTotal.setText(String.valueOf(aulasTotal));
                                tvFaltasCom.setText(String.valueOf(faltas));
                                tvFaltasReman.setText(String.valueOf(faltasReman));

                                //Grafico aqui
                                ArrayList<PieEntry> yValues = new ArrayList<>();
                                yValues.add(new PieEntry(faltas, "Faltas Cometidas"));
                                yValues.add(new PieEntry(faltasReman, "Faltas Remanescentes"));

                                final PieDataSet dataSet = new PieDataSet(yValues, "");
                                dataSet.setLabel("");
                                dataSet.setValueTextColor(Color.WHITE);
                                dataSet.setColors(cores);

                                Legend legend = pieChart.getLegend();
                                legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

                                PieData pieData = new PieData(dataSet);
                                pieChart.setData(pieData);
                                Description description = new Description();
                                description.setText("");
                                pieChart.setDescription(description);
                                pieChart.setDrawEntryLabels(false);
                                //pieChart.setUsePercentValues(true);
                                //pieChart.setCenterText("Faltas (%)");
                                pieChart.setCenterTextSize(9);
                                pieChart.animateXY(1000, 1000);
                                pieChart.invalidate();
                            }

                            @Override
                            public void onFailure(@NonNull Call<FaltasList> call, @NonNull Throwable t) {
                                Log.e("error > faltas", t.toString());
                            }
                        });
                        break;
                    }
                }
            }
        }catch (NullPointerException e){}
    }

    private void pullToRefresh() {
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullToRefresh.setRefreshing(true);
                setLabels();
                clickButtons(buttonG);
                pullToRefresh.setRefreshing(false);
            }
        });
    }
}

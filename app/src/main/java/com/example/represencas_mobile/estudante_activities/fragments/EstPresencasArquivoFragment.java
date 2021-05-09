package com.example.represencas_mobile.estudante_activities.fragments;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.represencas_mobile.R;
import com.example.represencas_mobile.all.adapters.MyHashMap;
import com.example.represencas_mobile.all.storage.LocalStorage;
import com.example.represencas_mobile.model.fake_model.DoughnutChartFaltas;
import com.example.represencas_mobile.model.fake_model.Turmas;
import com.example.represencas_mobile.model.list_model.FaltasList;
import com.example.represencas_mobile.model.list_model.TurmasList;
import com.example.represencas_mobile.remote.AttendanceService;
import com.example.represencas_mobile.remote.RetrofitClient;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstPresencasArquivoFragment extends Fragment {

    private EditText edtAno;
    private ImageButton btnSearch;
    private Spinner spTurmas;
    private TextView tvNome;
    private TextView tvIsParticipante;
    private TextView tvAulasTotal;
    private TextView tvFaltasCometidas;
    private BarChart barChart;
    private LocalStorage localStorage;
    private List<Turmas> turmasG = new LinkedList<>();
    private ArrayAdapter<MyHashMap> adapter;
    private int[] colors = new int[]{
            Color.rgb(255, 65, 151),
            Color.rgb(2, 187, 169),
            Color.rgb(5, 172, 130),
            Color.rgb(67, 112, 120),
            Color.rgb(23, 162, 190),
            Color.rgb(42, 180, 80),
            Color.rgb(50, 32, 40),
            Color.rgb(12, 253, 100),
            Color.rgb(25, 172, 200),
            Color.rgb(255, 190, 10),
            Color.rgb(3, 12, 13),
            Color.rgb(9, 180, 13),
    };


    public EstPresencasArquivoFragment() {
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_est_presencas_arquivo, container, false);

        edtAno = view.findViewById(R.id.edt_ano);
        btnSearch = view.findViewById(R.id.btn_search_ano);
        spTurmas = view.findViewById(R.id.sp_turmas);
        tvNome = view.findViewById(R.id.tv_nome_arq);
        tvIsParticipante = view.findViewById(R.id.tv_is_participante_arq);
        tvAulasTotal = view.findViewById(R.id.tv_aulas_total_arq);
        tvFaltasCometidas = view.findViewById(R.id.tv_faltas_cometidas_arq);
        barChart = view.findViewById(R.id.bar_chart_arq);
        localStorage = new LocalStorage(Objects.requireNonNull(getContext()));

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spTurmas.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        setLabels();
    }

    private void setLabels() {
        String nome = localStorage.getNomeUser() + " " + localStorage.getApelidoUser();
        tvNome.setText(nome);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AttendanceService attendanceService = RetrofitClient.getClient().create(AttendanceService.class);
                Call<TurmasList> call = attendanceService.getTurmas(localStorage.getIdUser(), Integer.parseInt(edtAno.getText().toString()));
                call.enqueue(new Callback<TurmasList>() {
                    @Override
                    public void onResponse(@NonNull Call<TurmasList> call, @NonNull Response<TurmasList> response) {
                        assert response.body() != null;
                        List<Turmas> turmas = response.body().getTurmas();
                        turmasG = turmas;
                        LinkedList<MyHashMap> list = new LinkedList<>();
                        adapter.clear();

                        for (Turmas turma : turmas) {
                            int idAlocacao = turma.getId_alocacao();
                            String t = turma.getDisciplina_sigla() + " " + turma.getAno() + " " + turma.getRegime();

                            list.add(new MyHashMap(idAlocacao, t));
                        }
                        adapter.addAll(list);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(@NonNull Call<TurmasList> call, @NonNull Throwable t) {

                    }
                });
                plotChart();
            }
        });

        spTurmas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MyHashMap hashMap = (MyHashMap) parent.getItemAtPosition(position);
                int idturmaSelecionada = hashMap.getKey();

                for (Turmas turma : turmasG) {
                    if (idturmaSelecionada == turma.getId_alocacao()) {

                        if (turma.getIs_excluido() == 1) {
                            tvIsParticipante.setText("Excluido");
                        } else if (turma.getIs_excluido() == 0) {
                            tvIsParticipante.setText("Nao Excluido");
                        }

                        AttendanceService attendanceService = RetrofitClient.getClient().create(AttendanceService.class);
                        Call<FaltasList> call = attendanceService.getFaltas(idturmaSelecionada, turma.getId_inscricao());
                        call.enqueue(new Callback<FaltasList>() {
                            @Override
                            public void onResponse(@NonNull Call<FaltasList> call, @NonNull Response<FaltasList> response) {
                                assert response.body() != null;
                                DoughnutChartFaltas f = response.body().getFaltas().get(0);

                                tvAulasTotal.setText(String.valueOf(f.getCarga_horaria()));
                                tvFaltasCometidas.setText(String.valueOf(f.getFaltas()));
                            }

                            @Override
                            public void onFailure(@NonNull Call<FaltasList> call, @NonNull Throwable t) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void plotChart() {
        try {
            final ArrayList<BarEntry> yValues = new ArrayList<>();
            final LinkedList<String> streamsHelper = new LinkedList<>();
            yValues.clear();
            streamsHelper.clear();

            AttendanceService attendanceService = RetrofitClient.getClient().create(AttendanceService.class);
            Call<TurmasList> call = attendanceService.getTurmas(localStorage.getIdUser(), Integer.parseInt(edtAno.getText().toString()));
            call.enqueue(new Callback<TurmasList>() {
                @Override
                public void onResponse(@NonNull Call<TurmasList> call, @NonNull Response<TurmasList> response) {
                    assert response.body() != null;
                    final List<Turmas> turmas = response.body().getTurmas();

                    for (int i = 0; i < turmas.size(); i++) {
                        AttendanceService attendanceService = RetrofitClient.getClient().create(AttendanceService.class);
                        Call<FaltasList> call2 = attendanceService.getFaltas(turmas.get(i).getId_alocacao(), turmas.get(i).getId_inscricao());
                        final int index = i;
                        call2.enqueue(new Callback<FaltasList>() {
                            @Override
                            public void onResponse(@NonNull Call<FaltasList> call, @NonNull Response<FaltasList> response) {
                                assert response.body() != null;
                                DoughnutChartFaltas f = response.body().getFaltas().get(0);

                                String label = turmas.get(index).getDisciplina_sigla() + " " + turmas.get(index).getRegime_sigla();
                                float entryPosition = Float.parseFloat(index + "f");
                                yValues.add(new BarEntry(entryPosition, f.getFaltas()));
                                streamsHelper.add(label);

                                try {
                                    if (turmas.size() - 1 == index) {

                                        String[] streams = new String[turmas.size()];
                                        for (int i = 0; i < streamsHelper.size(); i++) {
                                            streams[i] = streamsHelper.get(i);
                                        }

                                        Log.e("STREAMHELPERS", Arrays.toString(streams));
                                        Log.e("YVALUES", yValues.toString());

                                        BarDataSet barDataSet = new BarDataSet(yValues, "");
                                        barDataSet.setColors(colors);

                                        XAxis xAxis = barChart.getXAxis();
                                        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

                                        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(streams);
                                        xAxis.setValueFormatter(formatter);
                                        xAxis.setLabelRotationAngle(-90f);

                                        Legend legend = barChart.getLegend();
                                        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
                                        legend.setForm(Legend.LegendForm.SQUARE);
                                        legend.setXEntrySpace(2f);

                                        BarData data = new BarData(barDataSet);
                                        barChart.setData(data);
                                        Description description = new Description();
                                        description.setText("");
                                        barChart.setDescription(description);
                                        barChart.setFitBars(true);
                                        barChart.animateXY(1000, 1000);
                                        barChart.invalidate();
                                    }
                                } catch (NullPointerException e) {
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<FaltasList> call, @NonNull Throwable t) {

                            }
                        });
                    }
                }

                @Override
                public void onFailure(@NonNull Call<TurmasList> call, @NonNull Throwable t) {

                }
            });
        } catch (IndexOutOfBoundsException | NullPointerException e) {
        }
    }
}

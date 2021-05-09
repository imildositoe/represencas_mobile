package com.example.represencas_mobile.remote;

import com.example.represencas_mobile.model.fake_model.Estudantes;
import com.example.represencas_mobile.model.list_model.FaltasList;
import com.example.represencas_mobile.model.list_model.NotificacaoList;
import com.example.represencas_mobile.model.list_model.TurmasList;
import com.example.represencas_mobile.model.list_model.EstudantesList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AttendanceService {

    @GET("api/get_all_estudantes/")
    Call<EstudantesList> getEstudantes();

    @GET("api/get_all_estudantes/")
    Call<EstudantesList> authUser();

    @GET("api/get_turmas_estudante")
    Call<TurmasList> getTurmas(@Query("id_estudante") int id_estudante, @Query("ano") int ano);

    @GET("api/doughnut_chart_faltas")
    Call<FaltasList> getFaltas(@Query("id_alocacao") int id_alocacao, @Query("id_inscricao") int id_inscricao);

    @GET("api/get_notificacoes")
    Call<NotificacaoList> getNotificacoes(@Query("id_estudante") int id_estudante);


    @POST("users/")
    Call<Estudantes> addUser(@Body Estudantes user);

    @PUT("users/{id}")
    Call<Estudantes> updateUser(@Path("id") int id, @Body Estudantes user);

    @DELETE("users/{id}")
    Call<Estudantes> deleteuser(@Path("id") int id);

}

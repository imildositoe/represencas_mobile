package com.example.represencas_mobile.model.list_model;

import com.example.represencas_mobile.model.Notificacao;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificacaoList {
    @SerializedName("notificacoes")
    private List<Notificacao> notificacoes;

    public NotificacaoList() {
    }

    public NotificacaoList(List<Notificacao> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<Notificacao> notificacoes) {
        this.notificacoes = notificacoes;
    }

    @Override
    public String toString() {
        return "NotificacaoList{" +
                "notificacoes=" + notificacoes +
                '}';
    }
}

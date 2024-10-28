package uts.fernanda.tasks;


import android.content.Context;

import java.util.List;

import uts.fernanda.tasks.model.Agenda;

public class AgendaRepository {
    private agendaDao agendaDAO;

    public AgendaRepository(Context context) {
        agendaDAO = new agendaDao(context);
    }

    public long addAgenda(Agenda agenda) {
        return agendaDAO.addAgenda(agenda);
    }

    public List<Agenda> getAllAgendas() {
        return agendaDAO.getAllAgendas();
    }

    public void deleteAgenda(int id) {
        agendaDAO.deleteAgenda(id);
    }
}


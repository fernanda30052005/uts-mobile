package uts.fernanda.tasks;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

import uts.fernanda.tasks.adapter.AgendaAdapter;
import uts.fernanda.tasks.model.Agenda;

public class MainActivity extends AppCompatActivity {
    private AgendaRepository agendaRepository;
    private AgendaAdapter agendaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agendaRepository = new AgendaRepository(this);
        RecyclerView recyclerView = findViewById(R.id.agendaRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        agendaAdapter = new AgendaAdapter(agendaRepository.getAllAgendas());
        recyclerView.setAdapter(agendaAdapter);

        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AddAgendaActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Agenda> agendas = agendaRepository.getAllAgendas();
        agendaAdapter.updateData(agendas);
    }
}

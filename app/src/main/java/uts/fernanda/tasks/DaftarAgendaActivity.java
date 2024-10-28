package uts.fernanda.tasks;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

import uts.fernanda.tasks.adapter.AgendaAdapter;
import uts.fernanda.tasks.model.Agenda;

public class DaftarAgendaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AgendaAdapter adapter;
    private List<Agenda> agendaList;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_agenda);

        // Initialize the RecyclerView and Agenda list
        recyclerView = findViewById(R.id.agendaRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the agenda list with sample data
        agendaList = new ArrayList<>(); // Ensure agendaList is not null
        agendaList.add(new Agenda("Meeting with team", "Discuss project details", "Pending"));
        agendaList.add(new Agenda("Project deadline", "Complete the UI design", "Ongoing"));
        agendaList.add(new Agenda("Client call", "Monthly update call with the client", "Completed"));

        // Set up the adapter
        adapter = new AgendaAdapter(agendaList);
        recyclerView.setAdapter(adapter);

        // Initialize Floating Action Button
        fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Action when FAB is clicked
                agendaList.add(new Agenda("New Task", "Description of new task", "Pending"));
                adapter.notifyDataSetChanged();
            }
        });
    }
}


package uts.fernanda.tasks;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import uts.fernanda.tasks.model.Agenda;

public class AddAgendaActivity extends AppCompatActivity {
    private EditText editTextName, editTextDescription;
    private Spinner spinnerStatus;
    private AgendaRepository agendaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_agenda);

        // Initialize AgendaRepository
        agendaRepository = new AgendaRepository(this);

        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextDescription = findViewById(R.id.editTextDescription);
        spinnerStatus = findViewById(R.id.spinnerStatus);
        Button buttonSave = findViewById(R.id.buttonSave);

        // Set up the spinner with options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.status_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapter);

        // Set button click listener
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Agenda agenda = new Agenda(
                        editTextName.getText().toString(),
                        editTextDescription.getText().toString(),
                        spinnerStatus.getSelectedItem().toString() // Get selected status from Spinner
                );

                // Add agenda to database
                agendaRepository.addAgenda(agenda);

                // Close activity
                finish();
            }
        });
    }
}
package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.example.myapplication.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.myapplication.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.myapplication.EXTRA_DESCRIPTION";
    public static final String EXTRA_AMOUNT = "com.example.myapplication.EXTRA_AMOUNT";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private EditText editTextAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextAmount = findViewById(R.id.edit_text_amount);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID))
        {
            setTitle("Edit Expenses");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            editTextAmount.setText(intent.getStringExtra(EXTRA_AMOUNT));
        }
        else
        {
            setTitle("Add Expenses");
        }
    }

    private void saveNote()
    {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String amount1 = editTextAmount.getText().toString();
        int amount = Integer.parseInt(editTextAmount.getText().toString());

        if(title.trim().isEmpty() || description.trim().isEmpty() || amount1.trim().isEmpty())
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_AMOUNT, amount);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1)
        {
            data.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK, data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

package com.example.qaydlar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTitle, editContent;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTitle = findViewById(R.id.editNoteTitle);
        editContent = findViewById(R.id.editNoteContent);
        btnSave = findViewById(R.id.btnSaveNote);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString().trim();
                String content = editContent.getText().toString().trim();

                if (title.isEmpty() || content.isEmpty()) {
                    Toast.makeText(AddNoteActivity.this, "Iltimos, barcha maydonlarni to'ldiring", Toast.LENGTH_SHORT).show();
                } else {
                    // Ma'lumotlarni MainActivity'ga qaytarish
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("note_title", title);
                    resultIntent.putExtra("note_content", content);
                    setResult(RESULT_OK, resultIntent);
                    finish(); // Oynani yopish
                }
            }
        });

    }
}
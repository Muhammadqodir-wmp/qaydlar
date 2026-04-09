package com.example.qaydlar;

import android.content.Intent; // MUHIM: Intent uchun import
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable; // MUHIM: Nullable uchun import
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private List<Note> noteList;
    private FloatingActionButton fabAddNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. UI elementlarini bog'lash
        recyclerView = findViewById(R.id.recyclerViewNotes);
        fabAddNote = findViewById(R.id.fabAddNote);

        // 2. Ma'lumotlar ro'yxatini tayyorlash
        noteList = new ArrayList<>();
        loadDummyData();

        // 3. RecyclerView va Adapterni sozlash
        adapter = new NoteAdapter(noteList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // 4. FAB tugmasi bosilganda AddNoteActivity'ni ochish
        fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    // Ma'lumotlarni yuklash metodi
    private void loadDummyData() {
        noteList.add(new Note("Loyiha rejasi", "Laravel'da yangi nazorat panelini tugatish kerak."));
        noteList.add(new Note("Bozor uchun xaridlar", "Led lampalar va qurilish anjomlari qoldiqlarini hisoblash."));
        noteList.add(new Note("Ubuntu sozlamalari", "Serverni Ubuntu 24.04 LTS versiyasiga o'tkazish muvaffaqiyatli yakunlandi."));
    }

    // Boshqa oynadan qaytgan ma'lumotni qabul qilish (onCreate'dan tashqarida)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra("note_title");
            String content = data.getStringExtra("note_content");

            // Ro'yxat boshiga yangi qaydni qo'shish
            noteList.add(0, new Note(title, content));

            // Faqat o'zgargan joyni yangilash (samaradorlik uchun)
            adapter.notifyItemInserted(0);
            recyclerView.scrollToPosition(0);

            Toast.makeText(this, "Qayd saqlandi", Toast.LENGTH_SHORT).show();
        }
    }
}
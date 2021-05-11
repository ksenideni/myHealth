package com.example.myhealth.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhealth.AdapterNote;
import com.example.myhealth.Note;
import com.example.myhealth.R;
import com.example.myhealth.customization.VerticalSpacingItemDecorator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class SettingsFragment extends Fragment implements
        AdapterNote.OnNoteListener,
        View.OnClickListener {


    private static final String TAG = "NotesListFragment";
//    private FloatingActionButton add;
//    private Dialog dialog;
//    private AppDataBase appDataBase;
    private RecyclerView recyclerView;
    private AdapterNote adapterNote;
    private ArrayList<Note> allNotes=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        recyclerView=v.findViewById(R.id.rv_notes);

        v.findViewById(R.id.fab).setOnClickListener(this);


        initRecyclerView();

//        appDataBase = AppDataBase.geAppdatabase(getActivity());

//insertFakeNotes для примера
        for(int i = 0; i < 1000; i++){
            Note note = new Note();
            note.setTitle("title #" + i);
            note.setContent("content #: " + i);
            note.setTimestamp("May 2021");
            allNotes.add(note);
        }
        adapterNote.notifyDataSetChanged();





        return v;
    }

    private void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        recyclerView.addItemDecoration(itemDecorator);
        //свайпер
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);
        adapterNote=new AdapterNote(allNotes, this);
        recyclerView.setAdapter(adapterNote);
    }

    //done: переход на фрагмент с просмотром информации
    //NoteFragment должен получить позицию
    //в активностях: bundle и note д. б. parcelable
    @Override
    public void onNoteClick(int position) {
        //allNotes.get(position);
        NoteFragment noteFragment=new NoteFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("selected_note", allNotes.get(position));

        noteFragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, noteFragment).addToBackStack(null).commit();
        Log.d(TAG, "onNoteClick: " + position);
    }

    @Override
    public void onClick(View v) {
        NoteFragment noteFragment=new NoteFragment();
        Bundle bundle = new Bundle();
        noteFragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, noteFragment).addToBackStack(null).commit();
    }

    //удаление заметки
    private void deleteNode(Note note){
        allNotes.remove(note);
        adapterNote.notifyDataSetChanged();
    }


    //удаление по свайпу
    private ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT| ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            deleteNode(allNotes.get(viewHolder.getAdapterPosition()));
        }
    };
}
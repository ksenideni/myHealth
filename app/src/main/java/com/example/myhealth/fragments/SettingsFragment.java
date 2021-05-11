package com.example.myhealth.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

import java.util.ArrayList;


public class SettingsFragment extends Fragment implements AdapterNote.OnNoteListener {


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
        initRecyclerView();

//        appDataBase = AppDataBase.geAppdatabase(getActivity());
//insertFakeNotes
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
        adapterNote=new AdapterNote(allNotes, this);
        recyclerView.setAdapter(adapterNote);
    }

    //todo: переход на фрагмент с просмотром информации
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
}
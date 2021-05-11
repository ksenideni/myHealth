package com.example.myhealth.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myhealth.Note;
import com.example.myhealth.R;
import com.example.myhealth.customization.LinedEditText;


public class NoteFragment extends Fragment {

    private static final String TAG = "NoteFragment";

    // UI components
    private LinedEditText mLinedEditText;
    private EditText mEditTitle;
    private TextView mViewTitle;
//    private RelativeLayout mCheckContainer, mBackArrowContainer;
//    private ImageButton mCheck, mBackArrow;

    // vars
    private boolean mIsNewNote;
    private Note mNoteInitial;
//    private GestureDetector mGestureDetector;
//    private int mMode;
//    private NoteRepository mNoteRepository;
//    private Note mNoteFinal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_note, container, false);
        Bundle bundle=getArguments();
        if(bundle==null){
            throw new IllegalArgumentException("Note should not be null");
        }
        mNoteInitial = bundle.getParcelable("selected_note");
        mLinedEditText =v.findViewById(R.id.note_text);
        mEditTitle = v.findViewById(R.id.note_edit_title);
        mViewTitle = v.findViewById(R.id.note_text_title);
        mViewTitle.setText(mNoteInitial.getTitle());
        mEditTitle.setText(mNoteInitial.getTitle());
        mLinedEditText.setText(mNoteInitial.getContent());
        return v;
    }

    private boolean getIncomingBundle(){
        Bundle bundle=getArguments();
        if(bundle!=null){
            mNoteInitial =bundle.getParcelable("selected_note");
            Log.d(TAG, "getIncomingBundle:"+mNoteInitial.toString());
            mIsNewNote = false;
            return false;
        }
        mIsNewNote=true;
        return true;
    }

    private void setNoteProperties(){
        mViewTitle.setText(mNoteInitial.getTitle());
        mEditTitle.setText(mNoteInitial.getTitle());
        mLinedEditText.setText(mNoteInitial.getContent());
    }

    //если заметка новая, кладем дефолтное значение
    //frdjilghreghlrehgre
    private void setNewNoteProperties(){
        mViewTitle.setText("Note Title");
        mEditTitle.setText("Note Title");
    }
}
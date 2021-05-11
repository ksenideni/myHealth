package com.example.myhealth.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myhealth.Note;
import com.example.myhealth.R;
import com.example.myhealth.customization.LinedEditText;


public class NoteFragment extends Fragment implements
        View.OnTouchListener,
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener,
        View.OnClickListener
{

    private static final String TAG = "NoteFragment";
    //константы состояния редактирования (вкл/выкл)
    private static final int EDIT_MODE_ENABLED=1;
    private static final int EDIT_MODE_DISABLED=0;

    // UI components
    private LinedEditText mLinedEditText;
    private EditText mEditTitle;
    private TextView mViewTitle;
    private RelativeLayout mCheckContainer, mBackArrowContainer;
    private ImageButton mCheck, mBackArrow;

    // vars
    private boolean mIsNewNote;
    private Note mNoteInitial;
    //'обнаруживатель' двойного касания
    private GestureDetector mGestureDetector;
    private int mMode;
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
        mCheckContainer=v.findViewById(R.id.check_container);
        mBackArrowContainer=v.findViewById(R.id.back_arrow_container);
        mCheck=v.findViewById(R.id.toolbar_check);
        mBackArrow=v.findViewById(R.id.toolbar_back_arrow);
        if(getIncomingBundle()){
            //new note (edit mode)
            setNewNoteProperties();
            enabledEditMode();
        }
        else{
            // not new note (view mode)
            setNoteProperties();
        }
        setListeners();

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
            mMode = EDIT_MODE_DISABLED;
            mIsNewNote = false;
            return false;
        }
        mMode=EDIT_MODE_ENABLED;
        mIsNewNote=true;
        return true;
    }

    //вкл режим ввода
    private void enabledEditMode(){
        //если вводим текст, выкл кнопку назад и вкл кнопку сохранить
        mBackArrowContainer.setVisibility(View.GONE);
        mCheckContainer.setVisibility(View.VISIBLE);

        mViewTitle.setVisibility(View.GONE);
        mEditTitle.setVisibility(View.VISIBLE);

        mMode=EDIT_MODE_ENABLED;
    }

    private void disableEditMode(){
        mBackArrowContainer.setVisibility(View.VISIBLE);
        mCheckContainer.setVisibility(View.GONE);

        mViewTitle.setVisibility(View.VISIBLE);
        mEditTitle.setVisibility(View.GONE);

        mMode=EDIT_MODE_DISABLED;
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

    private void setListeners(){
        mLinedEditText.setOnTouchListener(this);
        //не уверена в аргументах context и listener
        mGestureDetector = new GestureDetector(getActivity(), this);
        mViewTitle.setOnClickListener(this);
        mCheck.setOnClickListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d(TAG, "onDoubleTap: double tapped.");
        enabledEditMode();
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //если нажать на галочку
            case R.id.toolbar_check:{
                disableEditMode();
                break;
            }
            case R.id.note_text_title:{
                //если нажать на заголовок
                enabledEditMode();
                //поставить курсор в конце
                mEditTitle.requestFocus();
                mEditTitle.setSelection(mEditTitle.length());
                break;
            }
        }

        //todo: не реализую пока т.к. и так робит вроде
        //по кнопке назад телефона вернуться на ресайклер заметок
        //onBackPressed() - override метод активности
    }
}
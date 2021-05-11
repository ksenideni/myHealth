package com.example.myhealth.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
//        if(bundle!=null) {
//            mNoteInitial = bundle.getParcelable("selected_note");
//        }
        mLinedEditText =v.findViewById(R.id.note_text);
        mEditTitle = v.findViewById(R.id.note_edit_title);
        mViewTitle = v.findViewById(R.id.note_text_title);
        mCheckContainer=v.findViewById(R.id.check_container);
        mBackArrowContainer=v.findViewById(R.id.back_arrow_container);
        mCheck=v.findViewById(R.id.toolbar_check);
        mBackArrow=v.findViewById(R.id.toolbar_back_arrow);

        setListeners();


        if(getIncomingBundle()){
            //new note (edit mode)
            setNewNoteProperties();
            enabledEditMode();
        }
        else{
            // not new note (view mode)
            setNoteProperties();
            disableContentInteraction();
        }

        return v;
    }

    private boolean getIncomingBundle(){
        Bundle bundle=getArguments();
        if(bundle.getParcelable("selected_note")!=null){
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

    //отключим действие по одному клику
    private void disableContentInteraction(){
        mLinedEditText.setKeyListener(null);
        mLinedEditText.setFocusable(false);
        mLinedEditText.setFocusableInTouchMode(false);
        mLinedEditText.setCursorVisible(false);
        mLinedEditText.clearFocus();
    }

    private void enableContentInteraction(){
        mLinedEditText.setKeyListener(new EditText(getActivity()).getKeyListener());
        mLinedEditText.setFocusable(true);
        mLinedEditText.setFocusableInTouchMode(true);
        mLinedEditText.setCursorVisible(true);
        mLinedEditText.requestFocus();
    }
    //вкл режим ввода
    private void enabledEditMode(){
        //если вводим текст, выкл кнопку назад и вкл кнопку сохранить
        mBackArrowContainer.setVisibility(View.GONE);
        mCheckContainer.setVisibility(View.VISIBLE);

        mViewTitle.setVisibility(View.GONE);
        mEditTitle.setVisibility(View.VISIBLE);

        mMode=EDIT_MODE_ENABLED;

        enableContentInteraction();
    }

    private void disableEditMode(){
        mBackArrowContainer.setVisibility(View.VISIBLE);
        mCheckContainer.setVisibility(View.GONE);

        mViewTitle.setVisibility(View.VISIBLE);
        mEditTitle.setVisibility(View.GONE);

        mMode=EDIT_MODE_DISABLED;

        disableContentInteraction();

    }

    //скрыть клавиатуру (при нажатии галочки)
    private void hideSoftKeyboard(){
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getActivity().getCurrentFocus();
        if(view==null){
            view=new View(getActivity());
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void setNoteProperties(){
        mViewTitle.setText(mNoteInitial.getTitle());
        mEditTitle.setText(mNoteInitial.getTitle());
        mLinedEditText.setText(mNoteInitial.getContent());
    }

    //если заметка новая, кладем дефолтное значение в титульник
    private void setNewNoteProperties(){
        mViewTitle.setText("Название");
        mEditTitle.setText("Название");
    }

    private void setListeners(){
        mLinedEditText.setOnTouchListener(this);
        //не уверена в аргументах context и listener
        mGestureDetector = new GestureDetector(getActivity(), this);
        mViewTitle.setOnClickListener(this);
        mCheck.setOnClickListener(this);
        mBackArrow.setOnClickListener(this);
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
                hideSoftKeyboard();
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
            case R.id.toolbar_back_arrow:{
                //todo: правильно (?) описать кнопку 'назад'
                SettingsFragment settingsFragment=new SettingsFragment();
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, settingsFragment).commit();
                break;
            }
        }

        //todo: не реализую пока т.к. и так робит вроде (работает из-за того что я добавляла фрагмент в BackStack )
        //по кнопке назад телефона вернуться на ресайклер заметок
        //onBackPressed() - override метод активности
    }
}
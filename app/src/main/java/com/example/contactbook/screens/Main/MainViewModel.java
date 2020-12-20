package com.example.contactbook.screens.Main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.contactbook.App;
import com.example.contactbook.model.Note;

import java.util.List;

public class MainViewModel extends ViewModel {

    private LiveData<List<Note>> noteLiveData = App.getInstance().getNoteDao().getAllLiveData();

    public LiveData<List<Note>> getNoteLiveData() {
        return noteLiveData;
    }
}

package com.example.classnotes;

public class NoteModal {

    // variables for our notes,
    // description, title, id.
    private String title;
    private String note;
    private String date;
    private int id;

    // creating getter and setter methods
    public String getTitle() {
        return title;
    }

    public void setTitle(String courseName) {
        this.title = courseName;
    }

    public String getNoteDetails() {
        return note;
    }

    public void setNoteDetails(String courseDuration) {
        this.note = courseDuration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // constructor
    public NoteModal(String noteTitle, String noteDetails, String date) {
        this.title = noteTitle;
        this.note = noteDetails;
        this.date = date;
    }
}

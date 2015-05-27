package main;

import model.HateNote;

import java.util.List;

/**
 * Data Transfer Object for note requests
 * Created by escobeitor on 27/05/15.
 */
public class NoteListDto {

    private int currentPage = 0;

    private int totalResults = 0;

    private List<HateNote> notes;

    public NoteListDto(int currentPage, int totalResults, List<HateNote> notes) {
        this.currentPage = currentPage;
        this.totalResults = totalResults;
        this.notes = notes;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<HateNote> getNotes() {
        return notes;
    }

    public void setNotes(List<HateNote> notes) {
        this.notes = notes;
    }
}

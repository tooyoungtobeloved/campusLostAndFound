package user.entity;

import java.util.Date;

public class Note {
	private int noteId;
	private String noteTitle;
	private String noteContent;
	private Date notePubDate;
	private int isTop;
	
	public Note() {}

	public Note(int noteId, String noteTitle, String noteContent,Date notePubDate) {
		super();
		this.noteId = noteId;
		this.noteTitle = noteTitle;
		this.noteContent = noteContent;
		this.notePubDate = notePubDate;
	}

	
	public Note(int noteId, String noteTitle, String noteContent, Date notePubDate, int isTop) {
		super();
		this.noteId = noteId;
		this.noteTitle = noteTitle;
		this.noteContent = noteContent;
		this.notePubDate = notePubDate;
		this.isTop = isTop;
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public Date getNotePubDate() {
		return notePubDate;
	}

	public void setNotePubDate(Date notePubDate) {
		this.notePubDate = notePubDate;
	}

	public int getIsTop() {
		return isTop;
	}

	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}
	
	
}

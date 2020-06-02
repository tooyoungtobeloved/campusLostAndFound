package admin.entity;

import java.util.Date;

public class Notice {
	private String noteId;
	private String noteTitle;
	private String noteContent;
	private Date notePubDate;
	private int isTop;

	public Notice() {
	}

	public Notice(String noteId, String noteTitle, String noteContent, Date notePubDate, int isTop) {
		this.noteId = noteId;
		this.noteTitle = noteTitle;
		this.noteContent = noteContent;
		this.notePubDate = notePubDate;
		this.isTop = isTop;
	}

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
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

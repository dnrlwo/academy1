package project_palace_guide;

import java.util.Vector;

public class BrdVo {
	String id;
	String title;
	String mcontent;
	String rdate;
	String picture; // 사진은 1개만
	Vector<String> mcomment; // 댓글
	int favorite;
	int views;
	int serial;
	String path;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return mcontent;
	}

	public void setContent(String content) {
		this.mcontent = content;
	}

	public String getDate() {
		return rdate;
	}

	public void setDate(String date) {
		this.rdate = date;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Vector<String> getComment() {
		return mcomment;
	}

	public void setComment(Vector<String> comment) {
		this.mcomment = new Vector<String>();
		if (comment.size() > 0) {
			for (String f : comment) {
				this.mcomment.add(f);
			}
		}

	}

	public int getLike() {
		return favorite;
	}

	public void setLike(int like) {
		this.favorite = like;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

}

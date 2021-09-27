package exam.model;

import java.sql.Timestamp;

public class BoardDto {
	private int num;
	private int rnum;
	private String writer;
	private String pass;
	private String title;
	private String content;
	private int readcount;
	private int ref;
	private int step;
	private int depth;
	private Timestamp regdate;
	private String file;
	
	public BoardDto() { }
	public BoardDto(int num, String writer, String pass, String title, String content, int readcount, int ref, int step, int depth,
			Timestamp regdate) {
		this.num = num;
		this.writer = writer;
		this.pass = pass;
		this.title = title;
		this.content = content;
		this.readcount = readcount;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.regdate = regdate;
	}
	
	public BoardDto(int num, String writer,  String pass, String title, String content, int readcount, int ref, int step, int depth,
			Timestamp regdate,String file) {
		this.num = num;
		this.writer = writer;
		this.pass = pass;
		this.title = title;
		this.content = content;
		this.readcount = readcount;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.regdate = regdate;
		this.file = file;
	}

	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
}

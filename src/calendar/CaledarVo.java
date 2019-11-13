package calendar;

import java.util.ArrayList;
import java.util.List;

public class CaledarVo {
	
	int month_c;
	int year_c;
	
	List<Integer> week_1 = new ArrayList<Integer>();
	List<Integer> week_2 = new ArrayList<Integer>();
	List<Integer> week_3 = new ArrayList<Integer>();
	List<Integer> week_4 = new ArrayList<Integer>();
	List<Integer> week_5 = new ArrayList<Integer>();
	
	public int getMonth_c() {
		return month_c;
	}
	public void setMonth_c(int month_c) {
		this.month_c = month_c;
	}
	public int getYear_c() {
		return year_c;
	}
	public void setYear_c(int year_c) {
		this.year_c = year_c;
	}
	public List<Integer> getWeek_1() {
		return week_1;
	}
	public void setWeek_1(List<Integer> week_1) {
		this.week_1 = week_1;
	}
	public List<Integer> getWeek_2() {
		return week_2;
	}
	public void setWeek_2(List<Integer> week_2) {
		this.week_2 = week_2;
	}
	public List<Integer> getWeek_3() {
		return week_3;
	}
	public void setWeek_3(List<Integer> week_3) {
		this.week_3 = week_3;
	}
	public List<Integer> getWeek_4() {
		return week_4;
	}
	public void setWeek_4(List<Integer> week_4) {
		this.week_4 = week_4;
	}
	public List<Integer> getWeek_5() {
		return week_5;
	}
	public void setWeek_5(List<Integer> week_5) {
		this.week_5 = week_5;
	}
	
	
}

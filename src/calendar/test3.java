package calendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class test3 {
	public test3() {
		
	}

	public CaledarVo testt(int y, int m) {
		CaledarVo vo = new CaledarVo();

		int[] month = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		int w, total;

		if (y % 4 == 0 && y % 100 != 0 || y % 400 == 0)
			month[1] = 29; // 윤년
		else
			month[1] = 28; // 평년

		total = (y - 1) * 365 + (y - 1) / 4 - (y - 1) / 100 + (y - 1) / 400;
		for (int i = 0; i < m - 1; i++)
			total += month[i];

		total += 1;

		w = total % 7;

		List<Integer> first_week = new ArrayList<Integer>();
		List<Integer> second_week = new ArrayList<Integer>();
		List<Integer> third_week = new ArrayList<Integer>();
		List<Integer> fourth_week = new ArrayList<Integer>();
		List<Integer> fifth_week = new ArrayList<Integer>();
		
		for (int i = 0; i < w; i++) {
			first_week.add(i, null);
		}

		int date = 1;
		int start = w;
		int k;
		int c = month[m - 1];
		
		for (k = 0; k < (7 - w); k++) {
			first_week.add(start, date);
			date++;
			start++;
		}
		
		for (k = 0; k < 7; k++) {
			second_week.add(k, date);
			date++;
		}
		
		for (k = 0; k < 7; k++) {
			third_week.add(k, date);
			date++;
		}
		
		for (k = 0; k < 7; k++) {
			fourth_week.add(k, date);
			date++;
		}
		
		if (date == c) {
			for (int i = 0; i < 7; i++) {
				fifth_week.add(i, null);
			}
		} else {
			int last = c - date;
			for (k = 0; k < last; k++) {
				fifth_week.add(k, date);
				date++;
			}
		}
		vo.setMonth_c(m);
		vo.setYear_c(y);
		vo.setWeek_1(first_week);
		vo.setWeek_2(second_week);
		vo.setWeek_3(third_week);
		vo.setWeek_4(fourth_week);
		vo.setWeek_5(fifth_week);
		return vo;
	}
}
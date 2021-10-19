package com.core;

import static java.lang.Math.*;

public class Pagination2 {
	private int startNo; // 페이지 구간 시작 번호
	private int lastNo; // 페이지 구간 마지막 번호
	
	public Pagination2(int page, int total) {
		/** 
		 * 페이지 구간 번호
		 * (구간별 페이지 개수)
		 */
		page = (page <= 0)?1:page;
		int pageLinks = 5; // 구간별 페이지 개수
		int num = (int)floor((page - 1) / pageLinks); // 페이지 구간 번호 
		// System.out.printf("num = %d", num);
		
		/**
		 * 구간별 시작 페이지 번호 
		 *  구간별 종료 페이지 번호
		 */
		startNo = num * pageLinks + 1;
		lastNo = startNo + pageLinks - 1;
		
		
		/**
		 * lastNo <= 마지막 페이지
		 * 1) 마지막페이지(1페이지당 레코드 갯수)
		 * 2) 구간별 종료 번호가 마지막페이지 보다 큰지 체크 -> 크면  = 마지막 페이지
		 */
		int limit = 15;
		int lastPageNo = (int)ceil(total / (double)limit);
		if (lastNo > lastPageNo) {
			lastNo = lastPageNo;
		}
		
	}
	
	public String getPageHtml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ul class='pagination'>");
		for(int i = startNo; i <= lastNo; i++) {
			sb.append("<li>");
			sb.append(i);
			sb.append("</li>");
		}
		sb.append("</ul>");
		return sb.toString();
	}
}
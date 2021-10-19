package com.core;

import javax.servlet.http.HttpServletRequest;

public class Pagination3 {
	
	private int num; // 페이지 구간 번호(현재 페이지)
	private int lastNum; // 마지막 페이지 구간번호
	private int lastPageNo; // 마지막 페이지 번호
	private int startNo; // 구간별 시작 번호(0번째 구간  - 1페이지)
	private int lastNo; // 구간별 종료 번호(0번째 구간 - 5페이지)
	private int prevNo; // 이전 구간 시작 번호
	private int nextNo; // 다음 구간 시작 번호
	private int page; // 현재 페이지 번호
	
	/**
	 * 생성자 - 페이지 네이션 구성을 위한 각각의 요소를 구하는 
	 *   1. 페이지 구간번호 
	 *   	버림((현재 페이지 번호 - 1 ) / 구간별 페이지 표기 갯수)
	 *   
	 *   2. 페이지 구간에서 시작 페이지 번호, 종료 페이지 번호
	 *   	시작 페이지 번호 = (페이지 구간번호 * 구간별 페이지 표기 갯수 + 1)
	 *   	종료 페이지 번호 = (시작페이지 번호 + 구간별 페이지 표기 갯수 -1)
	 *   
	 *  3. 다음 시작 페이지 번호, 이전 시작 페이지 번호
	 *  	다음 시작 페이지 - (현재 페이지 구간번호 + 1) * 구간별 페이지 표기 갯수 + 1
	 *  						   - 마지막 구간 X
	 *  							(마지막 페이지번호의 구간 ) 
	 *  							마지막 페이지 구간 = 버림((마지막 페이지 - 1) / 구간별 페이지 갯수)
	 *  							마지막 페이지번호 = 올림(전체 레코드 / 1페이지당 레코드수)
	 *  						 - 마지막 구간 끝나는 페이지번호 = 마지막 페이지 번호							
	 *  
	 *  	이전 시작 페이지 - (현재 페이지 구간번호 - 1) * 구간별 페이지 표기 갯수 + 1 
	 *								- 처음 구간 X 								
	 *   							- 페이지 구간번호 - 0(첫 구간) 제외
	 *   
	 *   4. 첫 페이지로 이동(1페이지)
	 *   	-첫 페이지 구간만 빼고 다 노출(페이지 구간번호가 1이상(2번째 구간))
	 *   
	 *   5. 마지막 페이지 이동
	 *   	- 마지막 구간만 빼고 다 노출
	 *   
	 * @param page - 현재 페이지 번호
	 * @param total - 전체 레코드 개수
	 * @param pageLinks - 구간별 페이지 표기 갯수(1 2 3 4 5 | 6 7 8 9 10 ..)
	 * @param limit - 1페이지 당 레코드 개수
	 */
	public Pagination3(int page, int total, int pageLinks, int limit) {
		page = (page <= 0)?1:page;
		pageLinks = (pageLinks <= 0)?10:pageLinks;
		limit = (limit <= 0)?15:limit;
		
		this.page = page;
		
		/**
			1. 페이지 구간번호 
		    	버림((현재 페이지 번호 - 1 ) / 구간별 페이지 표기 갯수)
		    	Math.floor 버림 / Math.ceil 올림 / Math.round 반올림
		*/
		num = (int)Math.floor((page - 1) / pageLinks);
		
		/**
		 * lastNum; // 마지막 페이지 구간번호 
		 * lastPageNo; // 마지막 페이지 번호
		 */
		lastPageNo =  (int)Math.ceil(total / (double)limit); 
		lastNum = (int)Math.floor((lastPageNo - 1) / pageLinks);
		
		/**
		*   2. 페이지 구간에서 시작 페이지 번호, 종료 페이지 번호
		*   	시작 페이지 번호 = (페이지 구간번호 * 구간별 페이지 표기 갯수 + 1)
		*   	종료 페이지 번호 = (시작페이지 번호 + 구간별 페이지 표기 갯수 -1)
		*/
		startNo = num * pageLinks + 1; 
		lastNo =  startNo + pageLinks - 1;
		
		// 마지막 페이지 구간 끝나는 페이지는 = 마지막 페이지 번호
		if (lastNo > lastPageNo) { 
			lastNo = lastPageNo;
		}
		
		/**
		 * 다음 시작 페이지 번호, 이전 시작 페이지 번호
		 */
		if (num > 0) {
			prevNo = (num - 1) * pageLinks + 1;
		}
		
		if (num < lastNum) {
			nextNo = (num + 1) * pageLinks + 1;
		}
	}
	
	public Pagination3(HttpServletRequest request,int total) {
		this(
			request.getParameter("page"),
			total,
			10,
			15
			);
	}
	
	public Pagination3(String page, int total, int pageLinks, int limit) {
		this(
			(page == null)?1:Integer.parseInt(page),
			total,
			pageLinks,
			limit
			);		
	}
	
	public String getPageHtml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ul class='pagination'>");
		
		// 첫 페이지, 이전 페이지 
		if (num > 0) { // 구간이 첫 구간이 아닌 경우만 노출 
			sb.append("<li class='page first'><a href='?page=1'>First</a></li>");
			
			sb.append("<li class='page prev'><a href='?page=");
			sb.append(prevNo);
			sb.append("'>Prev</a></li>");
		}
		
		for (int i = startNo; i <= lastNo; i++) {
			sb.append("<li class='page");
			if (i == page) {
				sb.append(" on");
			}
			sb.append("'>");
			sb.append("<a href='?page=");
			sb.append(i);
			sb.append("'>");
			sb.append(i);
			sb.append("</a>");
			sb.append("</li>");
		}
		
		// 다음페이지 이동, 마지막 페이지 이동
		if (num < lastNum) {
			
			sb.append("<li class='page next'><a href='?page=");
			sb.append(nextNo);
			sb.append("'>Next</a></li>");
			
			sb.append("<li class='page last'><a href='?page=");
			sb.append(lastPageNo);
			sb.append("'>Last</a></li>");
		}
		
		sb.append("</ul>");
		
		return sb.toString();
	}
}



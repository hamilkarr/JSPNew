package com.core;

import javax.servlet.http.HttpServletRequest;

public class PagingSelf {
	// private int page;
	private int blockNo;
	private int blockStartNo;
	private int blockLastNo;
	private int prevBlockNo;
	private int nextBlockNo;
	private int lastBlockNo;
	private int lastPageNo;
	
	public PagingSelf(int page, int total, int limit, int pageLinks) {
		// this.page = page;
		
		pageLinks = (pageLinks <= 0)?10:pageLinks;
		limit = (limit <=0)?15:limit;
			
		blockNo = (int) Math.floor((page-1)/pageLinks);
		blockStartNo = blockNo * pageLinks + 1;
		blockLastNo = blockStartNo + pageLinks - 1;
			
		lastPageNo = (int) Math.ceil(total / (double) limit);
		lastBlockNo = (int) Math.floor((lastPageNo-1)/pageLinks);
		
		if (blockLastNo >= lastPageNo) {
			blockLastNo = lastPageNo;
		}
		
		// 이전 구간 시작 번호
		if (blockNo > 0) {
		prevBlockNo = (blockNo - 1) * pageLinks + 1;
		}
		
		// 다음 구간 시작 번호
		if (blockNo < lastBlockNo) {
		nextBlockNo = (blockNo + 1) * pageLinks + 1;
		}
	}
	
	/*
	public PagingSelf(int page, int total, int pageLinks ) {
		this(page, total, pageLinks, 15);
	}
	*/
	
	public PagingSelf(int page, int total) {
		this(page,total,10,15);
	}
	
	public PagingSelf(String page, int total) {
		this((page == null)?1:Integer.parseInt(page),total);
	}
	
	public PagingSelf(HttpServletRequest request, int total) {
		this(request.getParameter("page"),total);
	}
	
	public String getPageHtml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ol class='pagination'>");
		
		// 첫 페이지
		if (blockNo > 0) {
			sb.append("<li class='page'><a href='?page=1'>first</a></li>");
		}
		
		// 이전 페이지 링크
		if (prevBlockNo > 0) {
		sb.append("<li class='page'><a href='?page=" + prevBlockNo + "'>");
		sb.append("prev");
		sb.append("</a></li>");
		}
		
		for (int i = blockStartNo; i <= blockLastNo; i++ ) {
		sb.append("<li class='page'>");
		sb.append(i);
		sb.append("</li>");
		}
		
		// 다음 페이지 링크
		if (nextBlockNo > 0) {
		sb.append("<li class='page'><a href='?page=" + nextBlockNo + "'>");
		sb.append("next");
		sb.append("</a></li>");
		}
		
		// 마지막 페이지
		if (blockNo < lastBlockNo) {
			sb.append("<li class='page'><a href='?page=" + lastPageNo + "'>");
			sb.append("last");
			sb.append("</a></li>");
		}
		
		sb.append("</ol>");		
		return sb.toString();
	}		
}

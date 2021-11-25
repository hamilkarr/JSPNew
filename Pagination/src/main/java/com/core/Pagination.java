package com.core;

import static java.lang.Math.*;
import javax.servlet.http.HttpServletRequest;

/**
 * 페이지네이션
 *
 */
public class Pagination {
	private int page; // 페이지 번호
	private int startNo; // 구간별 시작 번호
	private int lastNo; // 구간별 종료 번호
	private int num; // 구간 번호
	private int lastNum; // 마지막 구간 번호
	private int prevNo; // 이전 구간 시작 번호
	private int nextNo; // 다음 구간 시작 번호
	private int lastPageNo; // 마지막 페이지 번호

	public Pagination(int page, int total, int pageLinks, int limit) {
		this.page = page;
		if (this.page <= 0)
			this.page = 1;

		// 페이지 구간별 개수
		pageLinks = (pageLinks <= 0) ? 5 : pageLinks;

		// 1페이지당 출력 개수
		limit = (limit <= 0) ? 3 : limit;

		num = (int) floor((this.page - 1) / pageLinks); // 페이지 구간 번호

		startNo = num * pageLinks + 1; // 구간별 시작 번호
		lastNo = startNo + pageLinks - 1; // 구간별 종료 번호

		// 마지막 페이지 번호
		lastPageNo = (int) ceil(total / (double) limit);

		// 마지막 페이지 구간
		lastNum = (int) floor((lastPageNo - 1) / pageLinks);

		// 마지막 구간의 페이지 번호가 마지막 페이지 번호보다 크면 X
		if (lastNo > lastPageNo) {
			lastNo = lastPageNo;
		}

		// 이전 구간 페이지 시작 번호
		if (num > 0) {
			prevNo = (num - 1) * pageLinks + 1;
		}

		// 다음 구간 페이지 시작 번호
		if (num < lastNum) { // 현재 페이지 구간이 마지막 페이지 구간보다 이전인 경우
			nextNo = (num + 1) * pageLinks + 1;
		}
	}

	public Pagination(int page, int total, int pageLinks) {
		this(page, total, pageLinks, 3);
	}

	public Pagination(int page, int total) {
		this(page, total, 5, 3);
	}

	public Pagination(String page, int total) {
		this((page == null) ? 1 : Integer.parseInt(page), total);
	}

	public Pagination(HttpServletRequest request, int total) {
		this(request.getParameter("page"), total);
	}

	public String getPageHtml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ul class='pagination'>");

		// 첫 페이지
		if (num > 0) {
			sb.append("<li class='page'>");
			sb.append("<a href='?page=1'>");
			sb.append("first");
			sb.append("</a>");
			sb.append("</li>");
		}

		// 이전 페이지 링크
		if (prevNo > 0) {
			sb.append("<li class='page'>");
			sb.append("<a href='?page=" + prevNo + "'>");
			sb.append("prev");
			sb.append("</a>");
			sb.append("</li>");
		}

		for (int i = startNo; i <= lastNo; i++) {
			sb.append("<li class='page'>");
			sb.append("<a href='?page=" + i + "'>");
			if (page == i) {
				sb.append("<b>");
			}
			sb.append(i);
			if (page == i) {
				sb.append("</b>");
			}
			sb.append("</a>");
			sb.append("</li>");
		}

		// 다음 페이지
		if (nextNo > 0) {
			sb.append("<li class='page'>");
			sb.append("<a href='?page=" + nextNo + "'>");
			sb.append("next");
			sb.append("</a>");
			sb.append("</li>");
		}

		// 마지막 페이지
		if (num < lastNum) {
			sb.append("<li class='page'>");
			sb.append("<a href='?page=" + lastPageNo + "'>");
			sb.append("last");
			sb.append("</a>");
			sb.append("</li>");
		}

		sb.append("</ul>");

		return sb.toString();
	}
}
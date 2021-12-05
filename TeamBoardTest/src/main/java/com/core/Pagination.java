package com.core;

import static java.lang.Math.*;
import javax.servlet.http.HttpServletRequest;
import com.core.*;

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
	private String url; // 페이징 기본 URL
				
	
	/**
	 * 생성자
	 * 
	 * @param request
	 * @param limit   1페이지에 노출될 레코드 갯수
	 * @param total   전체 레코드수
	 * @param url     쿼리스트링 포함 URL
	 */
	public Pagination(int page, int total, int pageLinks, int limit, String url) {
		HttpServletRequest request = Req.get();
		try {
			this.page = page;
			if (this.page <= 0)
				this.page = 1;

			// 페이지 구간별 개수
			pageLinks = (pageLinks <= 0) ? 5 : pageLinks;

			// 1페이지당 출력 개수
			limit = (limit <= 0) ? 15 : limit;

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

			/**
			 * url이 null 인 경우는 URL 생성
			 */
			this.url = url;
			if (this.url == null) {
				StringBuilder sb = new StringBuilder();
				sb.append(request.getRequestURI());
				sb.append("?");
				String qs = request.getQueryString();
				if (qs != null && !qs.equals("")) {
					String[] _qs = qs.split("&");
					boolean isFirst = true;
					for (String s : _qs) {
						if (s.indexOf("page=") != -1)
							continue;
						else {
							if (!isFirst)
								sb.append("&");

							sb.append(s);
							isFirst = false;
						} // endif
					} // endfor
					sb.append("&");
				} // endif
				this.url = sb.toString();
			} else {
				this.url += "?";
			}
		} catch (ArithmeticException | NumberFormatException e) {
			// 총 레코드가 0이거나 숫자가 아닌 문자가 들어오는 경우 예외 발생
			e.printStackTrace();
		}

	}

	public Pagination(int page, int total, int pageLinks, String url) {
		this(page, total, pageLinks, 0, null);
	}

	public Pagination(int page, int total) {
		this(page, total, 0, 0, null);
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
			sb.append("<<");
			sb.append("</a>");
			sb.append("</li>");
		}

		// 이전 페이지 링크
		if (prevNo > 0) {
			sb.append("<li class='page'>");
			sb.append("<a href='?page=" + prevNo + "'>");
			sb.append("<");
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
			sb.append(">");
			sb.append("</a>");
			sb.append("</li>");
		}

		// 마지막 페이지
		if (num < lastNum) {
			sb.append("<li class='page'>");
			sb.append("<a href='?page=" + lastPageNo + "'>");
			sb.append(">>");
			sb.append("</a>");
			sb.append("</li>");
		}

		sb.append("</ul>");

		return sb.toString();
	}
}
package com.core;

public class Pagination2 {
  private int startNo;
  private int lastNo;

  public Pagination2(int page, int total) {
    // 페이지 구간 번호
    // 구간별 페이지 갯수
    page = (page <= 0) ? 1 : page;
    int pageLink = 5;
    int num = (int) Math.floor((page - 1) / pageLink);
    System.out.printf("num = %d", num);

    startNo = (num * pageLink) + 1;
    lastNo = (startNo + pageLink) - 1;

    int limit = 15;// 1페이지당 레코드 개수
    int lastPageNo = (int)ceil

    /**
     * lastNo <= 마지막 페이지 1) 마지막 페이지(1 페이지당 레코드 갯수) 2) 구간별 종료 번호가 마지막 페이지보다 큰지 체크. 크면
     * = 마지막 페이지 3)
     */
  }

  public String getPageHtml() {
    StringBuilder sb = new StringBuilder();
    sb.append("<ul class='pagination'>");
    for (int i = startNo; i <= lastNo; i++) {
      sb.append("<li>");
      sb.append(i);
      sb.append("</li>");
    }
    sb.append("</ul>");
    return sb.toString();
  }

}

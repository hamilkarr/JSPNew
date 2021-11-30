package com.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.models.board.*;
import com.models.comment.*;
import com.models.member.*;
import com.core.*;

import org.json.simple.*;

public class BoardController extends HttpServlet {
	private static final long serialVersionUID = -2654268002350534420L;

	private String httpMethod; // 요청 메서드
	private PrintWriter out;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String URI = request.getRequestURI();
		String mode = URI.substring(URI.lastIndexOf("/") + 1);
		httpMethod = request.getMethod().toUpperCase();

		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		if (httpMethod.equals("GET")) {
			response.setContentType("text/html; charset=utf-8");
		}

		out = response.getWriter();

		switch (mode) {
		case "write": // 게시글 작성
			writeController(request, response);
			break;
		case "list": // 게시글 목록
			listController(request, response);
			break;
		case "edit": // 게시글 조회
			editController(request, response);
			break;
		case "delete": // 게시글 삭제
			deleteController(request, response);
			break;
		case "view": // 게시글 상세보기
			viewController(request, response);
			break;
		case "comment": // 댓글 작성
			commentController(request, response);
			break;
		case "delete_comment": // 댓글 삭제
			deleteCommentController(request, response);
			break;
		default: // 없는 페이지
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/404.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void writeController(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MemberDao memDao = MemberDao.getInstance();
		boolean Login = memDao.isLogin(req);

		try {
			if (Login != true) {
				throw new Exception("로그인후 이용하실수 있습니다.");
			}

			req.setAttribute("mode", "write");
			RequestDispatcher rd = req.getRequestDispatcher("/views/board/write.jsp");
			rd.include(req, res);
		} catch (Exception e) {
			out.printf("<script>alert('%s');parent.location.href='../';</script>", e.getMessage());
		}

		if (httpMethod.equals("POST")) { // 등록 처리
			try {
				if (req.getParameter("memId") == "") {
					out.print("<script>parent.location.href='../';</script>");
					throw new Exception("로그인을 해주세요!");
				}
				BoardDao dao = BoardDao.getInstance();
				int result = dao.add(req);
				if (result > 0) {
					out.print("<script>parent.location.href='list';</script>");
				} else {
					out.print("<script>parent.location.reload();</script>");
					throw new Exception("작업등록 실패하였습니다.");
				}
			} catch (Exception e) {
				out.printf("<script>alert('%s');</script>", e.getMessage());
			}
		}
	}

	private void listController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			BoardDao dao = BoardDao.getInstance();
			ArrayList<Board> list = dao.getList(request);

			int total = dao.getTotal();
			total = 10000;
			Pagination pagination = new Pagination(request, total);
			String pagingHtml = pagination.getPageHtml();

			request.setAttribute("list", list);
			request.setAttribute("pagingHtml", pagingHtml);
		} catch (Exception e) {
			out.printf("<script>alert('%s');history.back();</script>", e.getMessage());
		}

		RequestDispatcher rd = request.getRequestDispatcher("/views/board/list.jsp");
		rd.include(request, response);
	}

	private void editController(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MemberDao memDao = MemberDao.getInstance();
		BoardDao dao = BoardDao.getInstance();

		try {
			boolean Login = memDao.isLogin(req);
			if (Login != true) {
				throw new Exception("회원만 이용하실수 있습니다.");
			}

			int postNm = Integer.parseInt(req.getParameter("postNm"));

			if (req.getParameter("postNm") == null) {
				throw new Exception("잘못된 접근입니다.");
			}

			Board board = dao.get(postNm);
			if (board == null) {
				throw new Exception("게시글이 없습니다!");
			}

			req.setAttribute("mode", "edit");
			req.setAttribute("board", board);

			RequestDispatcher rd = req.getRequestDispatcher("/views/board/write.jsp");
			rd.include(req, res);
		} catch (Exception e) {
			out.printf("<script>alert('%s');parent.location.href='../';</script>", e.getMessage());
		}

		if (httpMethod.equals("POST")) {
			try {
				if (req.getParameter("memId") == "") {
					out.print("<script>parent.location.href='../';</script>");
					throw new Exception("로그인을 해주세요!");
				}
				boolean rs = dao.edit(req);
				if (!rs) {
					throw new Exception("수정에 실패하였습니다.");
				}
				out.print("<script>parent.location.href='list';</script>");
			} catch (Exception e) {
				out.printf("<script>alert('%s');</script>", e.getMessage());
			}
		}
	}

	public void deleteController(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MemberDao memDao = MemberDao.getInstance();
		BoardDao dao = BoardDao.getInstance();
		boolean Login = memDao.isLogin(req);

		try {
			if (Login != true) {
				throw new Exception("회원만 이용하실수 있습니다.");
			}

			if (req.getParameter("postNm") == null) {
				throw new Exception("게시글이 없습니다.");
			}

			int postNm = Integer.parseInt(req.getParameter("postNm"));
			boolean rs = dao.delete(postNm);
			if (!rs) {
				throw new Exception("삭제 실패하였습니다.");
			}
			out.print("<script>parent.location.href='list';</script>");
		} catch (Exception e) {
			out.printf("<script>alert('%s');</script>", e.getMessage());
		}
	}

	public void viewController(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		BoardDao dao = BoardDao.getInstance();
		int postNm = Integer.parseInt(req.getParameter("postNm"));
		try {
			if (req.getParameter("postNm") == null) {
				throw new Exception("잘못된 접근입니다.");
			}
			Board view = dao.get(postNm);
			req.setAttribute("view", view);

			// 댓글
			ArrayList<Comment> comments = CommentDao.getInstance().getList(postNm);
			req.setAttribute("comments", comments);
		} catch (Exception e) {
			Logger.log(e);
		}

		RequestDispatcher rd = req.getRequestDispatcher("/views/board/view.jsp");
		rd.include(req, res);
	}

	/**
	 * 댓글 작성 처리
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void commentController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommentDao dao = CommentDao.getInstance();
		String mode = request.getParameter("mode");
		mode = (mode == null) ? "" : mode;
		try {
			switch (mode) {
			/** 댓글 조회 */
			case "get_comment":
				if (request.getParameter("commentNm") != null) {
					Comment comment = dao.get(request);
					out.print(comment.getContent());
				}
				break;
			/** 댓글 수정 */
			case "edit":
				HashMap<String, Object> map = new HashMap<>();
				try {
					boolean result = dao.edit(request);
					if (!result) {
						throw new Exception("댓글 수정 실패!");
					}
					map.put("success", true);
				} catch (Exception e) {
					map.put("success", false);
					map.put("message", e.getMessage());
				}
				JSONObject json = new JSONObject(map);
				out.print(json);
				break;
			default:
				int commentNm = dao.add(request);
				if (commentNm == 0) {
					throw new Exception("댓글 등록 실패!");
				}
				String url = "../board/view?postNm=" + request.getParameter("postNm") + "&commentNm=" + commentNm;
				CommonLib.go(out, url, "parent");
			}
		} catch (Exception e) {
			CommonLib.msg(out, e);
		}
	}

	/** 댓글 삭제 */
	private void deleteCommentController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			boolean result = CommentDao.getInstance().delete(request);
			if (!result) {
				throw new Exception("댓글 삭제 실패!");
			}

			CommonLib.reload(out, "parent"); // 댓글 삭제 성공시 새로고침
		} catch (Exception e) {
			Logger.log(e);
			CommonLib.msg(out, e.getMessage());
		}
	}
}

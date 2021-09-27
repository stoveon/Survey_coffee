package exam.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.model.BoardDao;
import exam.model.BoardDto;

public class DetailAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("num") == null) {
			response.sendRedirect("/board/list");
		}else {
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDao dbPro = BoardDao.getInstance();
		BoardDto article = dbPro.getArticle(num);
		
		String writer = article.getWriter();
		String title = article.getTitle();
		String content = article.getContent();
		int readcount = article.getReadcount();
		Timestamp regdate = article.getRegdate();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("writer", writer);
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		request.setAttribute("readcount", new Integer(readcount));
		request.setAttribute("regdate", regdate);
		request.setAttribute("ref", new Integer(ref));
		request.setAttribute("step", new Integer(step));
		request.setAttribute("depth", new Integer(depth));
		}
		return "/exboard/detail.jsp";
	}

}

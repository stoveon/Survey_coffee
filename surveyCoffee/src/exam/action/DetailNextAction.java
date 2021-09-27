package exam.action;

import java.sql.Timestamp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.model.BoardDao;
import exam.model.BoardDto;

public class DetailNextAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("num") == null) {
			response.sendRedirect("/board/list");
		}else {
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDao dbPro = BoardDao.getInstance();
		BoardDto article = dbPro.getNextArticle(num);
		
		int nnum = article.getNum();
		String writer = article.getWriter();
		String title = article.getTitle();
		String content = article.getContent();
		int readcount = article.getReadcount();
		Timestamp regdate = article.getRegdate();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		
		request.setAttribute("num", new Integer(nnum));
		request.setAttribute("writer", writer);
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		request.setAttribute("readcount", new Integer(readcount));
		request.setAttribute("regdate", regdate);
		request.setAttribute("ref", ref);
		request.setAttribute("step", step);
		request.setAttribute("depth", depth);
		}
		return "/exboard/detail.jsp";
	}

}

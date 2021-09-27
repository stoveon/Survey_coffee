package exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.model.BoardDao;
import exam.model.BoardDto;

public class DeleteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		if(request.getParameter("num") == null) {
			response.sendRedirect("/board/list");
		}
		
		int num = Integer.parseInt(request.getParameter("num"));
		String inputPass = request.getParameter("inputPass");
		
		BoardDao dbPro = BoardDao.getInstance();
		BoardDto article = dbPro.getArticleDetail(num);
		
		String writer = article.getWriter();
		String title = article.getTitle();
		String content = article.getContent();
		int readcount = article.getReadcount();
		
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("writer", writer);
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		request.setAttribute("readcount", new Integer(readcount));
		
		return "/exboard/deleteForm.jsp";
	}

}

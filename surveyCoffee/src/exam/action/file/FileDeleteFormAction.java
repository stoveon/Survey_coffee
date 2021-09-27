package exam.action.file;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.action.CommandAction;
import exam.model.BoardDto;
import exam.model.FBoardDao;

public class FileDeleteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		if(request.getParameter("num") == null) {
			response.sendRedirect("/board/listF");
		}
		
		int num = Integer.parseInt(request.getParameter("num"));
		String inputPass = request.getParameter("inputPass");
		
		FBoardDao dbPro = FBoardDao.getInstance();
		BoardDto article = dbPro.getArticleDetail(num);
		
		String writer = article.getWriter();
		String title = article.getTitle();
		String content = article.getContent();
		int readcount = article.getReadcount();
		String file = article.getFile();
		
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("writer", writer);
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		request.setAttribute("file", file);
		request.setAttribute("readcount", new Integer(readcount));
		
		return "/exfile/fileDeleteForm.jsp";
	}

}

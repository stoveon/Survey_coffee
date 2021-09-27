package exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.model.BoardDao;
import exam.model.BoardDto;

public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("num") == null) {
			response.sendRedirect("/board/list");
		}
		
		int num = Integer.parseInt((String)request.getParameter("num"));
		String inputPass = request.getParameter("inputPass");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		BoardDto updateArticle = new BoardDto();
		updateArticle.setNum(num);
		updateArticle.setTitle(title);
		updateArticle.setContent(content);
		
		BoardDao dbPro = BoardDao.getInstance();
		int result = dbPro.getArticleUpdate(updateArticle, inputPass);
		
		request.setAttribute("result", new Integer(result));
		
		return "/exboard/updatePro.jsp";
	}

}

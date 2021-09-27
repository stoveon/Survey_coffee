package exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.model.BoardDao;
import exam.model.BoardDto;

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("num") == null) {
			response.sendRedirect("/board/list");
		}
		
		int num = Integer.parseInt(request.getParameter("num"));
		String inputPass = request.getParameter("inputPass");
		
		BoardDao dbPro = BoardDao.getInstance();
		int result = dbPro.getArticleDelete(num, inputPass);
		
		request.setAttribute("result", new Integer(result));
		
		return "/exboard/deletePro.jsp";
	}

}

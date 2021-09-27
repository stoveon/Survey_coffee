package exam.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.model.BoardDao;
import exam.model.BoardDto;

public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		BoardDto article = new BoardDto();
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setTitle(request.getParameter("title"));
		article.setContent(request.getParameter("content"));
		article.setPass(request.getParameter("pass"));
		article.setRef(Integer.parseInt(request.getParameter("ref")));
		article.setStep(Integer.parseInt(request.getParameter("step")));
		article.setDepth(Integer.parseInt(request.getParameter("depth")));
		article.setRegdate(new Timestamp(System.currentTimeMillis()));
		
		BoardDao dbPro = BoardDao.getInstance();
		int result = dbPro.getArticleInsert(article);
		
		request.setAttribute("result", new Integer(result));
		
		return "/exboard/writePro.jsp";
	}

}

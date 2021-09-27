package exam.action.file;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.action.CommandAction;
import exam.model.BoardDto;
import exam.model.FBoardDao;

public class FileDetailAgoAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("num") == null) {
			response.sendRedirect("/board/list");
		}else {
		int num = Integer.parseInt(request.getParameter("num"));
		
		FBoardDao dbPro = FBoardDao.getInstance();
		BoardDto article = dbPro.getAgoArticle(num);

		if(article == null) {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter write = response.getWriter();
		write.println("<script languag='javascript'>");
		write.println("alert('이전글이 없습니다.'");
		write.println("</script>");
		write.flush();
		}else {
		
		int anum = article.getNum();
		String writer = article.getWriter();
		String title = article.getTitle();
		String content = article.getContent();
		int readcount = article.getReadcount();
		Timestamp regdate = article.getRegdate();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		String file = article.getFile();
		
		request.setAttribute("num", new Integer(anum));
		request.setAttribute("writer", writer);
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		request.setAttribute("readcount", new Integer(readcount));
		request.setAttribute("regdate", regdate);
		request.setAttribute("ref", new Integer(ref));
		request.setAttribute("step", new Integer(step));
		request.setAttribute("depth", new Integer(depth));
		request.setAttribute("file", file);
		
		}
		}
		return "/exfile/fileDetail.jsp";
	}

}

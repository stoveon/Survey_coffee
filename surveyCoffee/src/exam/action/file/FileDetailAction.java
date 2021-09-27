package exam.action.file;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.action.CommandAction;
import exam.model.BoardDto;
import exam.model.FBoardDao;

public class FileDetailAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("num") == null) {
			response.sendRedirect("/board/listF");
		}else {
		int num = Integer.parseInt(request.getParameter("num"));
		
		FBoardDao dbPro = FBoardDao.getInstance();
		BoardDto article = dbPro.getArticle(num);
		
		String writer = article.getWriter();
		String title = article.getTitle();
		String content = article.getContent();
		int readcount = article.getReadcount();
		Timestamp regdate = article.getRegdate();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		String file = article.getFile();
		
		String savePath = "/file/upload";
//		String realPath = request.getServletContext().getRealPath(savePath);
		String filePath = savePath + "/" + file;
		System.out.println(filePath);
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("writer", writer);
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		request.setAttribute("readcount", new Integer(readcount));
		request.setAttribute("regdate", regdate);
		request.setAttribute("ref", new Integer(ref));
		request.setAttribute("step", new Integer(step));
		request.setAttribute("depth", new Integer(depth));
		request.setAttribute("file", file);
		request.setAttribute("filePath", filePath);
		}
		return "/exfile/fileDetail.jsp";
	}

}

package exam.action.file;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import exam.action.CommandAction;
import exam.model.BoardDto;
import exam.model.FBoardDao;

public class FileUpdateChangeProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("num") == null) {
			response.sendRedirect("/board/listF");
		}
		
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println("num: " + num);
		String file = request.getParameter("file");
		
		boolean check = false;
		String savePath = "/file/upload";
		
		String writer = null;
		String title = null;
		String content = null;
		int readcount = 0;
		
		String realPath = request.getServletContext().getRealPath(savePath);
		
		String filePath = realPath + File.separator + file;
		
		File deleteFile = new File(filePath);
		
		if(deleteFile.exists()) {
			deleteFile.delete();
			check = true;
		}
		
		FBoardDao dbPro = FBoardDao.getInstance();
		BoardDto article = dbPro.getArticleDetail(num);
		
		writer = article.getWriter();
		title = article.getTitle();
		content = article.getContent();
		readcount = article.getReadcount();
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("writer", writer);
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		request.setAttribute("readcount", new Integer(readcount));
		request.setAttribute("check", check);
		
		return "/exfile/fileUpdateChangePro.jsp";
	}

}

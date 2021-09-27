package exam.action.file;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import exam.action.CommandAction;
import exam.model.BoardDto;
import exam.model.FBoardDao;

public class FileUpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("num") == null) {
			response.sendRedirect("/board/listF");
		}
		
		int num = 0;
		String inputPass = null;
		String title = null;
		String content = null;
		
		String fileName = null;
		String savePath = "/file/upload";
		String encType = "UTF-8";
		int uploadFileSizeLimit = 1024*1024*50;
		MultipartRequest multi = null;
		
		String realPath = request.getServletContext().getRealPath(savePath);
		
		try {
			
			multi = new MultipartRequest(request, realPath, uploadFileSizeLimit,
											encType, new DefaultFileRenamePolicy());
			fileName = multi.getFilesystemName("file");
			num = Integer.parseInt(multi.getParameter("num"));
			title = multi.getParameter("title");
			content = multi.getParameter("content");
			inputPass = multi.getParameter("inputPass");
			}catch(Exception e) {	}
		
		BoardDto updateArticle = new BoardDto();
		updateArticle.setNum(num);
		updateArticle.setTitle(title);
		updateArticle.setContent(content);
		updateArticle.setFile(fileName);
		
		FBoardDao dbPro = FBoardDao.getInstance();
		int result = dbPro.getArticleUpdate(updateArticle, inputPass);
		
		request.setAttribute("result", new Integer(result));
		
		return "/exfile/fileUpdatePro.jsp";
	}

}

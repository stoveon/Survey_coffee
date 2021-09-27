package exam.action.file;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import exam.action.CommandAction;
import exam.model.FBoardDao;

public class FileDeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("num") == null) {
			response.sendRedirect("/board/listF");
		}
		
		int num = Integer.parseInt(request.getParameter("num"));
		String inputPass = request.getParameter("inputPass");
		String file = request.getParameter("file");
		
		String savePath = "/file/upload";
		String realPath = request.getServletContext().getRealPath(savePath);
		
		String filePath = realPath + File.separator + file;
		
		File deleteFile = new File(filePath);
		
		if(deleteFile.exists()) {
			deleteFile.delete();
		}
		
		FBoardDao dbPro = FBoardDao.getInstance();
		int result = dbPro.getArticleDelete(num, inputPass);
		
		request.setAttribute("result", new Integer(result));
		
		return "/exfile/fileDeletePro.jsp";
	}

}

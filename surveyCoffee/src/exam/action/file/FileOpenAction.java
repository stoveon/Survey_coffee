package exam.action.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import exam.action.CommandAction;
import exam.model.BoardDto;
import exam.model.FBoardDao;

public class FileOpenAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("num") == null) {
			response.sendRedirect("/board/listF");
		}
		
		int num = Integer.parseInt(request.getParameter("num"));
		FBoardDao dbPro = FBoardDao.getInstance();
		BoardDto article = dbPro.getArticle(num);
		
		String fileName = article.getFile();
		String savePath = "/file/upload";
		String encType = "UTF-8";
		
		String realPath = request.getServletContext().getRealPath(savePath);
		//File.separator는 윈도우면 \, 리눅스면 /로 표시
		String downFile = realPath + File.separator + fileName;
	
		File file = new File(downFile);
		
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;
		OutputStream out = null;
		
		try {
			fis = new FileInputStream(downFile);
			baos = new ByteArrayOutputStream();
			
			int read = 0;
			byte[] buf = new byte[1024];
			while((read = fis.read(buf)) != -1) {
				baos.write(buf, 0, read);
			}
			
			byte[] imgBuf = null;
			imgBuf = baos.toByteArray();
			
			baos.close();
			fis.close();
			
			int length = imgBuf.length;
			out = response.getOutputStream();
			out.write(imgBuf, 0, length);
			
		}catch(Exception e) {
			e.printStackTrace();
		}

		out.close();
		
		return request.getContextPath() + "/board/DetailF?num=" + num;
	}

}

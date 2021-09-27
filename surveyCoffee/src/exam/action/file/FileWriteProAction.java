package exam.action.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import exam.action.CommandAction;
import exam.model.BoardDto;
import exam.model.FBoardDao;

public class FileWriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		int num = 0;
		int ref = 0;
		int step = 0;
		int depth = 0;
		String writer = null;
		String title = null;
		String content = null;
		String pass = null;
		
		//file저장
//		Part filePart = request.getPart("file");
//		String fileName = filePart.getSubmittedFileName();
//		InputStream fis = filePart.getInputStream();
		
		String savePath = "/file/upload";
		int uploadFileSizeLimit = 1024*1024*50;
		String encType = "UTF-8";
		MultipartRequest multi = null;
		String fileName = null;
		
		String realPath = request.getServletContext().getRealPath(savePath);
		System.out.println("fileRealPath: " + realPath);
		
//		String filePath  = realPath + File.separator + fileName;
		
		File folder = new File(realPath);
		if(!folder.exists()) {
			folder.mkdirs();
			System.out.println("폴더 생성 완료");
		}
		
		
		try {
						
		multi = new MultipartRequest(request, realPath, uploadFileSizeLimit,
										encType, new DefaultFileRenamePolicy());
		fileName = multi.getFilesystemName("file");
		num = Integer.parseInt(multi.getParameter("num"));
		ref = Integer.parseInt(multi.getParameter("ref"));
		step = Integer.parseInt(multi.getParameter("step"));
		depth = Integer.parseInt(multi.getParameter("depth"));
		writer = multi.getParameter("writer");
		title = multi.getParameter("title");
		content = multi.getParameter("content");
		pass = multi.getParameter("pass");
		}catch(Exception e) {	}
		
//		FileOutputStream fos = new FileOutputStream(filePath);
//		
//		byte[] buf = new byte[1024];
//		int size = 0;
//		while((size = fis.read(buf)) != -1) {
//			fos.write(buf, 0, size);			//1024씩 옮기다가 마지막에는 0부터 남은 size 만큼
//		}
//		
//		fos.close();
//		fis.close();
	
		BoardDto article = new BoardDto();
		article.setNum(new Integer(num));
		article.setWriter(writer);
		article.setTitle(title);
		article.setContent(content);
		article.setPass(pass);
		article.setRef(new Integer(ref));
		article.setStep(new Integer(step));
		article.setDepth(new Integer(depth));
		article.setRegdate(new Timestamp(System.currentTimeMillis()));
		article.setFile(fileName);
		
		FBoardDao dbPro = FBoardDao.getInstance();
		int result = dbPro.getArticleInsert(article);
		
		request.setAttribute("result", new Integer(result));
		
		return "/exfile/fileWritePro.jsp";
	}

}

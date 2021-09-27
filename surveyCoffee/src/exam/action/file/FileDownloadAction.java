package exam.action.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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

public class FileDownloadAction implements CommandAction{

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
		long length = file.length();
		
		//다운로드를 위한 응답 헤더 설정
		//다운로드창을 보여주기위한 응답헤더 설정
		//웹브라우저가 인식하지 못하는 ContentType(MIME)타입 설정 (보통 application/octet-stream 으로 설정)
		response.setContentType("application/octet-stream");
		
		//다운로드시 프로그레스바 표시를 위한 컨텐츠 길이 설정
		response.setContentLengthLong(length);
		
		
		boolean isIe = request.getHeader("user-agent").toUpperCase().indexOf("MSIE") != -1 ||
					   request.getHeader("user-agent").indexOf("11.0") != -1;
		if(isIe) {	//인터넷 익스플로러
			fileName = URLEncoder.encode(fileName, "encType");
		}else {		//기타 웹브라우저
			fileName = new String(fileName.getBytes(encType), "8859_1");
		}
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		//서버에 있는 파일에 연력할 입력 스트림 생성
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		
		//웹브라우저에 연결할 출력 스트림 생성
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		
		int read = 0;
		while((read = bis.read()) != -1) {
			bos.write(read);
		}
		
		bis.close();
		bos.close();
		
//		try {
//			FileInputStream fis = new FileInputStream(downFile);
//			OutputStream out = response.getOutputStream();
//			
//			int read = 0;
//			byte[] buf = new byte[1024];
//			while((read = fis.read(buf)) != -1) {
//				out.write(buf, 0, read);
//			}
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		fis.close();
//		fos.close();
		
		return request.getContextPath() + "/board/DetailF?num=" + num;
	}

}

package exam.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.model.BoardDao;
import exam.model.BoardDto;

public class ListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String p = request.getParameter("p");
		if(request.getParameter("p") == null) {
			p = "1";
		}
		int currentPage = Integer.parseInt(p);				//현재 페이지
		int pageCount = 5;									//한 페이지에 나올 게시글 수
		int startNum = (currentPage -1) * pageCount + 1;	//시작 번호
		int endNum = startNum + pageCount;
		int endPage = 0;
		int lastPage = 0;									//제일 끝 페이지 번호
		int startPage = 0;
		int totalCount = 0;									//게시글 총개 수
		int number = 0;

		BoardDao dbPro = BoardDao.getInstance();			//DB연결
		totalCount = dbPro.getArticleCount();
		if(endNum > totalCount) endNum = totalCount;
		
		lastPage = (int) (Math.ceil((double)totalCount / (double)pageCount));		//글 목록에 표시할 글 번호
		endPage = (int) Math.ceil(((double)currentPage / (double)pageCount)) * pageCount;
		if(lastPage < endPage) {
			endPage = lastPage;
		}		
		startPage = endPage - pageCount + 1;
		if(startPage < 1) startPage= 1;
		
		List<BoardDto> articleList = null;
		if(totalCount > 0) {
			articleList = dbPro.getArticles(startNum, endNum);
		}else {
			articleList = Collections.emptyList();
		}

		number = totalCount - (currentPage - 1) * pageCount;		//글 목록에 표시할 글 번호
		
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("pageCount", new Integer(pageCount));
		request.setAttribute("endNum", new Integer(endNum));
		request.setAttribute("startNum", new Integer(startNum));
		request.setAttribute("endPage", new Integer(endPage));
		request.setAttribute("lastPage", new Integer(lastPage));
		request.setAttribute("startPage", new Integer(startPage));
		request.setAttribute("totalCount", new Integer(totalCount));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		
		return "/exboard/list.jsp";
	}

}

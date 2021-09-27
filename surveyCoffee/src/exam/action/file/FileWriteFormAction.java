package exam.action.file;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.action.CommandAction;

public class FileWriteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int num = 0;
		int ref = 1;
		int step = 0;
		int depth = 0;
		String numcheck = request.getParameter("num");
		try {
			if(numcheck != null) {
				num = Integer.parseInt(request.getParameter("num"));
				ref = Integer.parseInt(request.getParameter("ref"));
				step = Integer.parseInt(request.getParameter("step"));
				depth = Integer.parseInt(request.getParameter("depth"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("ref", new Integer(ref));
		request.setAttribute("step", new Integer(step));
		request.setAttribute("depth", new Integer(depth));
		
		return "/exfile/fileWriteForm.jsp";
	}

}

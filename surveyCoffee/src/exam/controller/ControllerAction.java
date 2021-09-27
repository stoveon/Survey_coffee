package exam.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.action.CommandAction;
import exam.action.DeleteFormAction;
import exam.action.DetailAction;
import exam.action.ListAction;
import exam.action.UpdateFormAction;

public class ControllerAction extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	//명령어와 명령어 처리 클래스를 쌍으로 저장해두는 MAP
	private Map<String, Object> commandMap = new HashMap<String, Object>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String props = config.getInitParameter("propertyConfig");
		Properties pr = new Properties();
		FileInputStream f = null;
		String path = config.getServletContext().getRealPath("/WEB-INF");
		try {
			f = new FileInputStream(new File(path, props));
			pr.load(f);
		}catch(IOException e) {
			throw new ServletException(e);
		}finally {
			try {
				if(f != null) {f.close();}
			}catch(IOException e) {	}
		}
		
		Iterator<Object> keyIter = pr.keySet().iterator();
		
		while(keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String className = pr.getProperty(command);
			try {
				Class<?> commandClass = Class.forName(className);
				
				Object commandInstance = commandClass.newInstance();
				
				commandMap.put(command, commandInstance);
				System.out.println("board: " + command + "->" + commandInstance);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = null;
		CommandAction com = null;
		try {
			String command = request.getRequestURI();
			System.out.println("requestURI: " + command);
			if(command.indexOf(request.getContextPath()) == 0) {
				command = command.substring(request.getContextPath().length());
			}
			System.out.println("command:" + command);
			com = (CommandAction)commandMap.get(command);
			if(com != null) {
				view = com.requestPro(request, response);
				System.out.println("view:" + view);
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
			}
		}catch(Throwable e) {
			e.printStackTrace();
			RequestDispatcher error = request.getRequestDispatcher("/exboard/index.jsp");
			error.forward(request, response);			
		}
		
	}
}

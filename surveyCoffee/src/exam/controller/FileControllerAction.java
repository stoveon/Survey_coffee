package exam.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class FileControllerAction extends HttpServlet{
	
	private Map<String, Object> commandMap = new HashMap<String, Object>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String props = config.getInitParameter("propertyConfig");
		Properties pr = new Properties();
		FileInputStream fi = null;
		String path = config.getServletContext().getRealPath("/WEB-INF");
		
		try {
			fi = new FileInputStream(new File(path, props));
			pr.load(fi);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fi != null) {fi.close();}
			}catch(IOException e) {}
		}
		
		Iterator<Object> keyIter = pr.keySet().iterator();
		
		while(keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String className = pr.getProperty(command);
			try {
				Class<?> commandClass = Class.forName(className);
				
				Object commandInstance = commandClass.newInstance();
				
				commandMap.put(command, commandInstance);
				System.out.println("file: " + command + "->" + commandInstance);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
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
			System.out.println("requestURI : " + command);
			if(command.indexOf(request.getContextPath()) == 0) {
				command = command.substring(request.getContextPath().length());
			}
			System.out.println("command : " + command);
			com = (CommandAction) commandMap.get(command);
			
			if(com != null) {
				view = com.requestPro(request, response);
				System.out.println("view: " + view);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

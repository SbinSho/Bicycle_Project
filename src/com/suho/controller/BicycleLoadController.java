package com.suho.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suho.action.*;
import com.suho.search.LoadListAction;

public class BicycleLoadController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		System.out.println("Member_controller 실행완료!");
		System.out.println("RequstURL : " + RequestURI);
		System.out.println("contextPath : " + contextPath);
		System.out.println("command : " + command);
		
		if(command.equals("/LoadListAction.ld")){
			
			try {
				LoadListAction load = new LoadListAction();
				load.getLoad(request, response);
			} catch (Exception e) {
				System.out.println("LoadListAction 오류 : " + e);
				
			}
		}
		
		
		System.out.println("Member_controller 실행 끝!");
		System.out.println("=====================================");
	}
	
	
}

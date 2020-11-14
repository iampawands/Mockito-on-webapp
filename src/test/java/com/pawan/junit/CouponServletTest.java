package com.pawan.junit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CouponServletTest {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@Mock
	RequestDispatcher requestDispatcher;
	
	@Test
	public void doGet() throws IOException, ServletException {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(printWriter);
		new CouponServlet().doGet(request, response);
		assertEquals("SUPERSALE", stringWriter.toString());
	}
	
	@Test
	public void doPost() throws ServletException, IOException {
		when(request.getParameter(anyString())).thenReturn("SUPERSALE");
		when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
		new CouponServlet().doPost(request, response);
		verify(request).getParameter(anyString());
		verify(requestDispatcher).forward(request, response);
	}

}

/**
 * 
 */
package test.springweb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Oded Nissan
 *
 */
public class SecurityHandler extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(SecurityHandler.class);
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		boolean status = true;
		
		
		logger.debug("session = " + session);
		logger.debug("path =" + request.getRequestURI());
		logger.debug("in SecurityHandler preHandle");
		if(session == null) {
			logger.info("no session. redirecting to login page");
			status = false;
			response.sendRedirect("/SpringMVCTest/jsp/login.jsp");
		}
		return(status);
	}

}

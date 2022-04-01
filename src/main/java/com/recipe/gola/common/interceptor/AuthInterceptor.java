package com.recipe.gola.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.recipe.gola.common.code.ErrorCode;
import com.recipe.gola.common.exception.HandlableException;

public class AuthInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		
		String[] uriArr = request.getRequestURI().split("/");
		
		if(uriArr.length != 0) {
			switch (uriArr[1]) {
			case "dto":
				memberAuthorize(request,response,uriArr);
				break;

			default:
				break;
			}
		}
		
		
		return true;
	}

	private void memberAuthorize(HttpServletRequest request, HttpServletResponse response, String[] uriArr) {
		
		switch (uriArr[2]) {
		case "logout":
			// 로그인된 사용자인지 판단한 다음, 로그인 된 사용자가 아니라면 index로 돌려보냄
			if(request.getSession().getAttribute("authenticated") == null) {
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
			}
			
			break;
		default:
			break;
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}

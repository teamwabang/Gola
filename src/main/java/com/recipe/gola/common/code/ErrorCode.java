package com.recipe.gola.common.code;

public enum ErrorCode {
	
	UNAUTHORIZED_PAGE("접근 권한이 없는 페이지 입니다.");

	public final String MSG;
	
	ErrorCode(String msg) {
		this.MSG = msg;
	}

}

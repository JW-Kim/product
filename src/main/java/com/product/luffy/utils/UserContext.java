package com.product.luffy.utils;

import java.util.List;

import com.product.luffy.po.Auth;

public class UserContext {

	private static ThreadLocal<Auth> threadLocal = new ThreadLocal<Auth>();

	public static void put(Auth loginInfo) {
		if (loginInfo == null) {
			return;
		}
		threadLocal.set(loginInfo);
	}

	public static Auth get() {
		Auth loginInfo = threadLocal.get();
		if (loginInfo == null) {
			loginInfo = new Auth();
			put(loginInfo);
		}
		return loginInfo;
	}

	public static void remove() {
		threadLocal.remove();
	}

	public static String getUserId() {
		return get().getUserId();
	}

	public static String getUserNm() {
		return get().getUserNm();
	}

	public static String getUserLoginId() {
		return get().getUserLoginId();
	}
	
	public static String getUserRole() {
		return get().getUserRole();
	}
}

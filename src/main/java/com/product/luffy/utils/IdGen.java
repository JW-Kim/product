package com.product.luffy.utils;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class IdGen implements InitializingBean{
	private static AtomicLong currentIdx = new AtomicLong(0L);
    private static String siteCode="000";

    public IdGen() {
    }

    public static String getNextId() {
        StringBuffer idx = new StringBuffer();
        idx.append(System.currentTimeMillis());
        if (siteCode.length() > 2) {
            siteCode = siteCode.substring(siteCode.length() - 2, siteCode.length());
        }
        idx.append(siteCode);
        idx.append(String.format("%03d", getCurrentIndex()));
        return idx.toString();
    }

    private static Long getCurrentIndex() {
        Long idx = 0L;
        idx = getIdx();
        String idxStr = String.valueOf(idx);
        int strSize = idxStr.length();
        return strSize > 3 ? Long.parseLong(idxStr.substring(strSize - 3, strSize)) : idx;
    }

    private static Long getIdx() {
        Class var0 = IdGen.class;
        synchronized(IdGen.class) {
            Long idx = 0L;
            idx = currentIdx.getAndIncrement();
            return idx;
        }
    }
    
    public void afterPropertiesSet() throws Exception {

    }
	
}

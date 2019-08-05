package com.product.luffy.utils;

public class ProductUtil {
    public static  final String cleanXss(String param) {
        if(param == null || "".equals(param)) {
            return param;
        }

        String orgText = param;

        String[] dangerousTexts = {
                "eval\\((.*)\\)",
                "[\\\"\\\']*javascript:(.*)[\\\"\\\']",
                "<script",
                "<iframe",
                "<img",
                "<Embed",
                "document.cookie",
                "document.location",
                "document.write",
                "window.open",
                "window.location",
                "href="
        };

        for (int i = 0; i< dangerousTexts.length; i++) {
            String dangerousText = dangerousTexts[i];
            String tempOrgText = orgText.replace(" ", "").toLowerCase();

            if(tempOrgText.contains(dangerousText)) {
                if("<!--".equals(dangerousText)) {
                    orgText = orgText.replaceAll("<!--", "&lt;!--").replaceAll("-->", "--&gt;");
                } else if (orgText.contains("<")) {
                    orgText = orgText.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
                } else {
                    orgText = orgText.replaceAll(dangerousText, "");
                }
            }
        }

        return orgText;
    }
}

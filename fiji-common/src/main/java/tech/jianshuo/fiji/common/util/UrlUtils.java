package tech.jianshuo.fiji.common.util;

import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * url utils
 */
public class UrlUtils {

    private static final Logger logger = LoggerFactory.getLogger(HtmlUtils.class);
    private static final int HTTP_OK = 200;

    public static synchronized boolean isConnect(String urlStr) {
        int counts = 0;
        if (urlStr == null || urlStr.length() <= 0) {
            return false;
        }
        while (counts < 3) {
            try {
                URL url = new URL(urlStr);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                int state = con.getResponseCode();
                if (state == HTTP_OK) {
                    return true;
                }
                break;
            } catch (Exception ex) {
                counts++;
            }
        }
        return false;
    }

    public static String getDomainUrl(String urlStr) {
        String domainUrl = urlStr;
        try {
            URL url = new URL(urlStr);
            domainUrl = url.getProtocol() + "://" + url.getAuthority();
        } catch (Exception e) {
            logger.error("getDomainUrl is erro,url :" + urlStr, e);
        }
        return domainUrl;
    }

    public static String getHost(String urlStr) {
        String host = urlStr;
        try {
            URL url = new URL(urlStr);
            host = url.getHost();
        } catch (Exception e) {
            logger.error("getHost is erro,url :" + urlStr, e);
        }
        return host;
    }

}

package tech.jianshuo.fiji.common.util;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * url utils
 */
public class UrlUtils {

    private static final Logger logger = LoggerFactory.getLogger(UrlUtils.class);
    private static final int HTTP_OK = 200;

    public static String getDomainUrl(String urlStr) {
        String domainUrl = urlStr;
        try {
            URL url = new URL(urlStr);
            domainUrl = url.getProtocol() + "://" + url.getAuthority();
        } catch (Exception e) {
            logger.error("parse domainUrl from url error, url is: {}", urlStr, e);
        }
        return domainUrl;
    }

    public static String getHost(String urlStr) {
        String host = urlStr;
        try {
            URL url = new URL(urlStr);
            host = url.getHost();
        } catch (Exception e) {
            logger.error("parse host from url error, url is: {}", urlStr, e);
        }
        return host;
    }

}

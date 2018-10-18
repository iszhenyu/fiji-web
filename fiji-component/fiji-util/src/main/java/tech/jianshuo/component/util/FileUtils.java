package tech.jianshuo.component.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author zhen.yu
 * Created on 2018-09-02
 */
public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static String getFileExtName(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return null;
        }
        int i = fileName.lastIndexOf('.');
        if (i > -1) {
            return fileName.substring(i + 1).toLowerCase();
        }
        return null;
    }

    public static void saveUploadedFile(byte[] fileBytes, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        boolean createFilePathSuccess = true;
        if (!targetFile.exists()) {
            createFilePathSuccess = targetFile.mkdirs();
        }
        if (!createFilePathSuccess) {
            logger.error("failed to create file path: {}", filePath);
            return;
        }
        FileOutputStream out = new FileOutputStream(join(filePath, fileName));
        out.write(fileBytes);
        out.flush();
        out.close();
    }

    public static String join(String filePath, String fileName) {
        StringBuilder builder = new StringBuilder();
        if (filePath.endsWith(File.pathSeparator)) {
            builder.append(filePath);
        } else {
            builder.append(filePath).append(File.pathSeparator);
        }
        if (fileName.startsWith(File.pathSeparator)) {
            builder.append(fileName.substring(File.pathSeparator.length()));
        } else {
            builder.append(fileName);
        }
        return builder.toString();
    }
}

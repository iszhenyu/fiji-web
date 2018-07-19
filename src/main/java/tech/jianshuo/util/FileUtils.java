package tech.jianshuo.util;

import java.io.File;
import java.io.FileOutputStream;

/**
*/
public class FileUtils {

    /**
     * 获取文件类型
     * @param fileName
     * @return
     */
    public static String getFileExtName(String fileName) {
        if (fileName != null) {
            int i = fileName.lastIndexOf('.');
            if (i > -1) {
                return fileName.substring(i + 1).toLowerCase();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 上传文件
     * @param fileBytes
     * @param filePath
     * @param fileName
     * @throws Exception
     */
    public static void uploadFile(byte[] fileBytes, String filePath, String fileName)
            throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(fileBytes);
        out.flush();
        out.close();
    }

}
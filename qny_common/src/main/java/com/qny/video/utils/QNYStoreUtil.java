package com.qny.video.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 * @author ttpfx
 * @since 2023/10/25
 */
public class QNYStoreUtil {

    public static boolean upload() throws QiniuException {
        // 七牛云的配置信息
        String accessKey = "bpCQ7xwCT8QWx2D_XG4iDve5lV9sv4kMCEAvrTIJ";
        String secretKey = "j_IGV6OPI5Nf3HkhTrIDzzYkLHok-WVxQYXuTz02";
        String bucket = "qny-book";
        String key = "hello.txt"; // 上传到七牛云后的文件名

        // 创建上传对象
        Configuration cfg = new Configuration();
        UploadManager uploadManager = new UploadManager(cfg);

        // 生成上传凭证
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        // 本地文件路径
        String localFilePath = "C:\\Users\\14722\\Desktop\\hello.txt";

        try {
            // 调用put方法上传
            Response response = uploadManager.put(localFilePath, key, upToken);
            // 解析上传成功的结果
            System.out.println("上传成功，返回信息：" + response.bodyString());
            return true;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println("上传失败，错误信息：" + r.bodyString());
        }
        return false;
    }

    public static void main(String[] args) throws QiniuException {
        upload();
    }
}

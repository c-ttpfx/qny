package com.qny.common.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 七牛云工具类
 *
 * @author ttpfx
 * @since 2023/10/25
 */
public class QNYStoreUtil {

    // 七牛云账户的访问密钥和私有密钥
    private static final String ACCESS_KEY = "bpCQ7xwCT8QWx2D_XG4iDve5lV9sv4kMCEAvrTIJ";
    private static final String SECRET_KEY = "j_IGV6OPI5Nf3HkhTrIDzzYkLHok-WVxQYXuTz02";
    // 七牛云存储空间的名称
    private static final String BUCKET_NAME = "qny-book";
    // 数据处理通道的名称，可以在七牛云控制台创建和查看
    private static final String PIPELINE = "video-queue";

    /**
     * 上传并转码视频为m3u8格式的方法。
     *
     * @param localFilePath 本地视频文件的路径。
     * @return m3u8文件地址。
     * @throws QiniuException 如果上传过程中发生错误，将抛出QiniuException异常。
     */
    public static String uploadAndTranscodeVideo(String localFilePath) throws QiniuException {
        // 创建配置对象并指定区域，以华北区为例
        Configuration cfg = new Configuration(Region.huanan());
        // 创建上传管理器对象
        UploadManager uploadManager = new UploadManager(cfg);

        // 创建授权对象，并生成上传凭证
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

        // 获取当前时间
        LocalDate now = LocalDate.now();
        String currentDateTime = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth() + "/"
                + LocalDateTime.now().getHour() + "/" + LocalDateTime.now().getMinute() + "/";

        String filename = localFilePath.substring(localFilePath.lastIndexOf("\\") + 1);
        // 构建七牛云中的文件名和路径
        String key = currentDateTime + System.currentTimeMillis() + "--" + filename;

        // 定义转码操作
        String saveAsKey = currentDateTime + System.currentTimeMillis()
                + "--" + filename.substring(0, filename.lastIndexOf(".") + 1) + "m3u8";
        String hlsBaseUrl = currentDateTime;
        String fops = String.format(
                "avthumb/m3u8/segment/%d/hls_base_url/%s|saveas/%s",
                10,  // 设置分段时长为10秒，可以根据需要调整
                UrlSafeBase64.encodeToString(hlsBaseUrl),
                UrlSafeBase64.encodeToString(BUCKET_NAME + ":" + saveAsKey)
        );

        // 设置上传策略中的persistentOps字段以指定转码操作
        StringMap putPolicy = new StringMap();
        putPolicy.put("persistentOps", fops);
        putPolicy.put("persistentPipeline", PIPELINE);

        // 生成上传凭证
        String upToken = auth.uploadToken(BUCKET_NAME, key, 3600, putPolicy);

        // 上传视频文件并返回响应对象
        Response response = uploadManager.put(localFilePath, key, upToken);
        if (response.isOK()) {
            return saveAsKey;
        } else {
            return null;
        }
    }

    public static Map<String, String> getQiNiuToken(String fileName) {
        // 创建授权对象，并生成上传凭证
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

        // 获取当前时间
        LocalDate now = LocalDate.now();
        String currentDateTime = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth() + "/"
                + LocalDateTime.now().getHour() + "/" + LocalDateTime.now().getMinute() + "/";

        // 构建七牛云中的文件名和路径
        String key = currentDateTime + System.currentTimeMillis() + "--" + fileName;

        // 定义转码操作
        String saveAsKey = currentDateTime + System.currentTimeMillis()
                + "--" + fileName.substring(0, fileName.lastIndexOf(".") + 1) + "m3u8";
        String fops = String.format(
                "avthumb/m3u8/segment/%d/hls_base_url/%s|saveas/%s",
                10,  // 设置分段时长为10秒，可以根据需要调整
                UrlSafeBase64.encodeToString(currentDateTime),
                UrlSafeBase64.encodeToString(BUCKET_NAME + ":" + saveAsKey)
        );

        // 设置上传策略中的persistentOps字段以指定转码操作
        StringMap putPolicy = new StringMap();
        putPolicy.put("persistentOps", fops);
        putPolicy.put("persistentPipeline", PIPELINE);

        // 生成上传凭证
        String token = auth.uploadToken(BUCKET_NAME, key, 3600, putPolicy);

        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("key", key);
        return map;
    }

    public static void main(String[] args) {
        try {
            // String localFilePath = "D:\\代码\\JAVA代码\\qny\\qny_video\\src\\main\\resources\\video\\1.mp4";
            String localFilePath = "C:\\Users\\16071\\Desktop\\QQ录屏20231105185901.mp4";
            String s = uploadAndTranscodeVideo(localFilePath);
            System.out.println(s);
        } catch (QiniuException ex) {
            ex.printStackTrace();
        }
    }
}

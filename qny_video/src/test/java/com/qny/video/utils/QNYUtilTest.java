package com.qny.video.utils;

import com.qiniu.common.QiniuException;
import com.qny.common.utils.FileUtils;
import com.qny.common.utils.QNYStoreUtil;
import com.qny.video.domain.model.VideoMetadataModel;
import com.qny.video.service.VideoMetadataService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


/**
 * @author ttpfx
 * @since 2023/10/27
 */
@SpringBootTest
public class QNYUtilTest {

    @Resource
    private VideoMetadataService videoMetadataService;

    @Test
    public void addVideo() {
        String prefix = "http://s32r2vddr.hn-bkt.clouddn.com/";
        FileUtils.readFiles("D:\\资料\\学习资料\\视频", path->{
            String filePath = path.toString();
            try {
                String s = QNYStoreUtil.uploadAndTranscodeVideo(filePath);
                VideoMetadataModel videoMetadata = new VideoMetadataModel();
                videoMetadata.setFilePath(prefix+s);
                videoMetadataService.save(videoMetadata);
            } catch (QiniuException e) {
                throw new RuntimeException(e);
            }
        });
    }

}

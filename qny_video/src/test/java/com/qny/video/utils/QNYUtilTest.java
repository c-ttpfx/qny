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
        FileUtils.readFiles("C:\\Users\\14722\\Desktop\\体育", path->{
            String filePath = path.toString();
            try {
                String s = QNYStoreUtil.uploadAndTranscodeVideo(filePath);
                VideoMetadataModel videoMetadata = new VideoMetadataModel();
                videoMetadata.setFilePath(prefix+s);
                videoMetadata.setUploaderId("1721635672898420738");
                videoMetadataService.save(videoMetadata);
            } catch (QiniuException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

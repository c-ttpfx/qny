package com.qny.video.controller;

import com.qny.common.domain.entity.Result;
import com.qny.common.exception.VerifyException;
import com.qny.common.utils.JwtUtil;
import com.qny.common.utils.QNYStoreUtil;
import com.qny.common.utils.ValidationUtil;
import com.qny.video.domain.dto.VideoMetadataDTO;
import com.qny.video.domain.request.VideoMetadataRequest;
import com.qny.video.domain.vo.VideoInfoVO;
import com.qny.video.service.VideoMetadataService;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author ttpfx
 * @since 2023/10/25
 */
@RestController
@RequestMapping("/video")
public class VideoController {

    @Resource
    private VideoMetadataService videoMetadataService;

    /**
     * 返回一个随机的视频地址
     * @return Result
     */
    @GetMapping("/randomVideo")
    public Result<VideoInfoVO> getVideo() {
        // todo 这里后续必须使用Redis优化，现在先让功能跑起来
        VideoInfoVO videoInfoVO = videoMetadataService.randomVideoInfo();
        return Result.ok(videoInfoVO);
    }

    /**
     * 保存视频元信息
     * @param vmr VideoMetadataRequest
     * @return Result
     */
    @PostMapping("/save")
    public Result<Object> save(@Valid @RequestBody VideoMetadataRequest vmr,
                               BindingResult bindingResult) throws VerifyException {
        ValidationUtil.check(bindingResult);
        VideoMetadataDTO vmd = new VideoMetadataDTO();
        BeanUtils.copyProperties(vmr, vmd);
        vmd.setUploaderId(JwtUtil.getUserID());
        boolean flag = videoMetadataService.save(vmd);
        if (!flag){
            return Result.fail();
        }
        return Result.ok();
    }

    @PostMapping("/getQiNiuToken")
    public Result<Map<String, String>> getQiNiuToken(@RequestBody Map<String, String> map) {
        return Result.ok(QNYStoreUtil.getQiNiuToken(map.get("fileName")));
    }

    /**
     * 获取体育标签的视频
     * @return list
     */
    @GetMapping("/physicalCultureVideo")
    public Result<List<VideoInfoVO>> getPhysicalCultureVideo() {
        System.out.println("getPhysicalCultureVideo");
        return Result.ok(videoMetadataService.getPhysicalCultureVideo("体育"));
    }

    @GetMapping("/getVideoByTag")
    public Result<List<VideoInfoVO>> getVideoByTag(@RequestParam String tag) {
        System.out.println("获取到想要获取的标签：" + tag);
        return Result.ok(videoMetadataService.getPhysicalCultureVideo(tag));
    }
}

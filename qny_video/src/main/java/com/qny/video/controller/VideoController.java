package com.qny.video.controller;

import com.qny.video.domain.dto.VideoMetadataDTO;
import com.qny.common.domain.entity.Result;
import com.qny.video.domain.request.VideoMetadataRequest;
import com.qny.video.domain.vo.VideoInfoVO;
import com.qny.common.exception.VerifyException;
import com.qny.video.service.VideoMetadataService;
import com.qny.common.utils.ValidationUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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
    public Result getVideo() {
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
    public Result save(@Valid @RequestBody VideoMetadataRequest vmr, BindingResult bindingResult) throws VerifyException {
        ValidationUtil.check(bindingResult);
        VideoMetadataDTO vmd = new VideoMetadataDTO();
        BeanUtils.copyProperties(vmr, vmd);
        boolean flag = videoMetadataService.save(vmd);
        if (!flag){
            return Result.fail();
        }
        return Result.ok();
    }
}

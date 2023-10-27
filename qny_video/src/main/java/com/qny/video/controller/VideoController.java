package com.qny.video.controller;

import com.qny.video.domain.dto.VideoMetadataDTO;
import com.qny.video.domain.entity.Result;
import com.qny.video.domain.model.VideoMetadataModel;
import com.qny.video.domain.request.VideoMetadataRequest;
import com.qny.video.exception.VerifyException;
import com.qny.video.service.VideoMetadataService;
import com.qny.video.utils.ValidationUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
        List<VideoMetadataModel> list = videoMetadataService.list();
        List<String> paths = list.stream().map(VideoMetadataModel::getFilePath).collect(Collectors.toList());
        Random random = new Random();
        int index = random.nextInt(paths.size());
        return Result.ok(paths.get(index));
    }

    /**
     * 报错视频元信息
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

package com.qny.video.controller;

import com.qny.video.domain.entity.VideoMetadata;
import com.qny.video.service.VideoMetadataService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
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
    @GetMapping("/randomVideo")
    public String getVideo() {
        List<VideoMetadata> list = videoMetadataService.list();
        List<String> paths = list.stream().map(VideoMetadata::getFilePath).collect(Collectors.toList());
        Random random = new Random();
        int index = random.nextInt(paths.size());
        return paths.get(index);
    }
}

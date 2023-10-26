package com.qny.video.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author ttpfx
 * @since 2023/10/25
 */
@RestController
@RequestMapping("/video")
public class VideoController {

    @GetMapping("/getVideo")
    public void getVideo(HttpServletResponse response) throws IOException {
        response.setContentType("application/x-mpegURL");
        Resource resource = new ClassPathResource("segment/output.m3u8");
        InputStream inputStream = resource.getInputStream();
        StreamUtils.copy(inputStream, response.getOutputStream());
    }
}

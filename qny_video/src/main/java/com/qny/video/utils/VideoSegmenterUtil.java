package com.qny.video.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author ttpfx
 * @since 2023/10/25
 */
public class VideoSegmenterUtil {
    /**
     * 使用FFmpeg对视频文件进行分片处理
     * @param inputVideoPath 输入视频文件路径
     * @param outputFolderPath 输出文件夹路径
     * @param segmentDuration 分段时长（秒）
     */
    public static void segmentVideo(String inputVideoPath, String outputFolderPath, int segmentDuration) {
        // 构建FFmpeg命令
        String command = String.format("ffmpeg -i %s -c:v copy -c:a copy -hls_time %d -hls_list_size 0 %s\\output.m3u8",
                inputVideoPath, segmentDuration, outputFolderPath);

        System.out.println(command);
        try {
            // 创建进程构建器
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("cmd.exe", "/c", command); // 使用 cmd.exe 代替 bash

            // 启动进程
            Process process = processBuilder.start();

            // 读取命令行输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 等待进程完成
            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        segmentVideo("D:\\代码\\JAVA代码\\qny\\qny_video\\src\\main\\resources\\video\\英语.mp4","D:\\代码\\JAVA代码\\qny\\qny_video\\src\\main\\resources\\segment",5);
    }
}

package com.qny.video.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.qny.video.enumeration.VideoSortTag;
import com.qny.video.utils.VideoSortTagUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * VideoSortTagListDeserializer 是一个自定义的反序列化类，用于将逗号分隔的字符串反序列化为 VideoSortTag 枚举的列表。
 * 它继承了 Jackson 库的 JsonDeserializer 类，并重写了 deserialize 方法以提供自定义的反序列化逻辑。
 *
 * @author ttpfx
 * @since 2023/10/28
 */
public class VideoSortTagListDeserializer extends JsonDeserializer<List<VideoSortTag>> {

    /**
     * 此方法被重写以提供自定义的反序列化逻辑。
     * 它接受一个 JsonParser 和 DeserializationContext 作为参数，并返回一个 VideoSortTag 枚举的列表。
     * <p>
     * 方法从 JsonParser 中读取文本值，根据逗号进行分割，
     * 然后对分割数组中的每个标签，使用 VideoSortTagUtil.get 方法获取相应的 VideoSortTag 枚举。
     * 如果 VideoSortTag 枚举不为 null，则将其添加到标签列表中，然后返回该列表。
     *
     * @param jsonParser             用于读取 JSON 值的 JsonParser
     * @param deserializationContext 用于在反序列化期间提供任何上下文信息的 DeserializationContext
     * @return 一个 VideoSortTag 枚举的列表
     * @throws IOException 如果在反序列化过程中发生输入/输出错误
     */
    @Override
    public List<VideoSortTag> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        String[] tags = jsonParser.getText().split(",");  // 根据逗号分割文本值以获取单个标签
        List<VideoSortTag> tagList = new ArrayList<>();
        for (String tag : tags) {
            VideoSortTag videoSortTag = VideoSortTagUtil.get(tag);  // 获取每个标签的 VideoSortTag 枚举
            if (videoSortTag != null) tagList.add(videoSortTag);  // 如果 VideoSortTag 枚举不为 null，则将其添加到列表中
        }
        return tagList;  // 返回 VideoSortTag 枚举的列表
    }
}



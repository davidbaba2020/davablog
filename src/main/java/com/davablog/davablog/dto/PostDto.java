package com.davablog.davablog.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {
    private Long postId;
    private String title;
    private String content;
    private String description;
}

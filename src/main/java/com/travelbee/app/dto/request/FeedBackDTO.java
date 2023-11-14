package com.travelbee.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeedBackDTO {
    private String title;
    private String content;
    private String images;
    private String email;
}

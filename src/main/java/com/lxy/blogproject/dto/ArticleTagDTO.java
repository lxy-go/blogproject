package com.lxy.blogproject.dto;

import java.util.List;

public class ArticleTagDTO extends ArticleDTO {
    private List<String> tags;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "ArticleTagDTO{" +
                "tags=" + tags +
                '}';
    }
}

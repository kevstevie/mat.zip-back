package com.woowacourse.matzip.presentation.request;

import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReviewUpdateRequest {

    @NotNull(message = "리뷰 내용은 null이 들어올 수 없습니다.")
    private String content;
    private int rating;
    @NotNull(message = "리뷰 메뉴는 null이 들어올 수 없습니다.")
    private String menu;

    private ReviewUpdateRequest() {
    }

    public ReviewUpdateRequest(final String content, final int rating, final String menu) {
        this.content = content;
        this.rating = rating;
        this.menu = menu;
    }
}

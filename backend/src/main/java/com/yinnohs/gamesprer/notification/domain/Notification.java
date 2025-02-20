package com.yinnohs.gamesprer.notification.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Notification {
    private String id;
    private String title;
    private String message;
    private String type;
    private String UserId;
    private String gameToFindTitle;
}

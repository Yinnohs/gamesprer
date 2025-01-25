package com.yinnohs.gamesprer.games.domain;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game {
    private String id;
    private String url;
    private String title;
    private BigDecimal price;
    private String pageName;
    private String imageUrl;
    private LocalDateTime scrapedAt;

}

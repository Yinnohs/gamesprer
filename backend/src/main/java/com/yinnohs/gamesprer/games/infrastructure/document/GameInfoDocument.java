package com.yinnohs.gamesprer.games.infrastructure.document;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameInfoDocument {
    @Id
    private String id;
    private String url;
    private String title;
    private BigDecimal price;
    private String pageName;
    private LocalDateTime scrapedAt;
}

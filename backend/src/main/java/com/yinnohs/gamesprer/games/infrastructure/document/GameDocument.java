package com.yinnohs.gamesprer.games.infrastructure.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("games")
public class GameDocument {
    @Id
    private String id;
    private String url;
    private String title;
    private BigDecimal price;
    private String pageName;
    private String imageUrl;
    private LocalDateTime scrapedAt;
}

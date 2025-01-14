package com.yinnohs.gamesprer.games.infrastructure.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.data.mongodb.core.mapping.FieldType.DECIMAL128;

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
    @Field(targetType = DECIMAL128)
    private BigDecimal price;
    private String pageName;
    private LocalDateTime scrapedAt;
}

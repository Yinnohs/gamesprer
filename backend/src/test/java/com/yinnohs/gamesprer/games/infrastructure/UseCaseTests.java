package com.yinnohs.gamesprer.games.infrastructure;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MongoDBContainer;

import com.yinnohs.gamesprer.games.application.usecases.FindGamesBySimilarTitle;
import com.yinnohs.gamesprer.games.domain.model.Game;
import com.yinnohs.gamesprer.games.domain.ports.in.GameService;
import com.yinnohs.gamesprer.games.domain.ports.out.ApiService;

@SpringBootTest
public class UseCaseTests {

    private final String TEST_URL = "https://www.instant-gaming.com/pt/1234-comprar-key-gog-the-witcher-3-wild-hunt-game-of-the-year-edition/";
    private final String TEST_IMAGE_URL = "https://www.instant-gaming.com/pt/1234-comprar-key-gog-the-witcher-3-wild-hunt-game-of-the-year-edition/";
    private final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");
    
    @Autowired
    private ApiService apiService;
    private GameService mockGameService = Mockito.mock(GameService.class);;
    
    

    @BeforeAll
    public void setUp() {
        mongoDBContainer.start();
        String connectioString = mongoDBContainer.getConnectionString();
        System.setProperty("spring.data.mongodb.uri", connectioString);
        
    }

    @Test
    public void should_return_all_games_when_passed_similar_title() {
        //given
        var games = createListToTest();
        var gameTitleToFind = "witcher";
        int expectedGamesSize = games.size();
        FindGamesBySimilarTitle findGamesBySimilarTitleUseCase = new FindGamesBySimilarTitle(mockGameService, apiService);
        Mockito.when(mockGameService.findGameBySimilarTitle(gameTitleToFind)).thenReturn(games);

        //when
        var result = findGamesBySimilarTitleUseCase.execute(gameTitleToFind);

        //then 
        Assertions.assertEquals(expectedGamesSize, result.size());
    }

    private Game createGame(String title, String url, String imageUrl) {
        return Game.builder()
            .id(UUID.randomUUID().toString())
            .title(title)
            .price(BigDecimal.TEN)
            .url(url)
            .imageUrl(url)
            .pageName("Instant Gaming")
            .scrapedAt(LocalDateTime.now())
            .build();
    }


    private List<Game> createListToTest(){
        return List.of(
            createGame("The Witcher 2", TEST_URL, TEST_IMAGE_URL),
            createGame("The Witcher 4", TEST_URL, TEST_IMAGE_URL),
            createGame("The Witch", TEST_URL, TEST_IMAGE_URL),
            createGame("The Witcher 1 Olivus", TEST_URL, TEST_IMAGE_URL),
            createGame("The Tintwitch Witcher 1", TEST_URL, TEST_IMAGE_URL)
        );
    }
    
}

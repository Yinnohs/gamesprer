package com.yinnohs.gamesprer.games.infrastructure;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yinnohs.gamesprer.games.application.usecases.FindGamesBySimilarTitle;
import com.yinnohs.gamesprer.games.domain.model.Game;
import com.yinnohs.gamesprer.games.domain.ports.in.GameService;
import com.yinnohs.gamesprer.games.domain.ports.out.ApiService;
import com.yinnohs.gamesprer.games.infrastructure.mapper.GameMapper;
import com.yinnohs.gamesprer.games.infrastructure.repository.GameDocumentRepository;

@SpringBootTest
public class UseCaseTests {

    private final String TEST_URL = "https://www.instant-gaming.com/pt/1234-comprar-key-gog-the-witcher-3-wild-hunt-game-of-the-year-edition/";
    private final String TEST_IMAGE_URL = "https://www.instant-gaming.com/pt/1234-comprar-key-gog-the-witcher-3-wild-hunt-game-of-the-year-edition/";

    
    private ApiService apiService = Mockito.mock(ApiService.class); ;
    @Autowired
    private GameService gameService;
    @Autowired
    private GameMapper mapper;
    @Autowired
    private GameDocumentRepository gameDocumentRepository; 
    
    

    @BeforeEach
    public void setUp() {
        insertGames();
    }

    @AfterEach
    public void tearDown() {
        deleteAllGames();
    }

    @Test
    public void should_return_all_games_when_passed_similar_title() {
        //given
        var gameTitleToFind = "witch";
        int expectedGamesSize = 5;
        FindGamesBySimilarTitle findGamesBySimilarTitleUseCase = new FindGamesBySimilarTitle(gameService, apiService);
        Mockito.when(apiService.signalScraper(gameTitleToFind)).thenReturn("ok");
        //when
        var result = findGamesBySimilarTitleUseCase.execute(gameTitleToFind);

        //then 
        Assertions.assertEquals(expectedGamesSize, result.size());
    }

    private Game createGame(String title, String url, String imageUrl) {
        return Game.builder()
            .id(UUID.randomUUID().toString())
            .title(title)
            .price(BigDecimal.valueOf(200))
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
            createGame("The Tint witch cherioss 1", TEST_URL, TEST_IMAGE_URL)
        );
    }

    private void  insertGames(){
        var games = createListToTest().stream().map(mapper::toDocument).toList();
        gameDocumentRepository.saveAll(games);
    }

    private void deleteAllGames(){
        gameDocumentRepository.deleteAll();
    }
    
}

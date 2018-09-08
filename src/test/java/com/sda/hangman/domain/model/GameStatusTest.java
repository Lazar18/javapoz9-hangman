package com.sda.hangman.domain.model;

import com.sda.hangman.domain.model.GameStatus.GameStatusHelper;
import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@RunWith(Enclosed.class)
public class GameStatusTest {

    @Test
    public void isFinishedShouldReturnTrueWhenFailureAttemptsEqualsMaxAttempts() {
        //given
        GameStatus gameStatus = new GameStatus("mmm", "Anna", 1);

        //when
        gameStatus.setFailedAttempts(1);

        //then
        Assert.assertFalse(gameStatus.isGameFinished());
    }

    @Test
    public void isFinishedShouldReturnFalseWhenMaxAttemptsIsBiggerThanFailureAttempts() {
        //given
        GameStatus gameStatus = new GameStatus("mmm", "Anna", 3);

        //when
        gameStatus.setFailedAttempts(1);

        //then
        Assert.assertFalse(gameStatus.isGameFinished());

    }

    @Test
    public void isFinishedShouldReturnTrueWhenAllLettersAreGuessed() {
        //given
        GameStatus gameStatus = new GameStatus("mmm", "Anna", 2);

        //when
        gameStatus.setPhraseState("Anna".chars().mapToObj(e -> (char) e).toArray(Character[]::new));

        //then
        Assert.assertTrue(gameStatus.isGameFinished());

    }

    @Test
    public void getCurrentStatePhraseWithLeftAttemptsShouldReturnTextWithUnderscoresForEmptyPhraseState() {
        //given
        GameStatus gameStatus = new GameStatus("mmm", "Anna", 5);

        //when
        String state = gameStatus.getCurrentStatePhraseWithLeftAttempts();

        //then
        Assert.assertEquals("____ (5)", state);

    }


    public static class GameStatusHelperTest {

        @Test
        public void shouldReturnArrayWithNullValuesForOneWordPhrase() {
            //given
            GameStatusHelper gameStatusHelper = new GameStatusHelper();

            //when
            Character[] phraseState = gameStatusHelper.preparePhraseState("Anna");

            //then
            assertThat(phraseState).allMatch((e) -> e == null);
//        for (int i = 0; i < phraseState.length; i++) {    // to samo co
//            Assert.assertEquals(null, phraseState[i]);    // wyżej lamddą
//        }
        }

        @Test
        public void shouldReturnArrayWithNullValuesAndSpecialCharsForMultiWordPhrase() {
            //given
            GameStatusHelper gameStatusHelper = new GameStatusHelper();

            //when
            Character[] phraseState = gameStatusHelper.preparePhraseState("Anna ma-kota");

            //then
            assertThat(phraseState).containsOnly(null, ' ', '-');
            Assert.assertEquals((Character) ' ', phraseState[4]);
            Assert.assertEquals((Character) '-', phraseState[7]);
        }
    }
}

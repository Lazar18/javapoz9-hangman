package com.sda.hangman.domain.model;

import com.sda.hangman.domain.model.GameStatus.GameStatusHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(Enclosed.class)
public class GameStatusTest {

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

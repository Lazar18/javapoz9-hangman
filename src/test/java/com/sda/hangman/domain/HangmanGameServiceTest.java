package com.sda.hangman.domain;

import com.sda.hangman.domain.model.GameStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class HangmanGameServiceTest {

    @Before
    public void init() {
        this.hangmanGameService = new HangmanGameService();
    }

    private HangmanGameService hangmanGameService;

    @Test
    public void shouldReturnArrayWithCharacterIndexes() {

        //given


        //when
        List<Integer> result = hangmanGameService.performCharacter('a', "Anna");

        //then
        Assert.assertEquals(2, result.size());
        Assert.assertEquals((Integer) 0, result.get(0));
        Assert.assertEquals((Integer) 3, result.get(1));
    }

    @Test
    public void shouldReturnEmptyArrayForNonExistingCharacterInPhrase() {
        //given


        //when
        List<Integer> result = hangmanGameService.performCharacter('c', "Anna");

        //then
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void shouldReturnArrayCharacterWithIndexesFromPhraseContainingWhiteSpaces() {
        //given


        //when
        List<Integer> result = hangmanGameService.performCharacter('k', "Anna ma kota");

        //then
        Assert.assertEquals(1, result.size());
        Assert.assertEquals((Integer) 8, result.get(0));
    }

    @Test
    public void processNextLetter_should_update_characterState_when_there_is_letter_in_phrase() {
        //given
        GameStatus gameStatus = new GameStatus("ggg", "Anna");
        Character[] expected = {'A', null, null, 'a'};

        //when
        hangmanGameService.processNextLetter('a', gameStatus);

        //then
        Assert.assertArrayEquals(expected, gameStatus.getPhraseState());
    }

    @Test
    public void processNextLetter_should_not_update_characterState_when_there_is_no_letter_in_phrase() {
        //given
        GameStatus gameStatus = new GameStatus("ggg", "Anna");
        Character[] expected = {null, null, null, null};

        //when
        hangmanGameService.processNextLetter('z', gameStatus);

        //then
        Assert.assertArrayEquals(expected, gameStatus.getPhraseState());

    }

    @Test
    public void processNextLetter_should_update_successAttempts_when_there_is_letter_in_phrase() {
        //given
        GameStatus gameStatus = new GameStatus("ggg", "Anna");
        Character[] expected = {'A', null, null, 'a'};

        //when
        hangmanGameService.processNextLetter('a', gameStatus);

        //then
        Assert.assertEquals((Integer) 1, gameStatus.getSuccessAttempts());
    }

    @Test
    public void processNextLetter_should_update_failureAttempts_when_there_is_no_letter_in_phrase() {
        //given
        GameStatus gameStatus = new GameStatus("ggg", "Anna");

        //when
        hangmanGameService.processNextLetter('z', gameStatus);

        //then
        Assert.assertEquals((Integer) 1, gameStatus.getFailedAttempts());
    }

    @Test
    public void processNextLetter_should_update_history_for_new_letter() {
        //given
        GameStatus gameStatus = new GameStatus("ggg", "Anna");

        //when
        hangmanGameService.processNextLetter('a', gameStatus);

        //then
        Assert.assertEquals(Arrays.asList('a'), gameStatus.getHistory());
    }
}
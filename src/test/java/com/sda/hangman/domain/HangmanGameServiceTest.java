package com.sda.hangman.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

}
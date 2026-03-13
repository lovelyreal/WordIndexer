package util;

import java.util.*;
import java.util.regex.Pattern;

public interface RussianTokenizerSettings {
    List<String> ENDINGS = Arrays.asList("ами","ями","ыми","ими",
            "ого","его",
            "ому","ему",
            "ах","ях",
            "ия","ие","ий",
            "ый","ой",
            "ая","яя",
            "ое","ее",
            "ам","ям",
            "ом","ем",
            "ах","ях",
            "ы","и","а","е","о","у","я");
    List<String> STOP_WORDS = Arrays.asList(
            "и","в","во","не","что","он",
            "на","с","со","как","а",
            "то","все","она","так","его",
            "но","да","ты","к","у","же");
// Regex ищет совпадения из разряда "слово.." и "слово-слово" дефис включается
    Pattern WORD_PATTERN = Pattern.compile("[а-яА-ЯёЁ]+(?:-[а-яА-ЯёЁ]+)?");
}

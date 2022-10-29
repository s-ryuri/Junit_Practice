package Test.unit;

import Test.unit.lotto.LottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class LottoTest {



    @Test
    @DisplayName("로또 번호 갯수 테스트")
    void LottoNumberTest(){

        //given
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        int price = 1000;

        //when
        List<Integer> lottoNumber = lottoNumberGenerator.generate(price);

        //then
        assertThat(lottoNumber.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("로또 범위 갯수 테스트")
    void lottNumberRangeTest(){

        //given
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        int price = 1000;

        //when
        List<Integer> lottoNumbers = lottoNumberGenerator.generate(1000);

        assertThat(lottoNumbers.stream().allMatch(n -> n >=1 && n <= 45)).isTrue();
    }

    @Test
    @DisplayName("1000이 아닌 값을 넣을 때 예외 테스트")
    void lottoPriceTest(){

        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        int price = 500;



        assertThrows(RuntimeException.class, () -> lottoNumberGenerator.generate(price));
    }
}

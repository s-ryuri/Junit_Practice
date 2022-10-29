package Test.unit;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class LottoNumberGenerator {

    public List<Integer> generate(final int money){
        if (!isValidMoney(money)) {
            throw new RuntimeException("잘못된 금액입니다.");
        }

        return generate();
    }

    private boolean isValidMoney(int money) {
        return money == 1000;
    }

    private List<Integer> generate(){
        return new Random()
            .ints(1,45 + 1)
            .distinct()
            .limit(6)
            .boxed()
            .collect(Collectors.toList());
    }
}


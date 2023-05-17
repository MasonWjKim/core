package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
//  이름 그대로 생성자를 통해서 의존 관계를 주입 받는 방법이다.
//  지금까지 우리가 진행했던 방법이 바로 생성자 주입이다.
//  생성자 호출시점에 딱 1번만 호출되는 것이 보장된다.
//  불변, 필수 의존관계에 사용
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}

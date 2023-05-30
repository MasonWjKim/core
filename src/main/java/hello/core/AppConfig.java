package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class  AppConfig {
    // SRP 단일 책임 원칙 - 구현 객체를 생성하고 연결하는 책임은 AppConfig가 담당, 클라이언트 객체는 실행하는 책임만 담당
    // DIP 의존관계 역전 원칙 - 추상화의존, AppConfig가 객체 인스턴스를 클라이언트 코드 대신 생성하여 의존관계 주입
    // OCP 소프트웨어 요소는 확장에는 열려있으나, 변경에는 닫혀 있어야 한다 - 소프트웨어 요소를 새롭게 확장해도 사용 영역의 변경은 닫혀있다.

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()

    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository

    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService

    // 왜 한 번만 호출되었나..?? -> spring의 Singleton 보장

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); // 생성자 주입
    }

    @Bean
    public static MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}

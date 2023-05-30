package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private MemberRepository memberRepository;
    // @Autowired private MemberRepository memberRepository;
    // 필드주입 -> 외부에서 변경 불가능, DI프레임워크없이는 아무것도 하지 못함 ...>> 사용X
    private DiscountPolicy discountPolicy;


    @Autowired(required = false) // 선택적 수정자 주입**
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    // 수정자 주입(setter 주입) - 1. Spring Bean 등록 2. 연관관계 자동주입 (Autowired)
    // 선택**, 변경* 가능성이 있는 의존관계에 사용
    // 자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법이다.
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    @Autowired
    // 생성자 주입 -> Spring Bean을 등록하면서 연관관계를 같이 주입한다. (수정자 주입과 다르게 LifeCycle 1,2 동시 진행)
    // 중요!! 생성자가 하나만 있으면 Autowired를 생략해도 된다.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

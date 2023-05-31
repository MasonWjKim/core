package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    /*정리
    - 생성자 주입 방식을 선택하는 이유는 여러가지가 있지만, 프레임워크에 의존하지 않고, 순수한 자바 언어의 특징을 잘 살리는 방법이기도하다.
    - 기본으로 생성자 주입을 사용하고, 필수 값이 아닌 경우에는 수정자 주입 방식을 옵션으로 부여하면 된다. 생성자 주입과 수정자 주입을 동시에 사용할 수 있다.
    - **항상 생성자 주입을 선택해라!!** 그리고 가끔 옵션이 필요하면 수정자 주입을 선택해라..!! (필드 주입은 절대XXXX)
    * */

    private final MemberRepository memberRepository; // final을 쓰면 초기화단계에서 값이 들어와야하므로 컴파일에서 오류를 잡을 수 있다.
    // @Autowired private MemberRepository memberRepository;
    // 필드주입 -> 외부에서 변경 불가능, DI프레임워크없이는 아무것도 하지 못함 ...>> 사용X
    private final DiscountPolicy discountPolicy;



//    @Autowired(required = false) // 선택적 수정자 주입**
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

//    @Autowired
//    // 수정자 주입(setter 주입) - 1. Spring Bean 등록 2. 연관관계 자동주입 (Autowired)
//    // 선택**, 변경* 가능성이 있는 의존관계에 사용
//    // 자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법이다.
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    @Autowired
    // 생성자 주입 -> Spring Bean을 등록하면서 연관관계를 같이 주입한다. (수정자 주입과 다르게 LifeCycle 1,2 동시 진행)
    // 중요!! 생성자가 하나만 있으면 Autowired를 생략해도 된다.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

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

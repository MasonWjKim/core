package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // basePackages = "hello.core.member",
        // basePackageClasses = AutoAppConfig.class,
        // 지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작위치가 된다.
        // 권장방법 - 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이다. (스프링부트 기본 제공)
        // ex) com.hello 같은 프로젝트 시작 루트에 두고 basePackages 지정을 생략하여 최상단부터 검색하도록 둔다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        // 같은 이름의 빈 존재하지만 테스트 성공 -> 중복될 경우 수동빈이 우선권을 가진다.
//        // Overriding bean definition for bean 'memoryMemberRepository' with a different definition
//        // 하지만 잘 사용하지않고 오버라이딩이 된다면 실수인 경우가 대부분이다.
//        // 중복상태로 CoreApplication 실행시 자동으로 disabled 된다 (~~ and overriding is disabled.) 기본설정

//    }
}

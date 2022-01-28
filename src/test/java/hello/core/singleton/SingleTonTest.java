package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;

class SingleTonTest {

    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    @Test
    void pureContainer() {
        // given
        AppConfig appConfig = new AppConfig();
        // when

        /*
            jvm 메모리에 객체가 계속 생성돼서 올라간다
            요청이 올 때 마다 객체가 생성이 되는데 tps가 5만이면 5만개가 생성된다. -> 비효율
            안으로 들어가면 호출되는 객체들도 더 생성이 된다. -> memberService 안의 memberRepository까지
         */
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        // then
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    @Test
    void singletonService() {
        // given
        SingletonService singletonService1 = SingletonService.getInstance();
        // when
        SingletonService singletonService2 = SingletonService.getInstance();
        // then
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        // same == 참조 비교 (동일 비교)
        // equal == 동등 비교
        assertThat(singletonService1).isSameAs(singletonService2);
    }

    @DisplayName("스프링 컨테이너와 싱글톤")
    @Test
    void springContainer() {
        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // when
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // then
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
}

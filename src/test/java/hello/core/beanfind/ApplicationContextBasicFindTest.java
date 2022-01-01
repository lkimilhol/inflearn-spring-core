package hello.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @DisplayName("빈 이름으로 조회")
    @Test
    void findBeanByName() {
        // given
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        // when
        // then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @DisplayName("이름 없이 타입으로만 조회")
    @Test
    void findBeanByType() {
        // given
        MemberService memberService = ac.getBean(MemberService.class);
        // when
        // then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @DisplayName("객체 타입으로 조회 - 역할에 의존해야 해서 좋은 코드가 아니다")
    @Test
    void findBeanByName2() {
        // given
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        // when
        // then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @DisplayName("빈 이름으로 조회X")
    @Test
    void findByNameX() {
        // given
        // when
        // then
        assertThatThrownBy(() -> {
            ac.getBean("xxxx", MemberServiceImpl.class);
        }).isInstanceOf(NoSuchBeanDefinitionException.class);

        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberServiceImpl.class)
        );
    }
}

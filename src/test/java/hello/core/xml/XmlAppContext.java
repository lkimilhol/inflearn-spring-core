package hello.core.xml;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import hello.core.member.MemberService;

class XmlAppContext {

    @Test
    void xmlAppContext() {
        // given
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        // when
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        // then
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}

package hello.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 중복 오류가 발생한다")
    @Test
    void findBeanByParentTypeDuplicate() {
        // given
        // when
        // then
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
    }

    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이르믈 지정하면 된다")
    @Test
    void findBeanParentTypeBeanName() {
        // given
        // when
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        // then
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @DisplayName("특정 하위 타입으로 조회 - 안 좋은 방법")
    @Test
    void findBeanBySubType() {
        // given
        // when
        RateDiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
        // then
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @DisplayName("부모 타입으로 모두 조회하기")
    @Test
    void findAllBeanByParentType() {
        // given
        // when
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        // then
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    @Test
    void findAllBeanByObjectType() {
        // given
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        // when
        for (String key : beansOfType.keySet()) {
            System.out.println("ket = " + key + "value = " + beansOfType.get(key));
        }
        // then
    }

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}

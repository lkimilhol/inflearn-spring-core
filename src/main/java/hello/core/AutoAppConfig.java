package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//@Component가 붙은걸 찾아서 빈으로 등록
@ComponentScan(
        // 지정하지 않으면 @ComponentScan이 달려있는 패키지 부터 아래로 다 뒤짐 (지정하지 않는게 권장하는 방법)
        // @SpringBootApplication 안에 @ComponentSanc이 있음
        // basePackages = "hello.core.member",
        // basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}

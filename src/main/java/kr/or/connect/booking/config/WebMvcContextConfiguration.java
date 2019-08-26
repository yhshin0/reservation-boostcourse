package kr.or.connect.booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import kr.or.connect.booking.interceptor.LogInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"kr.or.connect.booking.controller"})
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
    
    /* 
     * 웹 어플리케이션 루트와 클래스 패스에 어떤 jar에서라도 알려진 /META-INF/public-web-resources/ 경로 모두에서
     * 리소스를 제공하려면 다음과 같이 설정한다.
     * registry.addResourceHandler("/resources/**").addResourceLocations("/", "classpath:/META-INF/public-web-resources/");
     * 출처: https://blog.outsider.ne.kr/904
     * */
    registry.addResourceHandler("/tmp_upload/**").addResourceLocations("file:///c:/tmp_upload/").setCachePeriod(31556926);
    registry.addResourceHandler("/img/**").addResourceLocations("classpath:img/").setCachePeriod(31556926);
    registry.addResourceHandler("/img_map/**").addResourceLocations("classpath:img_map/").setCachePeriod(31556926);
    registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    registry.addResourceHandler("/font/**").addResourceLocations("/font/").setCachePeriod(31556926);
  }
  
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    System.out.println("call addViewControllers");
    registry.addViewController("/").setViewName("index");
  }

  @Bean
  public InternalResourceViewResolver getInternalResourceViewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
    return resolver;
  }
  
  @Bean
  public MultipartResolver multipartResolver() {
    org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver =
        new org.springframework.web.multipart.commons.CommonsMultipartResolver();
    multipartResolver.setMaxUploadSize(1024 * 1024 * 10); //10MB 제한
    return multipartResolver;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LogInterceptor());
  }
  
  
  
}

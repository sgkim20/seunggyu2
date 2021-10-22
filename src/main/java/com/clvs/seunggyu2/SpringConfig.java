package com.clvs.seunggyu2;

import com.clvs.seunggyu2.aop.TimeTraceAop;
import com.clvs.seunggyu2.repository.*;
import com.clvs.seunggyu2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // JPA 이전에 사용하던 변수
//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//
//        this.dataSource = dataSource;
//    }

    @Bean
    public MemberService memberService() {

        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
        // 스프링을 사용하는 이유: 객체지향 설계의 장점 -> 다형성 활용(인터페이스에 구현체를 변경할 수 있다)
        // 기존 코드(실제 애플리케이션 코드)는 수정하지 않고 SpringConfig에서 assembly(조립) 설정만으로 코드 수정이 가능하다.
        // -> 스프링의 DI(Dependencies Injection)
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }

//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }
}

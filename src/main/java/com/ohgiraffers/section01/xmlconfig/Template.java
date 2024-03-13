package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Template {

    /* 필기.
        SqlSessionFactory 는 어플리케이션이 실행되는 동안 존재해야하며
        어플리케이션이 실행되는 동안 여러 차례 SqlSessionFactory 를 다시 빌드하지 않는 것이
        가장 좋은 형태이다.
        어플리케이션 스코프로 관리하기 위한 가장 간단한 방법은 싱글톤 패턴을 이용하는 것이다.
     */

    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession(){

        if(sqlSessionFactory == null){
            String resource = "mybatis-config.xml";
            try {
                InputStream inputStream = Resources.getResourceAsStream(resource);          //필기 인터페이스 이용해서 Resource 파일 로드하기
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);      // 필기 Resource 안에 정보로 팩토리 만들기(build)에 Resource

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        SqlSession sqlSession = sqlSessionFactory.openSession(false);                   // 필기 팩토리안에 정보로 세션열기

        System.out.println("SqlSessionFactory 의 hashcode() : " + sqlSessionFactory.hashCode());
        System.out.println("SqlSession의 hashCode() : " + sqlSession.hashCode());




        return sqlSession;

    }
}
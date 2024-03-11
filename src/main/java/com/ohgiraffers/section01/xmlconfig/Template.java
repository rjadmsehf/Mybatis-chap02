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
                InputStream inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        SqlSession sqlSession = sqlSessionFactory.openSession(false);

        System.out.println("SqlSessionFactory 의 hashcode() : " + sqlSessionFactory.hashCode());
        System.out.println("SqlSession의 hashCode() : " + sqlSession.hashCode());

        return sqlSession;

    }
}
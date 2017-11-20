package com.finerio.ios.aop

import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.stereotype.Component


@Component
@Aspect
class UserServiceCreateUser {

  final static Logger log = LoggerFactory.getLogger(
      'com.finerio.ios.services.UserServiceCreateUser' )

  @Pointcut(
    value='execution(com.finerio.ios.database.User com.finerio.ios.services.UserService.createUser(..)) && bean(userService) && args(email)',
    argNames='email'
  )
  public void createUser( String email ) {}

  @Before('createUser(email)')
  void before( String email ) {
    log.info( "<< email: {}", email )
  }

  @AfterReturning(
    pointcut='createUser(String)',
    returning='result'
  )
  void afterReturning( String result ) {
    log.info( ">> result: {}", result )
  }

  @AfterThrowing(
    pointcut='createUser(String)',
    throwing='e'
  )
  void afterThrowing( Exception e ) {
    log.info( "XX ${e.class.simpleName} - ${e.message}" )
  }

}

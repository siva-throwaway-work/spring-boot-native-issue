# payment-service

```shell
# Build GraalVM Native Image
$ ./mvnw -Pnative spring-boot:build-image -Dspring-boot.build-image.imageName=payment-service-native

# Start application
$ docker-compose -f docker-compose.yml -f docker-compose-app.yml up -d 

# test
curl --location --request POST 'http://localhost:8080/api/payments/validate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "cardNumber": "1111222233334444",
    "cvv": "123",
    "expiryMonth": 2,
    "expiryYear": 2030
}'
```

This API call fails with following error:

```shell
ERROR 1 --- [nio-8080-exec-2] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: java.lang.NullPointerException] with root cause
java.lang.NullPointerException: null
	at org.springframework.aop.framework.AdvisedSupport$MethodCacheKey.<init>(AdvisedSupport.java:578) ~[com.sivalabs.bookstore.payments.PaymentServiceApplication:6.0.3]
	at org.springframework.aop.framework.AdvisedSupport.getInterceptorsAndDynamicInterceptionAdvice(AdvisedSupport.java:470) ~[com.sivalabs.bookstore.payments.PaymentServiceApplication:6.0.3]
	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:689) ~[na:na]
	at com.sivalabs.bookstore.payments.domain.CreditCardRepository$$SpringCGLIB$$0.findByCardNumber(<generated>) ~[com.sivalabs.bookstore.payments.PaymentServiceApplication:na]
	at com.sivalabs.bookstore.payments.domain.PaymentService.validate(PaymentService.java:21) ~[com.sivalabs.bookstore.payments.PaymentServiceApplication:na]
	at java.base@17.0.5/java.lang.reflect.Method.invoke(Method.java:568) ~[com.sivalabs.bookstore.payments.PaymentServiceApplication:na]
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:343) ~[na:na]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:196) ~[com.sivalabs.bookstore.payments.PaymentServiceApplication:6.0.3]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163) ~[com.sivalabs.bookstore.payments.PaymentServiceApplication:6.0.3]
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:752) ~[na:na]
	at org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:123) ~[na:na]
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:388) ~[com.sivalabs.bookstore.payments.PaymentServiceApplication:6.0.3]
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119) ~[com.sivalabs.bookstore.payments.PaymentServiceApplication:6.0.3]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[com.sivalabs.bookstore.payments.PaymentServiceApplication:6.0.3]
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:752) ~[na:na]
	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:703) ~[na:na]
	at com.sivalabs.bookstore.payments.domain.PaymentService$$SpringCGLIB$$0.validate(<generated>) ~[com.sivalabs.bookstore.payments.PaymentServiceApplication:na]
	at com.sivalabs.bookstore.payments.api.PaymentController.validate(PaymentController.java:23) ~[com.sivalabs.bookstore.payments.PaymentServiceApplication:na]
	at java.base@17.0.5/java.lang.reflect.Method.invoke(Method.java:568) ~[com.sivalabs.bookstore.payments.PaymentServiceApplication:na]
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:207) ~[com.sivalabs.bookstore.payments.PaymentServiceApplication:6.0.3]
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:152) ~[com.sivalabs.bookstore.payments.PaymentServiceApplication:6.0.3]
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117) ~[com.sivalabs.bookstore.payments.PaymentServiceApplication:6.0.3]
```

**Fix:** Make CreditCardRepository.findByCardNumber() method `public`.
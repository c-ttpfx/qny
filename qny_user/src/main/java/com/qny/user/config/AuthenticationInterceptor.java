// package com.qny.user.config;
//
// import com.auth0.jwt.JWT;
// import com.auth0.jwt.JWTVerifier;
// import com.auth0.jwt.algorithms.Algorithm;
// import com.auth0.jwt.exceptions.JWTDecodeException;
// import com.auth0.jwt.exceptions.JWTVerificationException;
// import com.qny.user.annotation.PassToken;
// import com.qny.user.annotation.UserLoginToken;
// import com.qny.user.entity.User;
// import org.springframework.web.method.HandlerMethod;
// import org.springframework.web.servlet.HandlerInterceptor;
// import org.springframework.web.servlet.ModelAndView;
//
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.lang.reflect.Method;
//
// /**
//  * @author Knight
//  * @since 2023/10/26
//  * 拦截器: 获取 token 并验证 token
//  **/
//
// public class AuthenticationInterceptor implements HandlerInterceptor {
//
//     @Override
//     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//         String token = request.getHeader("token");// 从 http 请求头中取出 token
//         // 如果不是映射到方法直接通过
//         if(!(handler instanceof HandlerMethod)){
//             return true;
//         }
//         HandlerMethod handlerMethod=(HandlerMethod)handler;
//         Method method=handlerMethod.getMethod();
//         //检查是否有passToken注释，有则跳过认证
//         if (method.isAnnotationPresent(PassToken.class)) {
//             PassToken passToken = method.getAnnotation(PassToken.class);
//             if (passToken.required()) {
//                 return true;
//             }
//         }
//         //检查有没有需要用户权限的注解
//         if (method.isAnnotationPresent(UserLoginToken.class)) {
//             UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
//             if (userLoginToken.required()) {
//                 // 执行认证
//                 if (token == null) {
//                     throw new RuntimeException("无token，请重新登录");
//                 }
//                 // 获取 token 中的 user id
//                 String userId;
//                 try {
//                     userId = JWT.decode(token).getAudience().get(0);
//                 } catch (JWTDecodeException j) {
//                     throw new RuntimeException("401");
//                 }
//                 User user = new User(1L, "admin", "123");
//                 // 验证 token
//                 JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
//                 try {
//                     jwtVerifier.verify(token);
//                 } catch (JWTVerificationException e) {
//                     throw new RuntimeException("401");
//                 }
//                 return true;
//             }
//         }
//         return true;
//     }
//
//     @Override
//     public void postHandle(HttpServletRequest httpServletRequest,
//                            HttpServletResponse httpServletResponse,
//                            Object o, ModelAndView modelAndView) throws Exception {}
//     @Override
//     public void afterCompletion(HttpServletRequest httpServletRequest,
//                                 HttpServletResponse httpServletResponse,
//                                 Object o, Exception e) throws Exception {}
// }

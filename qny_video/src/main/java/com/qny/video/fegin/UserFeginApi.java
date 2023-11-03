package com.qny.video.fegin;

import com.qny.common.domain.entity.Result;
import com.qny.common.domain.model.UserModel;
import com.qny.video.fegin.excp.UserFeginServiceFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author ttpfx
 * @since 2023/11/1
 */
@FeignClient(value = "userService",path = "/user",fallbackFactory = UserFeginServiceFallBackFactory.class)
@Component
public interface UserFeginApi {

    /**
     * 通过用户id查询User
     * @param userId 用户id
     * @return User
     */
    @GetMapping("/getUserById")
    Result<UserModel> getUserById(@RequestParam("userId") Long userId);


    /**
     * 通过用户ids查询Users
     *
     * @param userIds 用户id集合
     * @return  List<UserModel>
     */
    @GetMapping("/getUserByIds")
    Result<List<UserModel>> getUserByIds(@RequestParam("userIds") Long[] userIds);
}

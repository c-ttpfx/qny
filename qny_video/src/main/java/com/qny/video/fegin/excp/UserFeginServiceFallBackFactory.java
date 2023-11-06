package com.qny.video.fegin.excp;

import com.qny.common.domain.entity.Result;
import com.qny.common.domain.model.UserModel;
import com.qny.video.fegin.UserFeginApi;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * FeignClient 调用异常工厂配置类
 * @author ttpfx
 * @since 2023/11/1
 */
@Component
public class UserFeginServiceFallBackFactory implements FallbackFactory<UserFeginApi> {
    @Override
    public UserFeginApi create(Throwable throwable) {
        return new UserFeginApi() {
            @Override
            public Result<UserModel> getUserById(Long userId) {
                return Result.fail();
            }

            @Override
            public Result<List<UserModel>> getUserByIds(Long[] userIds) {
                return Result.fail();
            }
        };
    }
}

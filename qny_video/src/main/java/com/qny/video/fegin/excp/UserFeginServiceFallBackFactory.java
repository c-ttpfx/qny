package com.qny.video.fegin.excp;

import com.qny.video.domain.entity.Result;
import com.qny.video.domain.model.UserModel;
import com.qny.video.fegin.UserFeginApi;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
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
                return null;
            }

            @Override
            public Result<List<UserModel>> getUserByIds(Long[] userIds) {
                return null;
            }
        };
    }
}

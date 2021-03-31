package com.honeysense.magpie.paymax.runner;

import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.user.entity.User;
import com.honeysense.magpie.user.entity.UserRole;
import com.honeysense.magpie.user.entity.refer.UserRefer;
import com.honeysense.magpie.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChannelRunner implements ApplicationRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments args) {
        MagpiePage<User> userMagpiePage = userService.findAll(MagpiePageRequest.builder().page(0).pageSize(1).build());
        if (userMagpiePage.getCurrentPageSize() != 0) {
            log.info("默认管理员账户已存在");
            return;
        }

        UserRefer userRefer = new UserRefer();
        userRefer.setIp("127.0.0.1");
        userRefer.setUserAgent("magpie");

        User user = userService.insertName("zhouyong", "123456", userRefer);
        userService.updateRole(user.getId(), UserRole.ADMIN);
        log.info("添加默认管理员 zhouyong。");
    }
}

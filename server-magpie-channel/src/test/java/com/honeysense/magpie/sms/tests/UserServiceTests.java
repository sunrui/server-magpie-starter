package com.honeysense.magpie.sms.tests;

import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.user.entity.User;
import com.honeysense.magpie.user.entity.UserThird;
import com.honeysense.magpie.user.entity.UserRelation;
import com.honeysense.magpie.user.entity.refer.UserRefer;
import com.honeysense.magpie.user.service.UserRelationService;
import com.honeysense.magpie.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
@Slf4j
public class UserServiceTests {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRelationService userRelationService;

    @Test
    public void testUser() {
        User user = User.builder()
                .name("sunrui")
                .phone("15068860507")
                .password("password")
                .build();

        user.dump();

        MagpiePage<User> userMagpiePage = userService.findAll(new MagpiePageRequest(0, 9));
        for (User one : userMagpiePage.getElements()) {
            one.dump();

            userService.deleteById(one.getId());
        }

        userService.save(user);

        Long userId = null;

        userMagpiePage = userService.findAll(new MagpiePageRequest(0, 9));
        for (User one : userMagpiePage.getElements()) {
            one.dump();

            one.setName("sunrui");
            userId = one.getId();

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            userService.save(one);
        }

        Assert.isTrue(userId != null, "userId is null");

        user = userService.findById(userId);
        user.dump();
    }

    @Test
    void testUserRegister() {
        UserRefer userRefer = new UserRefer();
        userRefer.setIp("127.0.0.1");
        userRefer.setUserAgent("ua");

        User user = userService.insertName("name2", userRefer, null);
        user.dump();

        user = userService.insertOAuth(UserThird.Type.WECHAT, "appId", "openId", userRefer, null);
        user.dump();

        user = userService.insertPhone("15068860057", userRefer, null);
        user.dump();
    }

    @Test
    void testAll() {
        MagpiePage<User> userMagpiePage = userService.findAll(new MagpiePageRequest(0, 99));
        for (User user : userMagpiePage.getElements()) {
            user.dump();
        }

        MagpiePage<UserRelation> userReferMagpiePage = userRelationService.findAll(new MagpiePageRequest(0, 99));
        for (UserRelation userRelation : userReferMagpiePage.getElements()) {
            userRelation.dump();
        }
    }
}

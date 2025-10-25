package com.ruoyi.device.api;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.device.domain.DeviceUserRegister;
import com.ruoyi.device.service.IDeviceUserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Anonymous
@RestController
@RequestMapping("/wechat")
public class WxUserController {
    @Autowired
    private IDeviceUserRegisterService deviceUserRegisterService;


    /**
     * 小程序用户注册接口
     * @param userRegister
     * @return
     */
//2、编写注册方法，接受小程序传来的body体（regDept、regCode、regName、regMobile、regPassword）

    @PostMapping("/register")
    public AjaxResult register(@RequestBody DeviceUserRegister userRegister) {
           List<DeviceUserRegister> deviceUserRegisters = deviceUserRegisterService.selectDeviceUserRegisterList(userRegister);
        //4、判断
//如果没有此学号即注册成功，输出注册成功！
        if (deviceUserRegisters == null || deviceUserRegisters.size() == 0) {
            // 注册前，需要设置此用户为待审核状态
            userRegister.setRegStatus(0);
            // 注册前，需要为此用户的密码进行加密
            userRegister.setRegPassword(SecurityUtils.encryptPassword(userRegister.getRegPassword()));
            // 注册成功后，判断注册的返回值，插入一行，返回值为int状态，为
          int result = deviceUserRegisterService.insertDeviceUserRegister(userRegister);
            return result == 1? AjaxResult.success("注册成功！") : AjaxResult.error("注册失败！");
        } else {
            //如果有此学号即注册失败，输出 该学号或工号已被注册！
            return AjaxResult.error(userRegister.getRegCode() + "该学号或工号已被注册！");
        }
    }

/**
 * 小程序登录接口
 * @param code
 * @param userName
 * @param password
 * @return
 */
@PostMapping("/login")
public AjaxResult login(String code, String userName, String password) {
    //3、需要根据传过来的用户名，去数据库中查找数据，看看有没有此用户
    DeviceUserRegister register = new DeviceUserRegister();
    register.setRegName(userName);
    List<DeviceUserRegister> deviceUserRegisters = deviceUserRegisterService.selectDeviceUserRegisterList(register);
    //4、如果没有查到数据，或者查到多个数据，输出 学号或者密码错误
    if (deviceUserRegisters == null || deviceUserRegisters.size() != 1) {
        return AjaxResult.error("学号或者密码错误");
    } else {
        //5、查到数据，需要匹配密码是否一致否则输出 学号或者工号密码错误！
        register = deviceUserRegisters.get(0);
        if (register.getRegPassword() != null && SecurityUtils.matchesPassword(password, register.getRegPassword())) {
            //6、判断审核状态，如果审核状态为null为账号异常，1为登录成功，0为未审核，2为审核未通过
            if (register.getRegStatus() == null) {
                return AjaxResult.error("账号异常，请联系管理员！");
            } else if (register.getRegStatus() == 1) {
                register.setRegPassword("");
                return AjaxResult.success("登录成功", register);
            } else if (register.getRegStatus() == 0) {
                return AjaxResult.error("账号未审核，请联系管理员！");
            } else {
                return AjaxResult.error("账号审核未通过，请联系管理员！");
            }
        } else {
            return AjaxResult.error("学号或者工号密码错误！");
        }
    }
}
}
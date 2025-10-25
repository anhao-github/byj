// pages/reg/reg.js
import {
    http
}from "../../utils/request"
Page({

    /**
     * 页面的初始数据
     */
    data: {
        aptIndex:null,
        aptArr:[
            {id:1,name:"人工智能学院"},
            {id:2,name:"网络安全学院"},
            {id:3,name:"网商学院"}
        ]
    },
    getApt(e){
        var index=e.detail.value;
        this.setData({
            aptIndex:index
        })
    },
    //手机号
    validPhone(e){
        var phone=e.detail.value;
        wx.request({
          url: 'http://apis.juhe.cn/mobile/get',
          method:"get",
          header:{
            "Content-Type":"application/x-www-form-urlencoded"
          },
          data:{
              phone:phone,
              key:"f143cb10258dd4309a506223d157fb01",
              dtype:"json"
          },
          success(res){
              console.log(res)
              if(res.data.error_code==20110||res.data.error_code201102||res.data.error_code==201103){
                  wx.showToast({
                      title:'手机号错误',
                      mask:true,
                      icon:"error"
                  })
              }
          }
        })
    },
//注册
reg(e){
    console.log(e);
    const {regCode,regDept,regMobile,regName,regPassword}=e.detail.value;
    http("/wechat/register","POST",{
        regCode,regDept,regMobile,regName,regPassword
    }).then(res=>{
        wx.showModal({
          title: '系统提示',
          content: '注册成功',
          showCancel:false,
          complete: (res) => {
            if (res.cancel) {
              wx.reLaunch({
                url: '../login/login',
              })
            }
          }
        })
    })
}
})
// const { http } = require("../../utils/request");
import {
    http
}from "../../utils/request"
// pages/login/login.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        codeLength:0,
        pwdLength:0
    },
    //限制字数
restrictNum(e){
    console.log(e)
    var size=e.detail.value.length;
    var type=e.target.dataset.type;
    if(type==='n'){
        this.setData({
            codeLength:size
        })
    }
    if(type==='p'){
        this.setData({
            pwdLength:size
        })
    }
},
    login(e){
      console.log(e)
      //取值
      var userName=e.detail.value.userName;
      var pwd=e.detail.value.pwd;
      //验证
      if(userName=="" || pwd==""){
        wx.showToast({
          title: '请填写内容',
          icon:"error",
          mask:true
        })
        return;
      }
      //发送数据到后端  API接口地址
      http("/wechat/login?userName="+userName+"&password="+pwd,"POST").then(res =>{
        wx.setStorageSync('u', res.data.data)
        wx.switchTab({
            url: '../my/my',
        }) 
      })
    }   
  })
// pages/deviceList/deviceList.js
import{http}from"../../utils/request"
Page({

  /**
   * 页面的初始数据
   */
  data: {
    device:[]
  },
  link(e){
    console.log(e.currentTarget.dataset.deviceid)
    //跳转详情页
    wx.navigateTo({
      url: '../deviceDetail/deviceDetail?id='+e.currentTarget.dataset.deviceid,
    })
  },
  onShow(){
    var that=this;
    http("/device/deviceInfo/list").then(res=>{
      that.setData({
        device:res.data.rows
      })
    })
    // wx.request({
    //   url:"http://192.168.10.170:8080/device/deviceInfo/list",
    //   method:"GET",
    //   header:{
    //     Authorization:wx.getStorageSync('u').token
    //   },
    //   success(res){
    //     console.log(res)
    //     that.setData({
    //       device:res.data.rows
    //     })
    //   }
    // })
  }
})
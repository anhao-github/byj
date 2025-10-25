// pages/index/index.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
  
    },
  
    link(e){
      console.log(e.currentTarget.dataset.url)
      //不能跳转tabBar页面
      wx.navigateTo({
        url: e.currentTarget.dataset.url,
      })
    }
  
  
  })
const IP="http://localhost:8080"
module.exports={
    http:function(url,method="GET",data={}){
        return new Promise((resolve,rejrct)=>{
            wx.request({
                url:IP+url,
                method:method,
                data:data,
                success(res){
                  if(res.data.code==200){
                      resolve(res)
                  }else{
                    wx.showToast({
                      title: res.data.msg,
                      icon:"error",
                      mask:true
                    })
                  }
        }
    })
})
}
}
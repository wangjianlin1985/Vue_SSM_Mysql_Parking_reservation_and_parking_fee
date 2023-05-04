// 防抖函数
export function debounce(func, delay) {
  let timer = null
  return function(...args) {
    if (timer) clearTimeout(timer)
    timer = setTimeout(() => {
      func.apply(this, args)
    }, delay);
  }
}

// 对Date的扩展，将 Date 转化为指定格式的String   
// 月(M)、日(d)、小时(H)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
// 例子：   
// (new Date()).Format("yyyy-MM-dd HH:mm:ss.S") ==> 2006-07-02 08:09:04.423   
// (new Date()).Format("yyyy-M-d H:m:s.S")      ==> 2006-7-2 8:9:4.18   
export function formatDate(date, fmt) { //author: meizz   
  var o = {
    "M+": date.getMonth() + 1, //月份   
    "d+": date.getDate(), //日   
    "h+": date.getHours(), //小时   
    "m+": date.getMinutes(), //分   
    "s+": date.getSeconds(), //秒   
    "q+": Math.floor((date.getMonth() + 3) / 3), //季度   
    "S": date.getMilliseconds() //毫秒   
  };
  if (/(y+)/.test(fmt))
    fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt))
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
  return fmt;
}


// 高德地图定位
export function getLocation(callback) {
  AMap.plugin('AMap.Geolocation', function() {
    var geolocation = new AMap.Geolocation({
      // 是否使用高精度定位，默认：true
      enableHighAccuracy: true,
      // 设置定位超时时间，默认：无穷大
      timeout: 10000,
    })

    geolocation.getCurrentPosition()
    AMap.event.addListener(geolocation, 'complete', onComplete);
    AMap.event.addListener(geolocation, 'error', onError);

    function onComplete(data) {
      // data是具体的定位信息
      console.log('定位成功信息：', data);
      // alert(data)
      callback(data)
    }

    function onError(data) {
      // 定位出错
      // console.log('定位失败错误：', data);
      // 调用ip定位
      // getLngLatLocation()
      callback(null);
    }
  })
}

function getLngLatLocation() {
  AMap.plugin('AMap.CitySearch', function() {
    var citySearch = new AMap.CitySearch();
    citySearch.getLocalCity(function(status, result) {
      if (status === 'complete' && result.info === 'OK') {
        // 查询成功，result即为当前所在城市信息
        console.log('通过ip获取当前城市：', result)
        return result
          //逆向地理编码
          // AMap.plugin('AMap.Geocoder', function() {
          //   var geocoder = new AMap.Geocoder({
          //     // city 指定进行编码查询的城市，支持传入城市名、adcode 和 citycode
          //     city: result.adcode
          //   })

        //   var lnglat = result.rectangle.split(';')[0].split(',');
        //   geocoder.getAddress(lnglat, function(status, data) {
        //     if (status === 'complete' && data.info === 'OK') {
        //       // result为对应的地理位置详细信息
        //       console.log(data)
        //     }
        //   })
        // })
      }
    })
  })
}

export function getDistance(lng1, lat1, lng2, lat2) {
  var radLat1 = lat1 * Math.PI / 180.0;
  var radLat2 = lat2 * Math.PI / 180.0;
  var a = radLat1 - radLat2;
  var b = lng1 * Math.PI / 180.0 - lng2 * Math.PI / 180.0;
  var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
    Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
  s = s * 6378.137; // EARTH_RADIUS;
  s = Math.round(s * 10000) / 10000;
  return s;
}
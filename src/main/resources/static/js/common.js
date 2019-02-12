// 虚拟表单提交
function post(URL, PARAMS) {
    var temp = $("<form>");
    temp.attr("action", URL);
    temp.attr("method", "post");
    temp.css("display", "none");
    for (var x in PARAMS) {
        var opt = $("<textarea>");
        opt.attr("name", x);
        opt.text(PARAMS[x]);
        opt.appendTo(temp);
    }
    temp.appendTo($("body"));
    temp.submit();
    return temp;
}

//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
//例子：
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
 var o = {
     "M+": this.getMonth() + 1,                 //月份
     "d+": this.getDate(),                    //日
     "h+": this.getHours(),                   //小时
     "m+": this.getMinutes(),                 //分
     "s+": this.getSeconds(),                 //秒
     "q+": Math.floor((this.getMonth() + 3) / 3), //季度
     "S": this.getMilliseconds()             //毫秒
 };
 if (/(y+)/.test(fmt))
     fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
 for (var k in o)
     if (new RegExp("(" + k + ")").test(fmt))
         fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
 return fmt;
}


//格式化金额
function fmoney(s, n)
{
   n = n > 0 && n <= 20 ? n : 2;
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
   var l = s.split(".")[0].split("").reverse(),
   r = s.split(".")[1];
   t = "";
   for(i = 0; i < l.length; i ++ )
   {
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
   }
   return t.split("").reverse().join("") + "." + r;
}

//用于生成uuid
var UUID = {
	S4:function () {
        return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    },
    randomUUID: function() {
        return (UUID.S4()+UUID.S4()+"-"+UUID.S4()+"-"+UUID.S4()+"-"+UUID.S4()+"-"+UUID.S4()+UUID.S4()+UUID.S4());
    }
}
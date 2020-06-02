var speed=40
  // 向上滚动
  var demo=document.getElementById("demo");
  var demo2=document.getElementById("demo2");
  var demo1=document.getElementById("demo1");
  demo2.innerHTML=demo1.innerHTML
  function Marquee(){
   if(demo.scrollTop>=demo1.offsetHeight){
    demo.scrollTop=0;
   }
   else{
    demo.scrollTop=demo.scrollTop+1;
   }
  }
  var MyMar=setInterval(Marquee,speed)
  demo.onmouseover=function(){clearInterval(MyMar)}
  demo.onmouseout=function(){MyMar=setInterval(Marquee,speed)}
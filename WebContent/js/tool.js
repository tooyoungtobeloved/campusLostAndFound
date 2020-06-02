function printer(element, word,times,callback) {
    let i = 0;
    var eles = document.querySelector(element)
    eles.classList.add("typeWriter")
    var timer = setInterval(function () {
        if (i < word.length) {
            // eles.InnerHTML = ;
            $(element).html(word.slice(0, ++i))
        } else {
            clearInterval(timer)
            if(typeof callback == "function")callback();
        }
    }, times, false)
}
// function imgEmerge(ele) {
//     let img = document.querySelector(ele)
//     window.addEventListener("mousemove",function (e){
//         let x = e.clientX;
//         let y = e.pageY;
//         img.style.left = x + "px";
//         img.style.top = y + "px";
//         this.window.removeEventListener(e.type,arguments.callee,false)
//     },false)
// }

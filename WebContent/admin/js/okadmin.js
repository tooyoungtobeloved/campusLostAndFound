layui.use(["element", "layer", "okTab", "okMenu", "okUtils", "okLayer"], function () {
    var element = layui.element;
    var layer = layui.layer;
    var okTab = layui.okTab;
    var okMenu = layui.okMenu;
    var $ = layui.jquery;
    var okUtils = layui.okUtils;
    var okLayer = layui.okLayer;

    /**
     * localhost运行提示
     */
    // var href = location.href;
    // if (href.substring(0, 4) != "http") {
    //     layer.msg("请先部署到localhost环境下再访问！", {icon: 7, time: 3000, anim: 1});
    // }

    /**
     * 左边菜单显示/隐藏功能
     * @type {boolean}
     */
    $(".menu-switch").click(function () {
        if ($(".layui-layout-admin .layui-side").css("left") == '0px') {
            $(".layui-layout-admin .layui-side").animate({ left: "-200px" });
            $(".layui-layout-admin .content-body").animate({ left: "0px" });
            $(".layui-layout-admin .layui-footer").animate({ left: "0px" });
        } else {
            $(".layui-layout-admin .layui-side").animate({ left: "0px" });
            $(".layui-layout-admin .content-body").animate({ left: "200px" });
            $(".layui-layout-admin .layui-footer").animate({ left: "200px" });
        }
    });

    /**
     * 生成左侧菜单树
     */
    // okMenu.generatorMenu("data/menu.json", "get");

    /**
     * 监听导航菜单的点击
     */
    $.ajax({
        type: "get",
        url: "/lostandfound/ForAdmin",
        data: {"method":"get"},
        dataType: "json",
        success: function (response) {
            //admin
            if(response.status == 0){
                $("#admintype").html("管理员")
                //超级管理员
            }else if(response.status == 1){
                $("#admintype").html("超级管理员")
                var html = `
                <dd id="open">
                <a href="javascript:;" path="pages/member/role.html">
                <i class="layui-icon layui-icon-link"></i>角色列表</a>
                </dd>`
                $("#append").append(html)
            }
    
        }
    });
    element.on("nav(navFilter)", function (elem) {
        var path = elem.context.attributes.path;
        if (path && path.textContent != "") {
            // var title = elem.context.innerHTML;
            var title = elem.context.innerText;
            title = title.substring(title.indexOf(" "), title.length);
            var path = path.textContent;
            okTab.add(title, path)
        }
    });

    // $("#open").click(function () {
    //     okTab.add("okLayer", "pages/use/use-okLayer.html");
    // });

    $("#opened").on('click','#open',function(){
        okTab.add("角色列表", "pages/member/role.html");
        var opend = $("#opened").find("dd");
        for(let i = 0; i<opend.length; i++){
            if($(opend[i]).hasClass("layui-this")){
               $(opend[i]).removeClass("layui-this")
            }
        }
        $("#open").addClass("layui-this")
    });

    /**
     * 修改copyright结束时间
     */
    // var data = new Date();
    // var year = data.getFullYear();
    // $("#endYear").text(year);

    /**
     * 捐赠作者
     */
    // $(".layui-footer button.donate").click(function () {
    //     layer.tab({
    //         area: ["330px", "350px"],
    //         tab: [{
    //             title: "支付宝",
    //             content: "<img src='imgs/zfb.jpg' width='200' height='300' style='margin-left: 60px'>"
    //         }, {
    //             title: "微信",
    //             content: "<img src='imgs/wx.jpg' width='200' height='300' style='margin-left: 60px'>"
    //         }]
    //     });
    // });

    /**
     * QQ群交流
     */
    // $(".layui-footer button.communication").click(function () {
    //     layer.tab({
    //         area: ["330px", "350px"],
    //         tab: [{
    //             title: "QQ群",
    //             content: "<img src='imgs/qq.png' width='200' height='300' style='display: block;margin: 0 auto'>"
    //         }]
    //     });
    // });

    /**
     * 退出操作
     */
    $("#logout").click(function () {
        okLayer.confirm("确定要退出吗？", function (index) {
            $.ajax({
                type: "post",
                url: "/lostandfound/ForAdmin",
                data: { "method": "out" },
                dataType: "html",
                success: function (response) {
                    if(response == "success"){
                        window.location = "login.html";
                    }
                }
            });
        });
    });
    
    /**
     * 弹窗皮肤
     */
    $("#alertSkin").click(function () {
        okLayer.open("皮肤动画", "pages/system/alertSkin.html", "50%", "45%", function (layero) { }, function () { });
    });

    /**
     * 锁定账户
     */
    $("#lock").click(function () {
        okLayer.confirm("确定要锁定账户吗？", function (index) {
            layer.close(index);
            $(".yy").show();
            layer.prompt({ btn: ['确定'], title: '输入密码解锁(123456)', closeBtn: 0, formType: 1 }, function (value, index, elem) {
                if (value == "123456") {
                    layer.close(index);
                    $(".yy").hide();
                } else {
                    layer.msg('密码错误', { anim: 6, time: 1000 });
                }
            });
        });
    });

    console.log("        __                         .___      .__        \n" +
        "  ____ |  | __         _____     __| _/_____ |__| ____  \n" +
        " /  _ \\|  |/ /  ______ \\__  \\   / __ |/     \\|  |/    \\ \n" +
        "(  <_> )    <  /_____/  / __ \\_/ /_/ |  Y Y  \\  |   |  \\\n" +
        " \\____/|__|_ \\         (____  /\\____ |__|_|  /__|___|  /\n" +
        "            \\/              \\/      \\/     \\/        \\/ \n" +
        "版本：v1.0\n" +
        "作者：bobi\n" +
        "邮箱：bobi1234@foxmail.com\n" +
        "描述：一个很赞的，扁平化风格的，响应式布局的后台管理模版，旨为后端程序员减压！")
});

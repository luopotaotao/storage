<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>L1服装管理系统登录</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui/jquery.min.js"></script>
    <STYLE>
        body {
            background: #ebebeb;
            font-family: "Helvetica Neue", "Hiragino Sans GB", "Microsoft YaHei",
            "\9ED1\4F53", Arial, sans-serif;
            color: #222;
            font-size: 12px;
        }

        * {
            padding: 0px;
            margin: 0px;
        }

        .top_div {
            background: #008ead;
            width: 100%;
            height: 300px;
        }

        .ipt {
            border: 1px solid #d3d3d3;
            padding: 10px 10px;
            width: 290px;
            border-radius: 4px;
            padding-left: 35px;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
            -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
            -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
            transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
        }

        .ipt:focus {
            border-color: #66afe9;
            outline: 0;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6)
        }

        .u_logo {
            padding: 10px 10px;
            position: absolute;
            top: 43px;
            left: 40px;
        }

        .p_logo {
            padding: 10px 10px;
            position: absolute;
            top: 12px;
            left: 40px;
        }

        a {
            text-decoration: none;
        }

        .tou {
            width: 97px;
            height: 92px;
            position: absolute;
            top: -87px;
            left: 140px;
        }

        .left_hand {
            width: 32px;
            height: 37px;
            position: absolute;
            top: -38px;
            left: 150px;
        }

        .right_hand {
            width: 32px;
            height: 37px;
            position: absolute;
            top: -38px;
            right: -64px;
        }

        .initial_left_hand {
            width: 30px;
            height: 20px;
            position: absolute;
            top: -12px;
            left: 100px;
        }

        .initial_right_hand {
            width: 30px;
            height: 20px;
            position: absolute;
            top: -12px;
            right: -112px;
        }

        .left_handing {
            width: 30px;
            height: 20px;
            position: absolute;
            top: -24px;
            left: 139px;
        }

        .right_handinging {
            width: 30px;
            height: 20px;
            position: absolute;
            top: -21px;
            left: 210px;
        }
    </STYLE>
    <SCRIPT type="text/javascript">
        $(function () {
            //得到焦点
            $("#password").focus(function () {
                $("#left_hand").animate({
                    left: "150",
                    top: " -38"
                }, {
                    step: function () {
                        if (parseInt($("#left_hand").css("left")) > 140) {
                            $("#left_hand").attr("class", "left_hand");
                        }
                    }
                }, 2000);
                $("#right_hand").animate({
                    right: "-64",
                    top: "-38px"
                }, {
                    step: function () {
                        if (parseInt($("#right_hand").css("right")) > -70) {
                            $("#right_hand").attr("class", "right_hand");
                        }
                    }
                }, 2000);
            });
            //失去焦点
            $("#password").blur(function () {
                $("#left_hand").attr("class", "initial_left_hand");
                $("#left_hand").attr("style", "left:100px;top:-12px;");
                $("#right_hand").attr("class", "initial_right_hand");
                $("#right_hand").attr("style", "right:-112px;top:-12px");
            });
        });
    </SCRIPT>

    <script type="text/javascript">
        $(function () {
            $('#img').bind('click', refreshImage);
            $('#btn_submit').bind('click', doLogin);
            function refreshImage() {
                $('#img').attr('src', 'images/kaptcha.jpg?timestamp=' + (new Date().getTime()));
            }

            function doLogin() {
                var username = $("#username").val();
                var password = $("#password").val();
                var captcha = $("#captcha").val();

                if (!username) {
                    alert("用户名不能为空！");
                    return;
                }
                if (!password) {
                    alert("密码不能为空！");
                    return;
                }
                if (!captcha) {
                    alert("验证码不能为空！");
                    return;
                }
                $.ajax({
                    url: 'login',
                    type: 'post',
                    data: {
                        username: $('#username').val(),
                        password: $('#password').val(),
                        captcha: $('#captcha').val()
                    },
                    dateType: 'json'
                }).success(function (ret) {
                    if (ret && ret.flag) {
                        location.href = '.'
                    } else {
                        if (ret.msg) {
                            alert(ret.msg);
                        } else {
                            alert('登录失败!');
                        }
                    }
                }).error(function () {
                    alert('error');
                });
            }
        });
    </script>
</head>
<body>
<form id="adminlogin" name="adminlogin" action="${pageContext.request.contextPath}/user/login">
    <DIV class="top_div"></DIV>
    <DIV
            style="background: rgb(255, 255, 255); margin: -100px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none;
			width: 400px; height: 200px; text-align: center;">
        <P style="padding: 30px 0px 10px; position: relative;">
            <INPUT class="ipt" type="text" name="username" id="username"
                   placeholder="请输入用户名">
        </P>
        <P style="position: relative;">
            <SPAN class="p_logo"></SPAN> <INPUT class="ipt" id="password" name="password"
                                                type="password" placeholder="请输入密码">
        </P>
        <P style="position: absolute;">
            <INPUT class="ipt" id="captcha" name="captcha" style="width: 100px;float: left;margin-left: 32px;
    margin-top: 12px;"
                                                type="text" placeholder="请输入验证码">
            <img id="img" src="/images/kaptcha.jpg"
                 style="width: 100px;height: 30px;padding-top: 15px;padding-left: 20px">
        </P>
        <DIV style="height: 50px; line-height: 50px;">
            <P style="margin: 0px 35px">
                <input id="btn_submit" type="button" value="登录" style="float: right; rgb(0, 142, 173); padding: 7px 10px;float: right;margin-top: 13px;
					 border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;">
            </P>
        </DIV>
    </DIV>
</form>
</body>
</html>
<script type=text/javascript>
    if ('${errorMsg}' != '') {
        alert('${errorMsg}');
    }
</script>
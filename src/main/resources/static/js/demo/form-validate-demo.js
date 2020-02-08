//以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
        $.validator.setDefaults({
            highlight: function (element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            success: function (element) {
                element.closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            errorElement: "span",
            errorPlacement: function (error, element) {
                if (element.is(":radio") || element.is(":checkbox")) {
                    error.appendTo(element.parent().parent().parent());
                } else {
                    error.appendTo(element.parent());
                }
            },
            errorClass: "help-block m-b-none",
            validClass: "help-block m-b-none"


        });

        //以下为官方示例
        $().ready(function () {
            // validate the comment form when it is submitted
            $("#commentForm").validate();
            // validate signup form on keyup and submit
            var icon = "<i class='fa fa-times-circle'></i> ";
            $("#signupForm").validate({
                rules: {
                    firstname: "required",
                    lastname: "required",
                    username: {
                        required: true,
                        minlength: 4,
                        //添加ajax异步验证用户名是否重复
                        remote: {
                            type: "post",
                            // 服务端验证地址，返回类型必须是boolean
                            url: "/users/detailUserByUsername",
                            data: {
                                username: function() {
                            //  有返回值，如果直接写“data: {username: $("#username").val();}”，这样是获取不到值的。
                                    return $("#username").val();
                                }
                            },
                            dataType: "html",
                            dataFilter: function(data, type) {
                                if (data == "true"){
                                    return true;
                                }else{
                                    return false;
                                }
                            }
                        }
                    },
                    sname: {
                        required: true,
                        //添加ajax异步验证用户名是否重复
                        remote: {
                            type: "post",
                            // 服务端验证地址，返回类型必须是boolean
                            url: "/student/existsStuName",
                            data: {
                                sname: function() {
                                    //  有返回值，如果直接写“data: {username: $("#username").val();}”，这样是获取不到值的。
                                    return $("#sname").val();
                                }
                            },
                            dataType: "html",
                            dataFilter: function(data, type) {
                                if (data === "true"){
                                    return true;
                                }else{
                                    return false;
                                }
                            }
                        }
                    },
                    sName: {
                        required: true,
                        //添加ajax异步验证用户名是否重复
                        remote: {
                            type: "post",
                            // 服务端验证地址，返回类型必须是boolean
                            url: "/student/isExistsStuName",
                            data: {
                                sname: function() {
                                    //  有返回值，如果直接写“data: {username: $("#username").val();}”，这样是获取不到值的。
                                    return $("#sName").val();
                                }
                            },
                            dataType: "html",
                            dataFilter: function(data, type) {
                                if (data === "true"){
                                    return true;
                                }else{
                                    return false;
                                }
                            }
                        }
                    },
                    password: {
                        required: true,
                        minlength: 6
                    },
                    pwd: {
                        required: true,
                        minlength: 6,
                        //添加ajax异步验证用户名是否重复
                        remote: {
                            type: "post",
                            // 服务端验证地址，返回类型必须是boolean
                            url: "/users/checkOldPwd",
                            data: {
                                pwd: function() {
                                    //  有返回值，如果直接写“data: {username: $("#username").val();}”，这样是获取不到值的。
                                    return $("#pwd").val();
                                }
                            },
                            dataType: "html",
                            dataFilter: function(data, type) {
                                if (data == "false"){
                                    return true;
                                }else{
                                    return false;
                                }
                            }
                        }
                    },
                    confirm_password: {
                        required: true,
                        minlength: 6,
                        equalTo: "#password"
                    },
                    phone:{
                        isPhone: true
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    userrole: {
                        required: true
                    },
                    topic: {
                        required: "#newsletter:checked",
                        minlength: 2
                    },
                    agree: "required"
                },
                messages: {
                    firstname: icon + "请输入你的姓",
                    lastname: icon + "请输入您的名字",
                    username: {
                        required: icon + "请输入您的用户名",
                        minlength: icon + "用户名必须四个字符以上",
                        remote: icon + "用户名已存在，请重新输入！"
                    },
                    sname: {
                        remote: icon + "该学生姓名已存在，请重新输入！"
                    },
                    sName: {
                        remote: icon + "学员信息不存在，请确认后再输入！"
                    },
                    password: {
                        required: icon + "请输入您的密码",
                        minlength: icon + "密码必须6个字符以上"
                    },
                    pwd: {
                        required: icon + "请输入您的密码",
                        minlength: icon + "密码必须6个字符以上",
                        remote: icon + "密码输入不正确，请重新输入！"
                    },
                    confirm_password: {
                        required: icon + "请再次输入密码",
                        minlength: icon + "密码必须6个字符以上",
                        equalTo: icon + "两次输入的密码不一致"
                    },
                    phone:{
                        isPhone: icon + "请填写正确的手机号码"
                    },
                    userrole: {
                        required: icon + "请选择用户角色"
                    },
                    email: icon + "请输入您的E-mail",
                    agree: {
                        required: icon + "必须同意协议后才能注册",
                        element: '#agree-error'
                    }
                }
            });

            // propose username by combining first- and lastname
            $("#username").focus(function () {
                var firstname = $("#firstname").val();
                var lastname = $("#lastname").val();
                if (firstname && lastname && !this.value) {
                    this.value = firstname + "." + lastname;
                }
            });

            jQuery.validator.addMethod("isPhone", function(value, element) {
                var length = value.length;
                var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(16[0-9]{1})|(19[0-9]{1}))+\d{8})$/;
                return this.optional(element) || (length == 11 && mobile.test(value));
            }, "请填写正确的手机号码");


        });



        $("#myModal").on("hidden.bs.modal", function () {

            $(this).removeData("bs.modal");

        });
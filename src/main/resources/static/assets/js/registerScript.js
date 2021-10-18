$(function() {
    $("register_name_error_message").hide();
    $("register_password_error_message").hide();

    var error_name = false;
    var error_password = false;
    var error_retype_password=false;

    $("#registerName").focusout(function() {
        check_name();
    });
    $("#registerPassword").focusout(function() {
        check_password();
    });
    $("#registerRetypePassword").focusout(function() {
            check_retype_password();
    });

    function check_name() {
        var pattern = /^[a-zA-Z ]*$/;
        var name = $("#registerName").val().trim();
        if (pattern.test(name) && name !== '') {
            $("#register_name_error_message").hide();
            $("#registerName").css("border-bottom", "2px solid #34F458");
        } else {
            $("#register_name_error_message").html("Should contain only Characters");
            $("#register_name_error_message").show();
            $("#registerName").css("border-bottom", "2px solid #F90A0A");
            error_name = true;
        }
    }

    function check_password() {
        var pattern = /^[a-zA-Z0-9,.@:;! ]*$/;
        var password = $("#registerPassword").val();
        if (pattern.test(password) && password !== '') {
            $("#register_password_error_message").hide();
            $("#registerPassword").css("border-bottom", "2px solid #34F458");
        } else {
            $("#register_password_error_message").html("Enter a valid password.(a-z,A_Z,0-9,@!:;");
            $("#register_password_error_message").show();
            $("#registerPassword").css("border-bottom", "2px solid #F90A0A");
            error_password = true;
        }
    }
    function check_retype_password() {
            var password=$("#registerPassword").val();
            var retypepassword = $("#registerRetypePassword").val();
            if (retypepassword === password && password !== '') {
                $("#register_retype_password_error_message").hide();
                $("#registerRetypePassword").css("border-bottom", "2px solid #34F458");
            } else {
                $("#register_retype_password_error_message").html("Password not matched");
                $("#register_retype_password_error_message").show();
                $("#registerRetypePassword").css("border-bottom", "2px solid #F90A0A");
                error_retype_password = true;
            }
        }

     $("#createUser").click(function() {
            error_name = false;
            error_password = false;
            error_retype_password=false;

            check_name();
            check_password();
            check_retype_password();

            if (error_name === false && error_password === false  && error_retype_password=== false) {
                register();
            } else {
                alert("Please Fill the form Correctly");
                return false;
            }

        });

    function register(){
    event.preventDefault();
        var username = $("#registerName").val();
        var password = $("#registerPassword").val();
        var role = $("#registerRole").val();
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "http://localhost:8080/register/user",
            cache: false,
            data: JSON.stringify({
                'username': username,
                'password': password,
                'role': role
            }),
            success: function(result) {
                alert("Successful Registeration");
                $('#registerUserform').each(function() {
                  this.reset();
                });
                window.location = "http://localhost:8080/";

            },
            error: function(err) {
                alert("Error: Registration, UserName Already exists");
            }
        });
        }



  });



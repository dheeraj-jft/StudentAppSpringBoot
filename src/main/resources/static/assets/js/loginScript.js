$(function() {
    $("name_error_message").hide();
    $("password_error_message").hide();
    $("retype_password_error_message").hide();

    var error_name = false;
    var error_password = false;
    var error_retype_password=false;

    $("#username").focusout(function() {
        check_name();
    });
    $("#password").focusout(function() {
        check_password();
    });
    $("#retype_password").focusout(function() {
            check_retype_password();
        });


    function check_name() {
        var pattern = /^[a-zA-Z ]*$/;
        var name = $("#username").val().trim();
        if (pattern.test(name) && name !== '') {
            $("#name_error_message").hide();
            $("#username").css("border-bottom", "2px solid #34F458");
        } else {
            $("#name_error_message").html("Should contain only Characters");
            $("#name_error_message").show();
            $("#username").css("border-bottom", "2px solid #F90A0A");
            error_name = true;
        }
    }

    function check_password() {
        var pattern = /^[a-zA-Z0-9,.@:;! ]*$/;
        var password = $("#password").val();
        if (pattern.test(password) && password !== '') {
            $("#password_error_message").hide();
            $("#password").css("border-bottom", "2px solid #34F458");
        } else {
            $("#password_error_message").html("Enter a valid password.(a-z,A_Z,0-9,@!:;)");
            $("#password_error_message").show();
            $("#password").css("border-bottom", "2px solid #F90A0A");
            error_password = true;
        }
    }

function check_retype_password() {
            var password=$("#password").val();
            var retypepassword = $("#retype_password").val();
            if (retypepassword === password && password !== '') {
                $("#retype_password_error_message").hide();
                $("#retype_password").css("border-bottom", "2px solid #34F458");
            } else {
                $("#retype_password_error_message").html("Password not matched");
                $("#retype_password_error_message").show();
                $("#retype_password").css("border-bottom", "2px solid #F90A0A");
                error_retype_password = true;
            }
        }




         $('#loginUser').click (function(e) {
                           e.preventDefault();
                           error_name = false;
                           error_password = false;
                           error_retype_password=false;

                           check_name();
                           check_password();
                           check_retype_password();

                           if (error_name === false && error_password === false  && error_retype_password=== false) {
                               console.log("Inside form register");
                               $("#loginForm").submit();
                           } else {

                               alert("Please Fill the form Correctly");
                               return false;
                           }


               });



  });


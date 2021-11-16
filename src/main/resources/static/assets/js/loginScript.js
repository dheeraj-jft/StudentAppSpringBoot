$(function () {
    $("name_error_message").hide();
    $("password_error_message").hide();

    var error_name = false;
    var error_password = false;

    $("#username").focusout(function () {
        check_name();
    });
    $("#password").focusout(function () {
        check_password();
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

    $('#loginUser').click(function (e) {
        e.preventDefault();
        error_name = false;
        error_password = false;
        check_name();
        check_password();

        if (error_name === false && error_password === false) {
            $("#loginForm").submit();
        } else {

            alert("Please Fill the form Correctly");
            return false;
        }
    });

    const togglePassword = document.querySelector('#togglePassword');
    const password = document.querySelector('#password');

    togglePassword.addEventListener('click', function (e) {
        // toggle the type attribute
        const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
        password.setAttribute('type', type);
        // toggle the eye slash icon
        this.classList.toggle('fa-eye-slash');
    });
});




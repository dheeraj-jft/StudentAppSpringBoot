var oldName = "";
$(function () {
    $("change_name_error_message").hide();
    $("change_password_error_message").hide();
    $("change_retype_password_error_message").hide();
    oldName = $("#changeUsername").val();
    var error_name = false;
    var error_password = false;
    var error_retype_password = false;

});

function check_name() {
    var pattern = /^[a-zA-Z ]*$/;
    var name = $("#changeUsername").val().trim();
    if (pattern.test(name) && name !== '') {
        $("#change_name_error_message").hide();
        $("#changeUsername").css("border-bottom", "2px solid #34F458");
    } else {
        $("#change_name_error_message").html("Should contain only Characters");
        $("#change_name_error_message").show();
        $("#changeUsername").css("border-bottom", "2px solid #F90A0A");
        error_name = true;
    }
}

function check_password() {
    var pattern = /^[a-zA-Z0-9,.@:;! ]*$/;
    var password = $("#changePassword").val();
    if (pattern.test(password) && password !== '') {
        $("#change_password_error_message").hide();
        $("#changePassword").css("border-bottom", "2px solid #34F458");
    } else {
        $("#change_password_error_message").html("Enter a valid password.(a-z,A_Z,0-9,@!:;");
        $("#change_password_error_message").show();
        $("#changePassword").css("border-bottom", "2px solid #F90A0A");
        error_password = true;
    }
}

function check_retype_password() {
    var password = $("#changePassword").val();
    var retypepassword = $("#changeRetypePassword").val();
    if (retypepassword === password) {
        $("#change_retype_password_error_message").hide();
        $("#changeRetypePassword").css("border-bottom", "2px solid #34F458");
    } else {
        $("#change_retype_password_error_message").html("Password not matched");
        $("#change_retype_password_error_message").show();
        $("#changeRetypePassword").css("border-bottom", "2px solid #F90A0A");
        error_retype_password = true;
    }
}

$(document).delegate('#updateDetails', 'click', function () {
    error_name = false;
    error_password = false;
    error_retype_password = false;

    var name = $("#changeUsername").val().trim();
    var password = $("#changePassword").val();
    var retypepassword = $("#changeRetypePassword").val();
    if (password != "") {
        check_password();
    } else {
        error_password === false;
    }
    check_name();
    check_retype_password();
    if (error_name === false && name === oldName && password === "" && error_password === false && error_retype_password === false) {
        alert("Nothing to update!!");
        return true;
    }

    if (error_name === false && error_password === false && error_retype_password === false) {
        updateDetails();
    } else {
        alert("Please Fill the form Correctly");
        return false;
    }

});

function updateDetails() {
    event.preventDefault();
    var username = $("#changeUsername").val();
    var password = $("#changePassword").val();
    $.ajax({
        type: "PUT",
        contentType: "application/json; charset=utf-8",
        url: "/users/" + oldName,
        data: JSON.stringify({
            'password': password,
            'username': username,
            'role': ""
        }),
        cache: false,
        success: function (result) {
            alert("Details updated Successfully");
            window.location = "/";
            return true;
        },
        error: function (err, xhr) {
            alert("Error: update details, UserName Already exists");
            return false;
        }
    });
}

$(document).ready(function () {
    $("#changePassword").val("");
});

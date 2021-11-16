$(document).ready(function () {
    showUserTable();
});

function showUserTable() {
    var role = $('#role').text();
    var t = $('#userTable').dataTable({
            "ajax": {
                "url": "/users/userslist",
                "dataSrc": ""
            },
            "columns": [
                {
                    "data": null,
                    render: function (data, type, row, meta) {
                        return meta.row + meta.settings._iDisplayStart + 1;
                    }
                },
                {"data": "username"},
                {
                    "data": "emailAddress",
                    "defaultContent": "No Email Address found"
                },
                {"data": "role"},
                {"data": "oauthEnabled", "defaultContent": "false"},
                {"data": "provider", "defaultContent": "none"},
                {
                    "data": null,
                    render: function (data, type, full) {
                        return '<div class="buttonContainer"><button class="edit">Edit</button><button class="delete">Delete</button></div>';
                    }
                }
            ],
            "bDestroy": true,
            "responsive": true
        }
    );

    new $.fn.dataTable.FixedHeader(t);

}

$(function () {
    $("register_name_error_message").hide();
    $("register_password_error_message").hide();
    $("register_retype_password_error_message").hide();
    $("register_email_error_message").hide();

    var error_name = false;
    var error_password = false;
    var error_retype_password = false;
    var error_email = false;

    $("#registerName").focusout(function () {
        check_name();
    });
    $("#registerPassword").focusout(function () {
        check_password();
    });
    $("#registerRetypePassword").focusout(function () {
        check_retype_password();
    });
    $("#registerEmail").focusout(function () {
        check_emailId();
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

    function check_emailId() {
        var pattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        var email = $("#registerEmail").val().trim();
        if (email === "" || pattern.test(email)) {
            $("#register_email_error_message").hide();
            $("#registerEmail").css("border-bottom", "2px solid #34F458");
        } else {
            $("#register_email_error_message").html("Should contain valid email Address");
            $("#register_email_error_message").show();
            $("#registerEmail").css("border-bottom", "2px solid #F90A0A");
            error_email = true;
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
        var password = $("#registerPassword").val();
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

    $("#createUser").click(function () {
        error_name = false;
        error_password = false;
        error_retype_password = false;
        error_email = false;


        check_name();
        check_password();
        check_retype_password();
        check_emailId();

        if (error_name === false && error_password === false && error_retype_password === false
            && error_email === false) {
            register();
        } else {
            alert("Please Fill the form Correctly");
            return false;
        }

    });

    function register() {
        event.preventDefault();
        var username = $("#registerName").val().trim();
        var password = $("#registerPassword").val();
        var role = $("#registerRole").val();
        var emailId = $("#registerEmail").val();
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/users",
            cache: false,
            data: JSON.stringify({
                'username': username,
                'password': password,
                'role': role,
                'emailAddress': emailId
            }),
            success: function (data) {
                $('#successBlock').html(data);
                $('#successModal').modal('show');
                showUserTable();
                $('#addModal_form').modal('hide');
                $('#registerUserform').each(function () {
                    this.reset();
                });

            },
            error: function (err) {
                alert("Error:" + err.responseText);
                return false;
            }
        });
    }

});
$(document).on('hide.bs.modal', '#addModal_form', function (e) {
    $('#registerUserform').each(function () {
        this.reset();
    });
    $("#register_name_error_message").hide();
    $("#register_email_error_message").hide();
    $("#register_password_error_message").hide();
    $("#register_retype_password_error_message").hide();

    $("#registerPassword").css("border-bottom", "none");
    $("#registerName").css("border-bottom", "none");
    $("#registerRetypePassword").css("border-bottom", "none");
    $("#registerEmail").css("border-bottom", "none");
});

$(function () {
    $("edit_name_error_message").hide();
    $("edit_email_error_message").hide();
    $("edit_password_error_message").hide();
    $("edit_retype_password_error_message").hide();

    var error_name = false;
    var error_email = false;
    var error_password = false;
    var error_retype_password = false;


    function check_name() {
        var pattern = /^[a-zA-Z ]*$/;
        var name = $("#editName").val().trim();
        if (pattern.test(name) && name !== '') {
            $("#edit_name_error_message").hide();
            $("#editName").css("border-bottom", "2px solid #34F458");
        } else {
            $("#edit_name_error_message").html("Should contain only Characters");
            $("#edit_name_error_message").show();
            $("#editName").css("border-bottom", "2px solid #F90A0A");
            error_name = true;
        }
    }

    function check_emailId() {
        var pattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        var email = $("#editEmail").val().trim();
        if (email === "" || pattern.test(email)) {
            $("#edit_email_error_message").hide();
            $("#editEmail").css("border-bottom", "2px solid #34F458");
        } else {
            $("#edit_email_error_message").html("Should contain valid email Address");
            $("#edit_email_error_message").show();
            $("#editEmail").css("border-bottom", "2px solid #F90A0A");
            error_email = true;
        }
    }

    function check_password() {
        var pattern = /^[a-zA-Z0-9,.@:;! ]*$/;
        var password = $("#editPassword").val();
        if (pattern.test(password) && password !== '') {
            $("#edit_password_error_message").hide();
            $("#editPassword").css("border-bottom", "2px solid #34F458");
        } else {
            $("#edit_password_error_message").html("Enter a valid password.(a-z,A_Z,0-9,@!:;)");
            $("#edit_password_error_message").show();
            $("#editPassword").css("border-bottom", "2px solid #F90A0A");
            error_password = true;
        }
    }

    function check_retype_password() {
        var password = $("#editPassword").val();
        var retypepassword = $("#editRetypePassword").val();
        if (retypepassword === password) {
            $("#edit_retype_password_error_message").hide();
            $("#editRetypePassword").css("border-bottom", "2px solid #34F458");
        } else {
            $("#edit_retype_password_error_message").html("Password not matched");
            $("#edit_retype_password_error_message").show();
            $("#editRetypePassword").css("border-bottom", "2px solid #F90A0A");
            error_retype_password = true;
        }
    }

    $("#edit_user_button").click(function () {
        error_name = false;
        error_email = false;
        error_password = false;
        error_retype_password = false;
        var name = $("#editName").val().trim();
        var password = $("#editPassword").val();
        var retypepassword = $("#editRetypePassword").val();
        var role = $("#editRole").val();
        if (password != "") {
            check_password();
        } else {
            error_password = false;
        }
        var email = $("#editEmail").val();
        if (email != "") {
            check_emailId();
        } else {
            error_email = false;
        }
        check_name();
        check_retype_password();
        check_emailId();
        if (oldEmail === email && error_email === false && error_name === false && name === oldname && oldrole == role && password === "" && error_password === false && error_retype_password === false) {
            alert("Nothing to update!!");
            $('#edit_modal_form').modal('hide');
            return true;
        }

        if (error_email === false && error_name === false && error_password === false && error_retype_password === false) {
            editDetails();
            return true;
        } else {
            alert("Please Fill the form Correctly");
            return false;
        }

    });

    function editDetails() {
        event.preventDefault();
        var username = $("#editName").val().trim();
        var password = $("#editPassword").val();
        var role = $("#editRole").val();
        var email = $("#editEmail").val();
        $.ajax({
            type: "PUT",
            contentType: "application/json; charset=utf-8",
            url: "/users/" + oldname,
            cache: false,
            data: JSON.stringify({
                'username': username,
                'password': password,
                'role': role,
                'emailAddress': email,
                'oldname': oldname

            }),
            success: function (data) {
                $('#successBlock').html(data);
                $('#successModal').modal('show');
                showUserTable();
                $('#edit_modal_form').modal('hide');
                $('#edit_user_form').each(function () {
                    this.reset();
                });
                return true;

            },
            error: function (err) {
                alert("Error:" + err.responseText);
                return false;
            }
        });
    }

});
$(document).on('hide.bs.modal', '#edit_modal_form', function (e) {
    $('#edit_user_form').each(function () {
        this.reset();
    });
    $("#edit_name_error_message").hide();
    $("#edit_email_error_message").hide();
    $("#edit_password_error_message").hide();
    $("#edit_retype_password_error_message").hide();

    $("#editPassword").css("border-bottom", "none");
    $("#editName").css("border-bottom", "none");
    $("#editEmail").css("border-bottom", "none");
    $("#editRetypePassword").css("border-bottom", "none");
});
var oldname, oldrole, oldEmail;
$(document).delegate('.edit', 'click', function () {
    let name, role, email;

    var $current_row = $(this).parents('tr');
    if ($current_row.hasClass('child')) {
        $current_row = $current_row.prev();
    }

    var $tds = $current_row.find("td:nth-child(2)");
    $.each($tds, function () {
        name = $(this).text();
        oldname = name;
    });
    $tds = $current_row.find("td:nth-child(4)");
    $.each($tds, function () {
        role = $(this).text();
    });
    $tds = $current_row.find("td:nth-child(3)");
    $.each($tds, function () {
        email = $(this).text();
    });

    $('#edit_modal_form').modal('show')
    $('#editName').val(name);
    $('#editPassword').val('');
    $('#editRole').val(role);
    if (email === 'No Email Address found') {
        $('#editEmail').val('');
        oldEmail = '';
    } else {
        $('#editEmail').val(email);
        oldEmail = email;
    }
    oldrole = role;

    $("#editPassword").css("border-bottom", "none");


});
$(document).delegate('#delete_details_button', 'click', function () {

    var username = $('#delete_username').val();
    var self_delete = false;
    var currentUsername = $('#username').text();
    if (currentUsername === username) {
        alert("Deleting self user, you will be logout after this operation.");
        self_delete = true;
    }

    $.ajax({
        type: "DELETE",
        url: "/users/" + username,
        cache: false,
        success: function (data) {
            $('#successBlock').html(data);
            $('#successModal').modal('show');
            showUserTable();
            $('#delete_modal').modal('hide');
            if (self_delete === true)
                window.location = "/login?logout";
        },
        error: function (xhr) {
            alert("Error: record delete");
        }
    });
});
$(document).delegate('.delete', 'click', function () {
    let name, role;
    var $current_row = $(this).parents('tr');
    if ($current_row.hasClass('child')) {
        $current_row = $current_row.prev();
    }

    var $tds = $current_row.find("td:nth-child(2)");
    $.each($tds, function () {
        name = $(this).text();

    });
    var $tds = $current_row.find("td:nth-child(4)");
    $.each($tds, function () {
        role = $(this).text();
    });
    $('#delete_modal').modal('show')
    $('#delete_username').val(name);
    $('#delete_role').val(role);

});







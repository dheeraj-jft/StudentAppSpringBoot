
$(document).ready(function() {
showUserTable();
});


function showUserTable() {

    $("#userTable").remove();
    $("#userTable_wrapper").remove();

    var  htmlForTable = '<table class ="table" id="userTable"><thead><tr><th>sr.</th><th>User Name</th><th>Role</th><th id="actionshead">Actions</th></tr></thead><tbody>';
    htmlForTableData = '';
    $.getJSON('/users/userslist', function(json) {

        for (var i = 0; i < json.length; i++) {
            var sr = i + 1;
                htmlForTableData += '<tr>' + '<td>' + sr + '</td>' + '<td>' + json[i].username + '</td>' + '<td>' + json[i].role + '</td>' +'<td class=\'buttonContainer\'><button class=\'edit\'>Edit</button>&nbsp;&nbsp;<button  class=\'delete\' id=' + json[i].id + '>Delete</button></td>' + '</tr>';
        }
        htmlForTable = htmlForTable + htmlForTableData + '</tbody></table>';
        $('#tableContainer').append(htmlForTable);
        $('#userTable').DataTable();


    });
}

$(function() {
    $("register_name_error_message").hide();
    $("register_password_error_message").hide();
    $("register_retype_password_error_message").hide();

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
        var username = $("#registerName").val().trim();
        var password = $("#registerPassword").val();
        var role = $("#registerRole").val();
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "http://localhost:8080/users/register",
            cache: false,
            data: JSON.stringify({
                'username': username,
                'password': password,
                'role': role
            }),
            success: function(result) {
                alert("Successful Registeration");
                showUserTable();
                $('#addModal_form').modal('hide');
                 $('#registerUserform').each(function() {
                      this.reset();
                  });

            },
            error: function(err) {
                alert("Error: Registration, UserName Already exists");
            }
        });
      }

  });

$(document).on('hide.bs.modal','#addModal_form',function(e){
  $('#registerUserform').each(function() {
  this.reset();
  });
    $("#register_name_error_message").hide();
    $("#register_password_error_message").hide();
    $("#register_retype_password_error_message").hide();

    $("#registerPassword").css("border-bottom", "none");
    $("#registerName").css("border-bottom", "none");
    $("#registerRetypePassword").css("border-bottom", "none");
 });
 $(document).on('shown.bs.modal','#addModal_form',function(e){
   $('#registerUserform').each(function() {
   this.reset();
   });
       $("#registerPassword").css("border-bottom", "none");
       $("#registerName").css("border-bottom", "none");
       $("#registerRetypePassword").css("border-bottom", "none");
  });





$(function() {
    $("edit_name_error_message").hide();
    $("edit_password_error_message").hide();
    $("edit_retype_password_error_message").hide();

    var error_name = false;
    var error_password = false;
    var error_retype_password=false;


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
            var password=$("#editPassword").val();
            var retypepassword = $("#editRetypePassword").val();
            if (retypepassword === password ) {
                $("#edit_retype_password_error_message").hide();
                $("#editRetypePassword").css("border-bottom", "2px solid #34F458");
            } else {
                $("#edit_retype_password_error_message").html("Password not matched");
                $("#edit_retype_password_error_message").show();
                $("#editRetypePassword").css("border-bottom", "2px solid #F90A0A");
                error_retype_password = true;
            }
        }

     $("#edit_user_button").click(function() {
            error_name = false;
            error_password = false;
            error_retype_password=false;
            var name = $("#editName").val().trim();
            var password=$("#editPassword").val();
            var retypepassword = $("#editRetypePassword").val();
            var role=$("#editRole").val();
                if(password!=""){
                   check_password();
                }else{
                    error_password===false;
                 }
            check_name();
            check_retype_password();
            if(error_name === false && name===oldname && oldrole==role && password==="" && error_password===false && error_retype_password===false){
                     alert("Nothing to update!!");
                     $('#edit_modal_form').modal('hide');
                     return true;
             }

            if (error_name === false && error_password === false  && error_retype_password=== false) {
                editDetails();
            } else {
                alert("Please Fill the form Correctly");
                return false;
            }

        });

    function editDetails(){
    event.preventDefault();
        var username = $("#editName").val().trim();
        var password = $("#editPassword").val();
        var role = $("#editRole").val();
        $.ajax({
            type: "PUT",
            contentType: "application/json; charset=utf-8",
            url: "http://localhost:8080/users/register/"+oldname,
            cache: false,
            data: JSON.stringify({
                'username': username,
                'password': password,
                'role': role,
                'oldname':oldname
            }),
            success: function(result) {
                alert("User details saved successfully");
                showUserTable();
                $('#edit_modal_form').modal('hide');
                 $('#edit_user_form').each(function() {
                      this.reset();
                  });

            },
            error: function(err) {
                alert("Error: Saving Details, Username: "+username+" already associated with another account");
            }
        });
      }

  });
  $(document).on('hide.bs.modal','#edit_modal_form',function(e){
    $('#edit_user_form').each(function() {
    this.reset();
    });
      $("#edit_name_error_message").hide();
      $("#edit_password_error_message").hide();
      $("#edit_retype_password_error_message").hide();

      $("#editPassword").css("border-bottom", "none");
      $("#editName").css("border-bottom", "none");
      $("#editRetypePassword").css("border-bottom", "none");
   });

var oldname,oldrole;
$(document).delegate('.edit', 'click', function() {
    let name, role;
    var $row = $(this).closest("tr");

    var $tds = $row.find("td:nth-child(2)");
    $.each($tds, function() {
        name = $(this).text();
        oldname=name;
    });
    var $tds = $row.find("td:nth-child(3)");
    $.each($tds, function() {
        role = $(this).text();
    });

    $('#edit_modal_form').modal('show')
    $('#editName').val(name);
    $('#editPassword').val('');
    $('#editRole').val(role);
    oldrole=role;
    $("#editPassword").css("border-bottom", "none");


});






$(document).delegate('#delete_details_button', 'click', function() {

        var username=$('#delete_username').val();

        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/users/delete/" +username,
            cache: false,
            success: function() {
                showUserTable();
                $('#delete_modal').modal('hide');
            },
            error: function(xhr) {
                alert("Error: record delete");
            }
        });
});

$(document).delegate('.delete', 'click', function() {
    let  name, role;
    var $row = $(this).closest("tr");

    var $tds = $row.find("td:nth-child(2)");
    $.each($tds, function() {
        name = $(this).text();

    });
    var $tds = $row.find("td:nth-child(3)");
    $.each($tds, function() {
        role = $(this).text();
    });
    $('#delete_modal').modal('show')
    $('#delete_username').val(name);
    $('#delete_role').val(role);

});






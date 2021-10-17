//$(document).ready(function() {
//console.log("Inside role check function");
//    $.ajax({
//        type: "GET",
//        contentType: "application/json; charset=utf-8",
//        url: "http://localhost:8080/edit/get/profile",
//        cache: false,
//        success: function(result) {
//            console.log("Successful")
//            return true;
//        },
//        error: function(err,xhr) {
//            console.log("Error")
//            console.log(xhr.status);
//            return false;
//        }
//
//    });
//
//});

$(function() {

    checkRoleforUser();
    $("change_name_error_message").hide();
    $("change_password_error_message").hide();
    $("change_retype_password_error_message").hide();

    var error_name = false;
    var error_password = false;
    var error_retype_password=false;

    $("#changUsername").focusout(function() {
        check_name();
    });
    $("#changePassword").focusout(function() {
        check_password();
    });
    $("#changeRetypePassword").focusout(function() {
            check_retype_password();
    });
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
            var password=$("#changePassword").val();
            var retypepassword = $("#changeRetypePassword").val();
            if (retypepassword === password && password !== '') {
                $("#change_retype_password_error_message").hide();
                $("#changeRetypePassword").css("border-bottom", "2px solid #34F458");
            } else {
                $("#change_retype_password_error_message").html("Password not matched");
                $("#change_retype_password_error_message").show();
                $("#changeRetypePassword").css("border-bottom", "2px solid #F90A0A");
                error_retype_password = true;
            }
        }


     $(document).delegate('#updateDetails', 'click', function() {
            error_name = false;
            error_password = false;
            error_retype_password=false;

            check_name();
            check_password();
            check_retype_password();

            if (error_name === false && error_password === false  && error_retype_password === false) {
                console.log("Inside edit details form ");
                updateDetails();
            } else {
                alert("Please Fill the form Correctly");
                return false;
            }

        });

    function updateDetails(){
    console.log("update details");
    event.preventDefault();
           var username = $("#changeUsername").val();
           var password = $("#changePassword").val();
           $.ajax({
               type: "PUT",
               contentType: "application/json; charset=utf-8",
               url: "http://localhost:8080/edit/profile/update",
               data: JSON.stringify({
                   'password': password,
                   'username': username
               }),
               cache: false,
               success: function(result) {
               console.log("update details");
                  alert("Details updated Successfully");
                  window.location = "http://localhost:8080/";
                   return true;
               },
               error: function(err,xhr) {
               console.log("update details");
                   alert("Error: update details ");
                   console.log("Error")
                   return false;
               }
           });
  }
  function checkRoleforUser() {

      console.log("Inside role check function");
      $.ajax({
          type: "GET",
          contentType: "application/json; charset=utf-8",
          url: "http://localhost:8080/userrole",
          cache: false,
          success: function(result) {
              console.log(result);
              if(result.includes("[USER]")){
                  $("#addUserNav").hide();

                }
              console.log("Successful")
              return true;
          },
          error: function(err,xhr) {
              console.log("Error")
              console.log(xhr.status);
              return false;
          }

      });
      }
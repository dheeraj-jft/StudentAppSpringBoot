$(document).ready(function(){
    updateTable();
});


$(function(){
    $("courseId_error_message").hide();
    $("courseName_error_message").hide();

    var error_name = false;
    var error_id=false;

    $("#form_courseName").focusout(function() {
        check_name();
    });
    $("#form_courseId").focusout(function() {
        check_id();
    });


    function check_name() {
        var pattern = /^[a-zA-Z ]*$/;
        var name = $("#form_courseName").val().trim();
        if (pattern.test(name) && name !== '') {
            $("#courseName_error_message").hide();
            $("#form_courseName").css("border-bottom", "2px solid #34F458");
        } else {
            $("#courseName_error_message").html("Should contain only Characters");
            $("#courseName_error_message").show();
            $("#form_courseName").css("border-bottom", "2px solid #F90A0A");
            error_name = true;
        }
    }

    function check_id() {
        var pattern = /^[a-zA-Z0-9-]*$/;
        var id = $("#form_courseId").val().trim();
        if (pattern.test(id) && id !== '') {
            $("#courseId_error_message").hide();
            $("#form_courseId").css("border-bottom", "2px solid #34F458");
        } else {
            $("#courseId_error_message").html("Enter a valid Id");
            $("#courseId_error_message").show();
            $("#form_courseId").css("border-bottom", "2px solid #F90A0A");
            error_id = true;
        }
    }



    $("#add_form_button").click(function() {
        error_name = false;
        error_id = false;
        check_name();
        check_id();

        if (error_name === false && error_id === false) {
            addCourse();
            return true;
        } else {
            alert("Please Fill the form Correctly");
            return false;
        }
    });



 });
   function addCourse(){

   let courseId=$("#form_courseId").val().trim();
   let courseName=$("#form_courseName").val().trim();
   $.ajax({
           type: "POST",
           contentType: "application/json; charset=utf-8",
           url: "/course/save",
           data: JSON.stringify({
               'courseId': courseId,
               'courseName': courseName,
           }),
           cache: false,
           success: function(result) {

               $('#addModal').modal('hide');
               $('#add_student_form').each(function() {
                   this.reset();
               });
               updateTable();
               return true;
           },
           error: function(err,xhr) {
               alert("Error: Roll no. already exists!!");
               return false;
           }
       });
    }
    function updateTable(){

$('#coursesTable').dataTable({
        "ajax":{
        "url": "/course/list",
         "dataSrc": ""
        },
        "columns": [
                    { "data": "courseId" },
                    { "data": "courseName" },
                ],
            "bDestroy": true,
            "select":true

        }
        );
        };





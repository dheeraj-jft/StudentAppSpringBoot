$(document).ready(function(){
    updateTable();
});


$(function() {
    $("teacherId_error_message").hide();
    $("firstName_error_message").hide();
    $("lastName_error_message").hide();
    $("address_error_message").hide();
    $("phone_error_message").hide();
    $("rollno_error_message").hide();

//    $("edit_name_error_message").hide();
//    $("edit_address_error_message").hide();
//    $("edit_phone_error_message").hide();

    var error_firstName = false;
    var error_lastName = false;
    var error_address = false;
    var error_phone = false;
    var error_teacherId = false;

//    var error_edit_name = false;
//    var error_edit_address = false;
//    var error_edit_phone = false;


    $("#teacherId").focusout(function() {
        check_teacherId();
    });
    $("#address").focusout(function() {
        check_address();
    });
    $("#phone").focusout(function() {
        check_phone();
    });
    $("#firstName").focusout(function() {
        check_firstName();
    });
    $("#lastName").focusout(function() {
        check_lastName();
    });

//    $("#edit_name").focusout(function() {
//        check_edit_name();
//    });
//    $("#edit_address").focusout(function() {
//        check_edit_address();
//    });
//    $("#edit_phone").focusout(function() {
//        check_edit_phone();
//    });


    function check_firstName() {
        var pattern = /^[a-zA-Z]*$/;
        var name = $("#firstName").val().trim();
        if (pattern.test(name) && name !== '') {
            $("#firstName_error_message").hide();
            $("#firstName").css("border-bottom", "2px solid #34F458");
        } else {
            $("#firstName_error_message").html("Should contain only Characters");
            $("#firstName_error_message").show();
            $("#firstName").css("border-bottom", "2px solid #F90A0A");
            error_firstName = true;
        }
    }
    function check_lastName() {
            var pattern = /^[a-zA-Z]*$/;
            var name = $("#lastName").val().trim();
            if (pattern.test(name) && name !== '') {
                $("#lastName_error_message").hide();
                $("#lastName").css("border-bottom", "2px solid #34F458");
            } else {
                $("#lastName_error_message").html("Should contain only Characters");
                $("#lastName_error_message").show();
                $("#lastName").css("border-bottom", "2px solid #F90A0A");
                error_lastName = true;
            }
        }

    function check_address() {
        var pattern = /^[a-zA-Z0-9, ./]*$/;
        var address = $("#address").val().trim();
        if (pattern.test(address) && address !== '') {
            $("#address_error_message").hide();
            $("#address").css("border-bottom", "2px solid #34F458");
        } else {
            $("#address_error_message").html("Enter a valid address");
            $("#address_error_message").show();
            $("#address").css("border-bottom", "2px solid #F90A0A");
            error_address = true;
        }
    }

    function check_phone() {
        var pattern = /^[0-9]*$/;
        var phone = $("#phone").val();
        if (pattern.test(phone) && phone !== '' && phone.length == 10) {
            $("#phone_error_message").hide();
            $("#phone").css("border-bottom", "2px solid #34F458");
        } else {
            $("#phone_error_message").html("Should contains 10 digits ");
            $("#phone_error_message").show();
            $("#phone").css("border-bottom", "2px solid #F90A0A");
            error_phone = true;
        }
    }

    function check_teacherId() {
        var pattern = /^[a-zA-Z0-9-]*$/;
        var teacherId = $("#teacherId").val();
        if (pattern.test(teacherId) && teacherId !== '') {
            $("#teacherId_error_message").hide();
            $("#teacherId").css("border-bottom", "2px solid #34F458");
        } else {
            $("#teacherId_error_message").html("Can contains (a-z, A-z, 0-9, /, -");
            $("#teacherId_error_message").show();
            $("#teacherId").css("border-bottom", "2px solid #F90A0A");
            error_teacherId = true;
        }
    }

    function check_edit_phone() {
        var pattern = /^[0-9]*$/;
        var phone = $("#edit_phone").val();
        if (pattern.test(phone) && phone !== '' && phone.length == 10) {
            $("#edit_phone_error_message").hide();
            $("#edit_phone").css("border-bottom", "2px solid #34F458");
        } else {
            $("#edit_phone_error_message").html("Should contains 10 digits");
            $("#edit_phone_error_message").show();
            $("#edit_phone").css("border-bottom", "2px solid #F90A0A");
            error_edit_phone = true;
        }
    }

    function check_edit_address() {
        var pattern = /^[a-zA-Z0-9, ./]*$/;
        var address = $("#edit_address").val().trim();
        if (pattern.test(address) && address !== '') {
            $("#edit_address_error_message").hide();
            $("#edit_address").css("border-bottom", "2px solid #34F458");
        } else {
            $("#edit_address_error_message").html("Enter a valid address");
            $("#edit_address_error_message").show();
            $("#edit_address").css("border-bottom", "2px solid #F90A0A");
            error_edit_address = true;
        }
    }

    function check_edit_name() {
        var pattern = /^[a-zA-Z ]*$/;
        var name = $("#edit_name").val().trim();
        if (pattern.test(name) && name !== '') {
            $("#edit_name_error_message").hide();
            $("#edit_name").css("border-bottom", "2px solid #34F458");
        } else {
            $("#edit_name_error_message").html("Should contain only Characters");
            $("#edit_name_error_message").show();
            $("#edit_name").css("border-bottom", "2px solid #F90A0A");
            error_edit_name = true;
        }
    }

    $("#add_button").click(function() {
        error_firstName = false;
        error_lastName=false;
        error_address = false;
        error_phone = false;
        error_teacherId=false;

        check_firstName();
        check_lastName();
        check_address();
        check_phone();
        check_teacherId();

        if (error_firstName === false && error_lastName === false && error_address === false && error_phone === false && error_teacherId === false) {
            addNewTeacherToDB();
            return true;
        } else {
            alert("Please Fill the form Correctly");
            return false;
        }
    });

    $("#edit_details_button").click(function() {
        error_edit_name = false;
        error_edit_address = false;
        error_edit_phone = false;

        var updatedName = $('#edit_name').val();
        var updatedAddress = $('#edit_address').val();
        var updatedPhone = $('#edit_phone').val();
        var updatedCourseSet= new Set();
         $("input.editcheckbox[type=checkbox]:checked ").each(function() {
                var courseId=$(this).val();
                updatedCourseSet.add(courseId);
        });
        check_edit_name();
        check_edit_address();
        check_edit_phone();


        if (error_edit_name === false && error_edit_address === false && error_edit_phone === false) {
            if(updatedName === name && updatedAddress === address && updatedPhone === phone && eqSet(updatedCourseSet,courseCheckedSet)){
                    alert("Nothing to update!!");
                }else{
                        editStudentDetails();
                    }
        } else {
            alert("Please Fill the form Correctly to save details");
            return false;
        }
    });
    function eqSet(as, bs) {
        if (as.size !== bs.size) return false;
        for (var a of as) if (!bs.has(a)) return false;
        return true;
    }
});

function updateTable(){
        var role=$('#role').text();


var t= $('#teachersTable').dataTable({
        "ajax":{
        "url": "/teachers/list",
         "dataSrc": ""
        },
        "columns": [
                    {"data":   null ,
                       render: function (data, type, row, meta) {
                        return meta.row + meta.settings._iDisplayStart + 1;
                         }
                         },
                    { "data": "teacherId" },
                    { "data": "firstName" },
                    { "data": "lastName" },
                    { "data": "address" },
                    { "data": "phone" },
                    { "data": "courseSet[]",
                                            render: function(data,row){
                                                var output='';
                                                $.each(data,function(index,item){
                                                   output+=data[index].courseName+", ";

                                                });
                                                if(output.length>30){
                                                    output= output.substring(0,27)+ '...';
                                                }
                                                else if(output===''){
                                                output='<span style="color:red">Not enrolled in any course</span>';
                                                }
                                                return output;
                                            }
                                         },
                    {  "data": null,
                        render: function(data, type, full) {
                       if(role==='[USER]'){
                       return '<div class="buttonContainer"><button class="view">View More</button></div>';

                       }else{
                            return '<div class="buttonContainer"><button class="edit">Edit</button><button class="delete">Delete</button><button class="view">View More</button></div>';

                       }


                                     }
                     }
                ],
            "bDestroy": true,
            "responsive":true


        }
        );
         new $.fn.dataTable.FixedHeader(t);


 }

 $(document).on('hide.bs.modal','#addModal',function(e){
  $('#registerTeacherform').each(function() {
  this.reset();
  });
    $("#teacherId").css("border-bottom", "none");
    $("#firstName").css("border-bottom", "none");
    $("#lastName").css("border-bottom", "none");
    $("#address").css("border-bottom", "none");
    $("#phone").css("border-bottom", "none");

    $("#teacherId_error_message").hide();
    $("#firstName_error_message").hide();
    $("#lastName_error_message").hide();
    $("#address_error_message").hide();
    $("#phone_error_message").hide();
 });


$(document).delegate('.view', 'click', function() {
    let courseId;
    var $row = $(this).closest("tr");

    var $tds = $row.find("td:nth-child(2)");
    $.each($tds, function() {
        courseId = $(this).text();
    });

    window.location = "/course/details/"+courseId;
});

$(document).delegate('.delete', 'click', function() {
    let id, name;
    var $row = $(this).closest("tr");

    var $tds = $row.find("td:nth-child(2)");
    $.each($tds, function() {
        id = $(this).text();

    });
    var $tds = $row.find("td:nth-child(3)");
    $.each($tds, function() {
        name = $(this).text();
    });
    $('#delete_modal').modal('show')
    $('#delete_name').val(name);
    $('#delete_courseId').val(id);

});

$(document).delegate('#delete_details_button', 'click', function() {

        var courseId=$('#delete_courseId').val();
        console.log("Delete "+ courseId);
        $.ajax({
            type: "DELETE",
            url: "/course/" + courseId,
            cache: false,
            success: function(data) {
                $('#successBlock').html(data);
                $('#successModal').modal('show');
                updateTable();
                $('#delete_modal').modal('hide');
            },
            error: function(xhr) {
                alert("Error: record delete");
            }
        });
});

$(document).delegate('.edit', 'click', function() {
    let id, name;
    var $row = $(this).closest("tr");
    var $tds = $row.find("td:nth-child(2)");
    $.each($tds, function() {
        id = $(this).text();
    });
    var $tds = $row.find("td:nth-child(3)");
    $.each($tds, function() {
        name = $(this).text();
    });

    $('#edit_modal').modal('show')
    $('#edit_name').val(name);
    $('#edit_id').val(id);

});




$(document).on('hide.bs.modal','#edit_modal',function(e){

     $("#edit_name").css("border-bottom", "none");
     $("#edit_name_error_message").hide();
  });


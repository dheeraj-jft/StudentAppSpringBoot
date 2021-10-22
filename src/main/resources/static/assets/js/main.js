var roleUser = false;

$(document).ready(function() {
    checkRoleforUser();
});

$(function() {
    $("name_error_message").hide();
    $("address_error_message").hide();
    $("phone_error_message").hide();
    $("rollno_error_message").hide();


    $("edit_name_error_message").hide();
    $("edit_address_error_message").hide();
    $("edit_phone_error_message").hide();

    var error_name = false;
    var error_address = false;
    var error_phone = false;
    var error_rollno = false;

    var error_edit_name = false;
    var error_edit_address = false;
    var error_edit_phone = false;



    $("#form_name").focusout(function() {
        check_name();
    });
    $("#form_address").focusout(function() {
        check_address();
    });
    $("#form_phone").focusout(function() {
        check_phone();
    });
    $("#form_rollno").focusout(function() {
        check_rollno();
    });

    $("#edit_name").focusout(function() {
        check_edit_name();
    });
    $("#edit_address").focusout(function() {
        check_edit_address();
    });
    $("#edit_phone").focusout(function() {
        check_edit_phone();
    });


    function check_name() {
        var pattern = /^[a-zA-Z ]*$/;
        var name = $("#form_name").val().trim();
        if (pattern.test(name) && name !== '') {
            $("#name_error_message").hide();
            $("#form_name").css("border-bottom", "2px solid #34F458");
        } else {
            $("#name_error_message").html("Should contain only Characters");
            $("#name_error_message").show();
            $("#form_name").css("border-bottom", "2px solid #F90A0A");
            error_name = true;
        }
    }

    function check_address() {
        var pattern = /^[a-zA-Z0-9, ./]*$/;
        var address = $("#form_address").val().trim();
        if (pattern.test(address) && address !== '') {
            $("#address_error_message").hide();
            $("#form_address").css("border-bottom", "2px solid #34F458");
        } else {
            $("#address_error_message").html("Enter a valid address");
            $("#address_error_message").show();
            $("#form_address").css("border-bottom", "2px solid #F90A0A");
            error_address = true;
        }
    }

    function check_phone() {
        var pattern = /^[0-9]*$/;
        var phone = $("#form_phone").val();
        if (pattern.test(phone) && phone !== '' && phone.length == 10) {
            $("#phone_error_message").hide();
            $("#form_phone").css("border-bottom", "2px solid #34F458");
        } else {
            $("#phone_error_message").html("Should contains 10 digits ");
            $("#phone_error_message").show();
            $("#form_phone").css("border-bottom", "2px solid #F90A0A");
            error_phone = true;
        }
    }

    function check_rollno() {
        var pattern = /^[0-9]*$/;
        var rollno = $("#form_rollno").val();
        if (pattern.test(rollno) && rollno !== '' && rollno.length <= 10) {
            $("#rollno_error_message").hide();
            $("#form_rollno").css("border-bottom", "2px solid #34F458");
        } else {
            $("#rollno_error_message").html("Should contain 10 or less number of digits");
            $("#rollno_error_message").show();
            $("#form_rollno").css("border-bottom", "2px solid #F90A0A");
            error_rollno = true;
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

    $("#add_form_button").click(function() {
        error_name = false;
        error_address = false;
        error_phone = false;
        error_rollno = false;

        check_name();
        check_address();
        check_phone();
        check_rollno();

        if (error_name === false && error_address === false && error_phone === false && error_rollno === false) {
            addNewStudentToDB();
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

        check_edit_name();
        check_edit_address();
        check_edit_phone();
        if (error_edit_name === false && error_edit_address === false && error_edit_phone === false) {
            editStudentDetails();
        } else {
            alert("Please Fill the form Correctly to save details");
            return false;
        }
    });
});

function checkRoleforUser() {
    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url: "/userrole",
        cache: false,
        success: function(result) {
            if (result.includes("[USER]")) {
                roleUser = true;
                $(".buttonContainer").hide();
                $("#addButton").hide();
                $("#actionshead").hide();
            }
            showStudentTable();
            return true;
        },
        error: function(err, xhr) {
            alert("Error: checking role");
            return false;
        }

    });

}
function showStudentTable() {

    $("#studentTableData").remove();
    $("#studentTableData_wrapper").remove();

    htmlForTable = '<table class ="table" id="studentTableData"><thead><tr><th>Sr.</th><th>Roll no.</th><th>Student Name</th><th>Address</th><th>Phone</th><th>Courses Enrolled</th><th id="actionshead">Actions</th></tr></thead><tbody>';

    htmlForTableData = '';
    $.getJSON('/studentlist', function(json) {

        for (var i = 0; i < json.length; i++) {
            var sr = i + 1;
            htmlForTableData += '<tr>' + '<td>' + sr + '</td>' + '<td>' + json[i].rollno + '</td>' + '<td>' + json[i].name + '</td>' + '<td>' + json[i].address + '</td>' + '<td>' + json[i].phone + '</td>';

            var coursesName = "";

            if (json[i].coursesList.length == 0) {
                coursesName = '<td style="color:red">' + "Not enrolled in any course."
            } else {
            coursesName = '<td>';
                for(let j=0;j<json[i].coursesList.length;j++){
                    if(j==0){
                     coursesName+=json[i].coursesList[j].courseName;
                     }
                     else{
                     coursesName+=', '+json[i].coursesList[j].courseName ;
                     }
                    if(coursesName.length>30){
                        coursesName+=',..'
                        break;
                    }
                }
            }
            htmlForTableData += coursesName + '</td>'

            if (roleUser === true) {
                htmlForTableData += '<td class=\'buttonContainer\'><button  class=\'view\' >view more</button></td>';
            } else {
                htmlForTableData += '<td class=\'buttonContainer\'><button class=\'edit\'>Edit</button>&nbsp;&nbsp;<button  class=\'delete\' id=' + json[i].id + '>Delete</button><button  class=\'view\' >view more</button></td>';
            }

            htmlForTableData += '</tr>';


        }
        htmlForTable = htmlForTable + htmlForTableData + '</tbody></table>';
        $('#tableContainer').append(htmlForTable);
        $('#studentTableData').DataTable();


    });
};


$(document).delegate('#delete_details_button', 'click', function() {

    var rollno = $('#delete_rollno').val();

    $.ajax({
        type: "DELETE",
        url: "/student/delete/" + rollno,
        cache: false,
        success: function() {
            showStudentTable();
            $('#delete_modal').modal('hide');
        },
        error: function(xhr) {
            alert("Error: record delete");
        }
    });
});
$(document).delegate('.delete', 'click', function() {
    let rollno, name;
    var $row = $(this).closest("tr");

    var $tds = $row.find("td:nth-child(2)");
    $.each($tds, function() {
        rollno = $(this).text();

    });
    var $tds = $row.find("td:nth-child(3)");
    $.each($tds, function() {
        name = $(this).text();
    });
    $('#delete_modal').modal('show')
    $('#delete_name').val(name);
    $('#delete_rollno').val(rollno);

});


$(document).on('hide.bs.modal', '#addModal', function(e) {
    $('#add_student_form').each(function() {
        this.reset();
    });
    $("#form_rollno").css("border-bottom", "none");
    $("#form_name").css("border-bottom", "none");
    $("#form_address").css("border-bottom", "none");
    $("#form_phone").css("border-bottom", "none");
    $("#name_error_message").hide();
    $("#address_error_message").hide();
    $("#phone_error_message").hide();
    $("#rollno_error_message").hide();
    $('input.add[type=checkbox]').prop('checked', false);
});
$(document).on('show.bs.modal', '#addModal', function(e) {
    $('#add_student_form').each(function() {
        this.reset();
    });
    addCoursesToModal();
});
function addCoursesToModal() {

    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url: "/course/list",
        cache: false,
        success: function(result) {
            addCoursesFromJSONToAddModal(result);
            return;
        },
        error: function() {
            alert("Error: fetching courses");
        }

    });
}
function addCoursesFromJSONToAddModal(json) {
    var htmlforCourseList = '<div class="form-check form-check-inline"><h5>Available Courses</h5>'

    for (var i = 0; i < json.length; i++) {

        let checkValue = '{' + '"courseId":"' + json[i].courseId + '"}';

        htmlforCourseList += '<input class="form-check-input add" type="checkbox" id="' + json[i].courseName + '" value=' + checkValue + '>';
        htmlforCourseList += '<label class="form-check-label" for="' + json[i].courseName + 'CheckBox">' + json[i].courseName + '</label><br>';

    }
    htmlforCourseList += '</div>';
    $('.courseSelect').html(htmlforCourseList);


}
function addNewStudentToDB() {
    var name = $("#form_name").val();
    var address = $("#form_address").val();
    var phone = $("#form_phone").val();
    var rollno = $("#form_rollno").val();
    var coursesArray = [];

    $("input.add[type=checkbox]:checked").each(function() {
        coursesArray.push(JSON.parse($(this).val()));

    });

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/student/save",
        data: JSON.stringify({
            'name': name,
            'rollno': rollno,
            'address': address,
            'phone': phone,
            'coursesList': coursesArray
        }),
        cache: false,
        success: function(result) {
            showStudentTable();
            $('#addModal').modal('hide');
            $('#add_student_form').each(function() {
                this.reset();
            });

            return true;
        },
        error: function(err, xhr) {
            alert("Error: Roll no. already exists!!");
            return false;
        }
    });
};


function editStudentDetails() {

    var name = $('#edit_name').val();
    var address = $('#edit_address').val();
    var phone = $('#edit_phone').val();
    var rollno = $('#edit_rollno').val();
    var coursesArray = [];

    $("input.editcheckbox[type=checkbox]:checked ").each(function() {
        coursesArray.push(JSON.parse($(this).val()));
    });

    $.ajax({
        type: "PUT",
        contentType: "application/json; charset=utf-8",
        url: "/student/save",
        cache: false,
        data: JSON.stringify({
            'name': name,
            'rollno': rollno,
            'address': address,
            'phone': phone,
            'coursesList': coursesArray
        }),
        success: function() {
            showStudentTable();
            $('#edit_modal').modal('hide');
        },
        error: function() {
            alert("Error: edit student data");
        }
    });
}
$(document).delegate('.edit', 'click', function() {

            let sr,rollno;
            var $row = $(this).closest("tr");
            var $tds = $row.find("td:nth-child(1)");
            $.each($tds, function() {
                sr = $(this).text();
            });
            $('#edit_sr').val(sr);
            var $tds = $row.find("td:nth-child(2)");
            $.each($tds, function() {
                rollno = $(this).text();
            });
            $('#edit_rollno').val(rollno);

            addCoursesToEditModal(rollno);


            function getAllDataforStudentinEditModal(rollno) {

            $.ajax({
                                type: "GET",
                                url: "/student/" + rollno,
                                cache: false,
                                success: function(result) {
                                    console.log(result);

                                    $('#edit_name').val(result.name);
                                    $('#edit_address').val(result.address);
                                    $('#edit_phone').val(result.phone);

                                    for (let i = 0; i < result.coursesList.length; i++) {
                                        var valueCheckbox = JSON.stringify(result.coursesList[i].courseId).replaceAll('"', '');
                                        var newVal='{"courseId":"'+valueCheckbox+'"}';
                                        $("input.editcheckbox[type=checkbox]").each(function() {

                                            var courseVal = $(this).val();
                                            console.log( "from edit modal: "+courseVal);
                                            if (courseVal === newVal) {
                                                console.log("checked");
                                                $(this).prop('checked',true);
                                            }

                                        });
                                    }

                                    $('#edit_modal').modal('show');
                                    return true;

                                },
                                error: function(xhr) {
                                    alert("Error: fetching details");
                                    return false;
                                }
                            });

            }
            function addCoursesToEditModal(rollno) {

                $.ajax({
                    type: "GET",
                    contentType: "application/json; charset=utf-8",
                    url: "/course/list",
                    cache: false,
                    success: function(json) {
                        var htmlforCourseList = '<div class="form-check form-check-inline"><h5>Available Courses</h5>'

                            for (var i = 0; i < json.length; i++) {

                                let checkValue = '{' + '"courseId":"' + json[i].courseId + '"}';
                                htmlforCourseList += '<input class="editcheckbox form-check-input" type="checkbox" id="' + json[i].courseName + '" value=' + checkValue + '>';
                                htmlforCourseList += '<label class="form-check-label" for="' + json[i].courseName + 'CheckBox">' + json[i].courseName + '</label><br>';

                            }
                            htmlforCourseList += '</div>';
                            $('.courseSelectEdit').html(htmlforCourseList);

                            getAllDataforStudentinEditModal(rollno);

                        return true;
                    },
                    error: function() {
                        alert("Error: fetching courses");
                        return false;
                    }

                });
            }

});


$(document).on('hide.bs.modal', '#edit_modal', function(e) {

    $("#edit_name").css("border-bottom", "none");
    $("#edit_address").css("border-bottom", "none");
    $("#edit_phone").css("border-bottom", "none");
    $('input.editcheckbox[type=checkbox]').prop('checked', false);
    $("#edit_name_error_message").hide();
    $("#edit_address_error_message").hide();
    $("#edit_phone_error_message").hide();
});


$(document).delegate('.view', 'click', function() {
    let rollno;
    var $row = $(this).closest("tr");

    var $tds = $row.find("td:nth-child(2)");
    $.each($tds, function() {
        rollno = $(this).text();
    });

    window.location = "/studentDetails/" + rollno;
});
var roleUser = false;
var name, address, phone;
var courseCheckedSet;

$(document).ready(function () {
    updateTable();
});

$(function () {
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


    $("#form_name").focusout(function () {
        check_name();
    });
    $("#form_address").focusout(function () {
        check_address();
    });
    $("#form_phone").focusout(function () {
        check_phone();
    });
    $("#form_rollno").focusout(function () {
        check_rollno();
    });

    $("#edit_name").focusout(function () {
        check_edit_name();
    });
    $("#edit_address").focusout(function () {
        check_edit_address();
    });
    $("#edit_phone").focusout(function () {
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

    $("#add_button").click(function () {
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

    $("#edit_details_button").click(function () {
        error_edit_name = false;
        error_edit_address = false;
        error_edit_phone = false;

        var updatedName = $('#edit_name').val();
        var updatedAddress = $('#edit_address').val();
        var updatedPhone = $('#edit_phone').val();
        var updatedCourseSet = new Set();
        $("input.editcheckbox[type=checkbox]:checked ").each(function () {
            var courseId = $(this).val();
            updatedCourseSet.add(courseId);
        });
        check_edit_name();
        check_edit_address();
        check_edit_phone();


        if (error_edit_name === false && error_edit_address === false && error_edit_phone === false) {
            if (updatedName === name && updatedAddress === address && updatedPhone === phone && eqSet(updatedCourseSet, courseCheckedSet)) {
                alert("Nothing to update!!");
            } else {
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

function updateTable() {
    var role = $('#role').text();
    var t = $('#studentTable').dataTable({
            "ajax": {
                "url": "/student/list",
                "dataSrc": ""
            },
            "columns": [
                {
                    "data": null,
                    render: function (data, type, row, meta) {
                        return meta.row + meta.settings._iDisplayStart + 1;
                    }
                },
                {"data": "rollno"},
                {"data": "name"},
                {"data": "address"},
                {"data": "phone"},
                {
                    "data": "coursesList[]",
                    render: function (data, row) {
                        var output = '';
                        $.each(data, function (index, item) {
                            if (index != 0)
                                output += ", " + data[index].courseName;
                            else {
                                output += data[index].courseName;
                            }

                        });
                        if (output.length > 30) {
                            output = output.substring(0, 27) + '...';
                        } else if (output === '') {
                            output = '<span style="color:red">Not enrolled in any course</span>';
                        }
                        return output;
                    }
                },
                {
                    "data": null,
                    render: function (data, type, full) {
                        if (role === '[ADMIN]') {

                            return '<div class="buttonContainer"><button class="edit">Edit</button><button class="delete">Delete</button><button class="view">View More</button></div>';
                        } else {
                            return '<div class="buttonContainer"><button class="view">View More</button></div>';

                        }
                    }
                }
            ],
            "bDestroy": true,
            "responsive": true

        }
    );
    new $.fn.dataTable.FixedHeader(t);


}

$(document).delegate('#delete_details_button', 'click', function () {

    var rollno = $('#delete_rollno').val();

    $.ajax({
        type: "DELETE",
        url: "/student/" + rollno,
        cache: false,
        success: function (data) {
            $('#successBlock').html(data);
            $('#successModal').modal('show');
            updateTable();
            $('#delete_modal').modal('hide');
        },
        error: function (xhr) {
            alert("Error: record delete");
        }
    });
});
$(document).delegate('.delete', 'click', function () {
    let rollno, name;
    var $current_row = $(this).parents('tr');
    if ($current_row.hasClass('child')) {
        $current_row = $current_row.prev();
    }
    var $tds = $current_row.find("td:nth-child(2)");
    $.each($tds, function () {
        rollno = $(this).text();
    });
    var $tds = $current_row.find("td:nth-child(3)");
    $.each($tds, function () {
        name = $(this).text();
    });
    $('#delete_modal').modal('show')
    $('#delete_name').val(name);
    $('#delete_rollno').val(rollno);

});
$(document).on('hide.bs.modal', '#addModal', function (e) {
    $('#add_student_form').each(function () {
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
$(document).on('show.bs.modal', '#addModal', function (e) {
    $('#add_student_form').each(function () {
        this.reset();
    });
});

function addNewStudentToDB() {
    var name = $("#form_name").val();
    var address = $("#form_address").val();
    var phone = $("#form_phone").val();
    var rollno = $("#form_rollno").val();
    var coursesArray = [];

    $("input.add[type=checkbox]:checked").each(function () {
        var courseId = {courseId: $(this).val()};
        coursesArray.push(courseId);
    });

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/student",
        data: JSON.stringify({
            'name': name,
            'rollno': rollno,
            'address': address,
            'phone': phone,
            'coursesList': coursesArray
        }),
        cache: false,
        success: function (data) {
            $('#successBlock').html(data);
            $('#successModal').modal('show');
            updateTable();
            $('#addModal').modal('hide');
            $('#add_student_form').each(function () {
                this.reset();
            });


            return true;
        },
        error: function (err, xhr) {
            alert("Error:" + err.responseText);
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

    $("input.editcheckbox[type=checkbox]:checked ").each(function () {
        var courseId = {courseId: $(this).val()};
        coursesArray.push(courseId);
    });

    $.ajax({
        type: "PUT",
        contentType: "application/json; charset=utf-8",
        url: "/student",
        cache: false,
        data: JSON.stringify({
            'name': name,
            'rollno': rollno,
            'address': address,
            'phone': phone,
            'coursesList': coursesArray
        }),
        success: function (data) {
            $('#successBlock').html(data);
            $('#successModal').modal('show');
            updateTable();
            $('#edit_modal').modal('hide');
        },
        error: function () {
            alert("Error: edit student data");
        }
    });
}

$(document).delegate('.edit', 'click', function () {

    var $current_row = $(this).parents('tr');
    if ($current_row.hasClass('child')) {
        $current_row = $current_row.prev();
    }
    var $tds = $current_row.find("td:nth-child(2)");
    $.each($tds, function () {
        rollno = $(this).text();
    });
    $('#edit_rollno').val(rollno);
    getAllDataforStudentinEditModal(rollno);

    function getAllDataforStudentinEditModal(rollno) {

        $.ajax({
            type: "GET",
            url: "/student/" + rollno,
            cache: false,
            success: function (result) {

                $('#edit_name').val(result.name);
                $('#edit_address').val(result.address);
                $('#edit_phone').val(result.phone);
                name = result.name;
                address = result.address;
                phone = result.phone;
                const courseSet = new Set();
                courseCheckedSet = new Set();

                for (let i = 0; i < result.coursesList.length; i++) {
                    var valueCourse = JSON.stringify(result.coursesList[i].courseId).replaceAll('"', '');
                    courseSet.add(valueCourse);
                }
                $("input.editcheckbox[type=checkbox]").each(function () {
                    var courseVal = $(this).val();
                    if (courseSet.has(courseVal)) {
                        $(this).prop('checked', true);
                        courseCheckedSet.add(courseVal);
                    }
                });


                $('#edit_modal').modal('show');
                return true;

            },
            error: function (xhr) {
                alert("Error: fetching details");
                return false;
            }
        });

    }

});
$(document).on('hide.bs.modal', '#edit_modal', function (e) {

    $("#edit_name").css("border-bottom", "none");
    $("#edit_address").css("border-bottom", "none");
    $("#edit_phone").css("border-bottom", "none");
    $('input.editcheckbox[type=checkbox]').prop('checked', false);
    $("#edit_name_error_message").hide();
    $("#edit_address_error_message").hide();
    $("#edit_phone_error_message").hide();
});
$(document).delegate('.view', 'click', function () {
    let rollno;
    var $current_row = $(this).parents('tr');
    if ($current_row.hasClass('child')) {
        $current_row = $current_row.prev();
    }
    var $tds = $current_row.find("td:nth-child(2)");
    $.each($tds, function () {
        rollno = $(this).text();
    });

    window.location = "/student/details/" + rollno;
});
var firstName, lastName, address, phone;
var courseCheckedSet;

$(document).ready(function () {
    updateTable();
});

function updateTable() {
    var role = $('#role').text();


    var t = $('#teachersTable').dataTable({
            "ajax": {
                "url": "/teachers/list",
                "dataSrc": ""
            },
            "columns": [
                {
                    "data": null,
                    render: function (data, type, row, meta) {
                        return meta.row + meta.settings._iDisplayStart + 1;
                    }
                },
                {"data": "teacherId"},
                {"data": "firstName"},
                {"data": "lastName"},
                {"data": "address"},
                {"data": "phone"},
                {
                    "data": "courseSet[]",
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

$(function () {
    $("teacherId_error_message").hide();
    $("firstName_error_message").hide();
    $("lastName_error_message").hide();
    $("address_error_message").hide();
    $("phone_error_message").hide();


    $("edit_firstName_error_message").hide();
    $("edit_lastName_error_message").hide();
    $("edit_address_error_message").hide();
    $("edit_phone_error_message").hide();

    var error_firstName = false;
    var error_lastName = false;
    var error_address = false;
    var error_phone = false;
    var error_teacherId = false;

    var error_edit_lastName = false;
    var error_edit_address = false;
    var error_edit_phone = false;
    var error_edit_firstName = false;

    $("#teacherId").focusout(function () {
        check_teacherId();
    });
    $("#address").focusout(function () {
        check_address();
    });
    $("#phone").focusout(function () {
        check_phone();
    });
    $("#firstName").focusout(function () {
        check_firstName();
    });
    $("#lastName").focusout(function () {
        check_lastName();
    });

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

    $("#add_button").click(function () {
        error_firstName = false;
        error_lastName = false;
        error_address = false;
        error_phone = false;
        error_teacherId = false;

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

    $("#edit_address").focusout(function () {
        check_edit_address();
    });
    $("#edti_phone").focusout(function () {
        check_edit_phone();
    });
    $("#edit_firstName").focusout(function () {
        check_edit_firstName();
    });
    $("#edit_lastName").focusout(function () {
        check_edit_lastName();
    });

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

    function check_edit_firstName() {
        var pattern = /^[a-zA-Z ]*$/;
        var name = $("#edit_firstName").val().trim();
        if (pattern.test(name) && name !== '') {
            $("#edit_firstName_error_message").hide();
            $("#edit_firstName").css("border-bottom", "2px solid #34F458");
        } else {
            $("#edit_firstName_error_message").html("Should contain only Characters");
            $("#edit_firstName_error_message").show();
            $("#edit_firstName").css("border-bottom", "2px solid #F90A0A");
            error_edit_firstName = true;
        }
    }

    function check_edit_lastName() {
        var pattern = /^[a-zA-Z ]*$/;
        var name = $("#edit_lastName").val().trim();
        if (pattern.test(name) && name !== '') {
            $("#edit_lastName_error_message").hide();
            $("#edit_lastName").css("border-bottom", "2px solid #34F458");
        } else {
            $("#edit_lastName_error_message").html("Should contain only Characters");
            $("#edit_lastName_error_message").show();
            $("#edit_lastName").css("border-bottom", "2px solid #F90A0A");
            error_edit_lastName = true;
        }
    }

    $("#edit_details_button").click(function () {
        error_edit_firstName = false;
        error_edit_lastName = false;
        error_edit_address = false;
        error_edit_phone = false;

        var updatedfirstName = $('#edit_firstName').val();
        var updatedlastName = $('#edit_lastName').val();
        var updatedAddress = $('#edit_address').val();
        var updatedPhone = $('#edit_phone').val();
        var updatedCourseSet = new Set();
        $("input.editcheckbox[type=checkbox]:checked ").each(function () {
            var courseId = $(this).val();
            updatedCourseSet.add(courseId);
        });
        check_edit_firstName();
        check_edit_lastName();
        check_edit_address();
        check_edit_phone();


        if (error_edit_firstName === false && error_edit_lastName === false && error_edit_address === false && error_edit_phone === false) {
            if (updatedfirstName === firstName && updatedlastName === lastName && updatedAddress === address && updatedPhone === phone && eqSet(updatedCourseSet, courseCheckedSet)) {
                alert("Nothing to update!!");
            } else {
                editTeacherDetails();
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
$(document).on('hide.bs.modal', '#addModal', function (e) {
    $('#registerTeacherform').each(function () {
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

function addNewTeacherToDB() {

    var firstName = $("#firstName").val();
    var lastName = $("#lastName").val();
    var address = $("#address").val();
    var phone = $("#phone").val();
    var teacherId = $("#teacherId").val();
    var coursesArray = [];

    $("input.add[type=checkbox]:checked").each(function () {
        var courseId = {courseId: $(this).val()};
        coursesArray.push(courseId);
    });

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/teachers",
        data: JSON.stringify({
            'firstName': firstName,
            'lastName': lastName,
            'teacherId': teacherId,
            'address': address,
            'phone': phone,
            'courseSet': coursesArray
        }),
        cache: false,
        success: function (data) {
            $('#successBlock').html(data);
            $('#successModal').modal('show');
            updateTable();
            $('#addModal').modal('hide');
            $('#registerTeacherform').each(function () {
                this.reset();
            });
            return true;
        },
        error: function (err, data) {
            console.log(err.responseText);
            alert("Error:" + err.responseText);
            return false;
        }
    });
}

function editTeacherDetails() {

    var firstName = $('#edit_firstName').val();
    var lastName = $('#edit_lastName').val();
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
        url: "/teachers",
        cache: false,
        data: JSON.stringify({
            'firstName': firstName,
            'lastName': lastName,
            'teacherId': teacherId,
            'address': address,
            'phone': phone,
            'courseSet': coursesArray
        }),
        success: function (data) {
            $('#successBlock').html(data);
            $('#successModal').modal('show');
            updateTable();
            $('#edit_modal').modal('hide');
        },
        error: function (err, data) {
            console.log(err.responseText);
            alert("Error:" + err.responseText);
            return false;
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
        teacherId = $(this).text();
    });
    $('#edit_teacherId').val(teacherId);

    getAllDataforTeacherinEditModal(teacherId);

    function getAllDataforTeacherinEditModal(teacherId) {

        $.ajax({
            type: "GET",
            url: "/teachers/" + teacherId,
            cache: false,
            success: function (result) {

                $('#edit_firstName').val(result.firstName);
                $('#edit_lastName').val(result.lastName);
                $('#edit_address').val(result.address);
                $('#edit_phone').val(result.phone);


                firstName = result.firstName;
                lastName = result.lastName;
                address = result.address;
                phone = result.phone;
                const courseSet = new Set();
                courseCheckedSet = new Set();

                for (let i = 0; i < result.courseSet.length; i++) {
                    var valueCourse = JSON.stringify(result.courseSet[i].courseId).replaceAll('"', '');
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

    $("#edit_firstName").css("border-bottom", "none");
    $("#edit_lastName").css("border-bottom", "none");
    $("#edit_address").css("border-bottom", "none");
    $("#edit_phone").css("border-bottom", "none");
    $('input.editcheckbox[type=checkbox]').prop('checked', false);
    $("#edit_firstName_error_message").hide();
    $("#edit_lastName_error_message").hide();
    $("#edit_address_error_message").hide();
    $("#edit_phone_error_message").hide();
});
$(document).delegate('#delete_details_button', 'click', function () {

    var teacherId = $('#delete_teacherId').val();

    $.ajax({
        type: "DELETE",
        url: "/teachers/" + teacherId,
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
    let teacherId;
    var $current_row = $(this).parents('tr');
    if ($current_row.hasClass('child')) {
        $current_row = $current_row.prev();
    }
    var $tds = $current_row.find("td:nth-child(2)");
    $.each($tds, function () {
        teacherId = $(this).text();
    });

    $('#delete_modal').modal('show')
    $('#delete_teacherId').val(teacherId);

});
$(document).delegate('.view', 'click', function () {
    let teacherId;
    var $current_row = $(this).parents('tr');
    if ($current_row.hasClass('child')) {
        $current_row = $current_row.prev();
    }
    var $tds = $current_row.find("td:nth-child(2)");
    $.each($tds, function () {
        teacherId = $(this).text();
    });

    window.location = "/teachers/details/" + teacherId;
});

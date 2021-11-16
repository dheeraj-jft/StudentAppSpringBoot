$(document).ready(function () {
    updateTable();
});


$(function () {
    $("courseId_error_message").hide();
    $("courseName_error_message").hide();
    $("edit_name_error_message").hide();

    var error_name = false;
    var error_id = false;
    var error_edit_name = false;

    $("#form_courseName").focusout(function () {
        check_name();
    });
    $("#form_courseId").focusout(function () {
        check_id();
    });
    $("#edit_name").focusout(function () {
        check_edit_name();
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

    $("#edit_details_button").click(function () {
        error_edit_name = false;
        check_edit_name();

        if (error_edit_name === false) {
            editCourse();
            return true;
        } else {
            alert("Please Fill the form Correctly");
            return false;
        }
    });


    $("#add_form_button").click(function () {
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

function editCourse() {

    let courseName = $("#edit_name").val().trim();
    let courseId = $("#edit_id").val().trim();
    $.ajax({
        type: "PUT",
        contentType: "application/json; charset=utf-8",
        url: "/course",
        data: JSON.stringify({
            'courseId': courseId,
            'courseName': courseName
        }),
        cache: false,
        success: function (data) {
            $('#successBlock').html(data);
            $('#successModal').modal('show');
            $('#edit_modal').modal('hide');
            updateTable();
            return true;
        },
        error: function (err, xhr) {
            alert("Error: Edit Name!!");
            return false;
        }
    });
}

function addCourse() {

    let courseId = $("#form_courseId").val().trim();
    let courseName = $("#form_courseName").val().trim();
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/course",
        data: JSON.stringify({
            'courseId': courseId,
            'courseName': courseName,
        }),
        cache: false,
        success: function (data) {
            $('#successBlock').html(data);
            $('#successModal').modal('show');
            $('#addModal').modal('hide');
            $('#add_student_form').each(function () {
                this.reset();
            });
            updateTable();
            return true;
        },
        error: function (err, xhr) {
            alert("Error: Course Id. already exists!!");
            return false;
        }
    });
}

function updateTable() {
    var role = $('#role').text();
    console.log(role);

    var t = $('#coursesTable').dataTable({
            "ajax": {
                "url": "/course/list",
                "dataSrc": ""
            },
            "columns": [
                {
                    "data": null,
                    render: function (data, type, row, meta) {
                        return meta.row + meta.settings._iDisplayStart + 1;
                    }
                },
                {"data": "courseId"},
                {"data": "courseName"},
                {
                    "data": "teacher.teacherId",
                    "defaultContent": "No Teacher Alloted"
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

$(document).on('hide.bs.modal', '#addModal', function (e) {
    $('#add_student_form').each(function () {
        this.reset();
    });
    $("#form_courseId").css("border-bottom", "none");
    $("#form_courseName").css("border-bottom", "none");

    $("#courseName_error_message").hide();
    $("#courseId_error_message").hide();
});
$(document).delegate('.view', 'click', function () {
    let courseId;


    var $current_row = $(this).parents('tr');
    if ($current_row.hasClass('child')) {
        $current_row = $current_row.prev();
    }

    var $tds = $current_row.find("td:nth-child(2)");
    $.each($tds, function () {
        courseId = $(this).text();
    });

    window.location = "/course/details/" + courseId;
});
$(document).delegate('.delete', 'click', function () {
    let id, name;
    var $row = $(this).closest("tr");

    var $tds = $row.find("td:nth-child(2)");
    $.each($tds, function () {
        id = $(this).text();

    });
    var $tds = $row.find("td:nth-child(3)");
    $.each($tds, function () {
        name = $(this).text();
    });
    $('#delete_modal').modal('show')
    $('#delete_name').val(name);
    $('#delete_courseId').val(id);

});
$(document).delegate('#delete_details_button', 'click', function () {

    var courseId = $('#delete_courseId').val();
    console.log("Delete " + courseId);
    $.ajax({
        type: "DELETE",
        url: "/course/" + courseId,
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
$(document).delegate('.edit', 'click', function () {
    let id, name;
    var $row = $(this).closest("tr");
    var $tds = $row.find("td:nth-child(2)");
    $.each($tds, function () {
        id = $(this).text();
    });
    var $tds = $row.find("td:nth-child(3)");
    $.each($tds, function () {
        name = $(this).text();
    });

    $('#edit_modal').modal('show')
    $('#edit_name').val(name);
    $('#edit_id').val(id);

});
$(document).on('hide.bs.modal', '#edit_modal', function (e) {

    $("#edit_name").css("border-bottom", "none");
    $("#edit_name_error_message").hide();
});


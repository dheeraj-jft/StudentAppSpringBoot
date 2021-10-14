//initial
$(document).ready(function() {
        showStudentTable();
    });

//auto refresh
$(document).ready(function() {
    setInterval(function(){showStudentTable();},200000);
    });



    function showStudentTable(){
    $("#studentTableData").remove();
    htmlForTable= '<table class ="table" id="studentTableData"><thead><tr><th>Id</th><th>Student Name</th><th>Address</th><th>Phone</th><th>Actions</th></tr></thead><tbody>';
    htmlForTableData='';
    $.getJSON('http://localhost:8080/student/studentlist', function(json) {
				var tr=[];

				for (var i = 0; i < json.length; i++) {
				    htmlForTableData+= '<tr>'+'<td>' + json[i].id + '</td>'+'<td>' + json[i].name + '</td>'+'<td>' + json[i].address + '</td>'+'<td>' + json[i].phone + '</td>'+'<td><button class=\'edit\'>Edit</button>&nbsp;&nbsp;<button  class=\'delete\' id=' + json[i].id + '>Delete</button></td>'+'</tr>';
				}
				htmlForTable= htmlForTable+ htmlForTableData+'</tbody></table>';

				$('#table').append(htmlForTable);
			});
}

//add student call
    $(document).delegate('#addNew', 'click', function(event) {
				event.preventDefault();

				var name = $('#name').val();
				var address = $('#address').val();
				var phone = $('#phone').val();

				$.ajax({
					type: "POST",
					contentType: "application/json; charset=utf-8",
					url: "http://localhost:8080/student/add",
					data: JSON.stringify({'name': name,'address': address,'phone': phone}),
					cache: false,
					success: function(result) {
						showStudentTable();
						$('#myModal').modal('hide');
						console.log("Succesfull")
					},
					error: function(err) {
						console.log("Error")
					}
				});
			});

$(".modal").on("hidden.bs.modal", function(){
    $(this).find('form').trigger('reset');
});


$(document).delegate('.delete', 'click', function() {
				if (confirm('Do you really want to delete record?')) {
					var id = $(this).attr('id');
					var parent = $(this).parent().parent();
					$.ajax({
						type: "DELETE",
						url: "http://localhost:8080/student/delete/" + id,
						cache: false,
						success: function() {
							parent.fadeOut('slow', function() {
								$(this).remove();
							});
							showStudentTable();
						},
						error: function() {
							$('#err').html('<span style=\'color:red; font-weight: bold; font-size: 30px;\'>Error deleting record').fadeIn().fadeOut(4000, function() {
								$(this).remove();
							});
						}
					});
				}
			});




$(document).delegate('.edit', 'click', function() {
                let id,phone,name, address;
                var $row = $(this).closest("tr");
                var $tds = $row.find("td:nth-child(1)");
                $.each($tds, function() {
                 id=$(this).text();
                console.log($(this).text());
                });
                var $tds = $row.find("td:nth-child(2)");
                $.each($tds, function() {
                 name=$(this).text();
                console.log($(this).text());
                });
                var $tds = $row.find("td:nth-child(3)");
                $.each($tds, function() {
                 address=$(this).text();
                console.log($(this).text());
                });
                var $tds = $row.find("td:nth-child(4)");

                $.each($tds, function() {
                phone=$(this).text();
                console.log($(this).text());
                });

				$('#modal_edit').modal('show')
				$('#name_edit').val(name);
				$('#address_edit').val(address);
				$('#phone_edit').val(phone);
				$('#id_edit').val(id);

			});

			$(document).delegate('#save', 'click', function() {
				    console.log("Save");
				    var name= $('#name_edit').val();
				    var address= $('#address_edit').val();
				    var phone= $('#phone_edit').val();
				    var id=$('#id_edit').val();


				$.ajax({
					type: "PUT",
					contentType: "application/json; charset=utf-8",
					url: "http://localhost:8080/student/save",
					data: JSON.stringify({'id': id, 'name': name,'address': address,'phone': phone}),
					cache: false,
					success: function() {
					    showStudentTable();
					    $('#modal_edit').modal('hide');
					},
					error: function() {
						$('#err').html('<span style=\'color:red; font-weight: bold; font-size: 30px;\'>Error updating record').fadeIn().fadeOut(4000, function() {
							$(this).remove();
						});
					}
				});
			});


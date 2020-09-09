var tableData = {
	create: function() {
		if($.fn.DataTable.isDataTable('#tableData')) {
			$('#tableData').DataTable().clear();
			$('#tableData').DataTable().destroy();
		}
		
		$.ajax({
			url: '/person',
			method:'get',
			contentType: 'application/json',
			success: function (res, status, xhr) {
				if(xhr.status == 200 || xhr.status == 201){
					$('#tableData').DataTable({
						data: res,
						columns: [
							{
								title : "NIK",
								data : "nik"
							},
							{
								title : "Nama",
								data : "nama"
							},
							{
								title : "Alamat",
								data : "alamat"
							},
							{
								title : "Tempat Lahir",
								data : "tempatLahir"
							},
							{
								title : "Tanggal Lahir",
								data : "tanggalLahir"
							},
							{
								title : "No Hp",
								data : "noHp"
							},
							{
                                title: "Action",
                                data: null,
                                render: function (data, type, row) {
                                	 console.log(res);
                                    return "<button class='btn-primary' onclick=formBiodata.setEditData('" + data.id + "')>Edit</button>"
                               		
                                }
                               
                            }
						]
					});
				} else {
				}
			},
			error : function (err) {
				console.log(err);
			}
		});
	}
};

var formBiodata = {
	resetForm: function() {
		$('#form-biodata')[0].reset();
		$('#id').val('');
		$('#form-biodata').parsley().reset();
	},
	saveForm: function() {
		if($('#form-biodata').parsley().validate()) {
		var dataResult = getJsonForm($('#form-biodata').serializeArray(), true);
		if ($('#nik').val().length < 16 || $('#nik').val().length > 16){
				const Toast = Swal.mixin({
					  toast: true,
					  position: 'top-end',
					  showConfirmButton: false,
					  timer: 3000,
					  timerProgressBar: true,
					  onOpen: (toast) => {
					    toast.addEventListener('mouseenter', Swal.stopTimer)
					    toast.addEventListener('mouseleave', Swal.resumeTimer)
					  }
					})
					
					Toast.fire({
					  icon: 'warning',
					  title: ' Masukan 16 digit NIK! ' 
					})
			}
			else {
			var dob = $('#tanggalLahir').val();
				dob = new Date(dob);
			var current = new Date();
			var	age = current - dob;
			var	ageGet = Math.floor(age / 1000 / 60 / 60 / 24 / 365.25); // age / ms / sec / min / hour / days in a year
				if(ageGet < 30){
					const Toast = Swal.mixin({
					  toast: true,
					  position: 'top-end',
					  showConfirmButton: false,
					  timer: 3000,
					  timerProgressBar: true,
					  onOpen: (toast) => {
					    toast.addEventListener('mouseenter', Swal.stopTimer)
					    toast.addEventListener('mouseleave', Swal.resumeTimer)
					  }
					})
					
					Toast.fire({
					  icon: 'warning',
					  title: ' Usia Anda Belum Memenuhi ' 
					})
				} else {
				
				$.ajax({
				url: '/person',
				method: 'post',
				contentType: 'application/json',
				dataType: 'json',
				data: JSON.stringify(dataResult),
				success: function (res, status, xhr){
					if(xhr.status == 200 || xhr.status == 201) {
						tableData.create();
						$('#modal-biodata').modal('hide');
						formBiodata.resetForm();
						const Toast = Swal.mixin({
						  toast: true,
						  position: 'top-end',
						  showConfirmButton: false,
						  timer: 3000,
						  timerProgressBar: true,
						  onOpen: (toast) => {
					    toast.addEventListener('mouseenter', Swal.stopTimer)
					    toast.addEventListener('mouseleave', Swal.resumeTimer)
					  }
					})
					
					Toast.fire({
					  icon: 'success',
					  title: ' Sukses ' 
					})
					} else {
					}
				},
				error: function(err) {
					console.log(err);
				}
			});
		}
		}
		}
	},
	setEditData: function (idperson) {
        formBiodata.resetForm();

        $.ajax({
            url: '/person/' + idperson,
            method: 'get',
            contentType: 'application/json',
            dataType: 'json',
            success: function (res, status, xhr) {
                if (xhr.status == 200 || xhr.status == 201) {
                    $('#form-biodata').fromJSON(JSON.stringify(res));
                    $('#modal-biodata').modal('show')

                } else {

                }
            },
            error: function (err) {
                console.log(err);
            }
        });


    }
};
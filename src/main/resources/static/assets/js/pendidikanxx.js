var tableData = {
	create: function() {
		if($.fn.DataTable.isDataTable('#tableData')) {
			$('#tableData').DataTable().clear();
			$('#tableData').DataTable().destroy();
		}
		
		$.ajax({
			url: '/pendidikan',
			method:'get',
			contentType: 'application/json',
			success: function (res, status, xhr) {
				if(xhr.status == 200 || xhr.status == 201){
					$('#tableData').DataTable({
						data: res,
						columns: [
							{
								title : "Id Person",
								data : "idperson"
							},
							{
								title : "Jenjang",
								data : "jenjang"
							},
							{
								title : "Institusi",
								data : "institusi"
							},
							{
								title : "Tahun Masuk",
								data : "tahunMasuk"
							},
							{
								title : "Tahun Lulus",
								data : "tahunLulus"
							},
							{
                                title: "Action",
                                data: null,
                                render: function (data, type, row) {
                                	 console.log(res);
                                    return "<button class='btn-primary' onclick=formPend.setEditData('" + data.id + "')>Edit</button>"
                               		
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

var formPend = {
	resetForm: function() {
		$('#form-pend')[0].reset();
	},
	saveForm: function() {
		if($('#form-pend').parsley().validate()) {
		var dataResult = getJsonForm($('#form-pend').serializeArray(),true);
		var arr = [];
		arr.push(dataResult)
		$.ajax({
		url: '/pendidikan?idperson='+ $('#idperson').val(),
		method: 'post',
		contentType: 'application/json',
		dataType: 'json',
		data: JSON.stringify(arr),
		success: function (res,status,xhr){
			if(xhr.status == 200 || xhr.status == 201) {
				tableData.create();
				$('#modal-pend').modal('hide')
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
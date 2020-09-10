var array = [];
var formPend = {
	resetForm: function() {
		$('#form-pend')[0].reset();
		$('#id').val('');
		$('#idPerson').val('');
		$('#form-pend').parsley().reset();
	},
	addPend: function() {
		if($('#form-pend').parsley().validate()) {
			var dataResult = getJsonForm($('#form-pend').serializeArray(),true);
			array.push(dataResult);
			
			if($.fn.DataTable.isDataTable('#tableData')) {
			$('#tableData').DataTable().clear();
			$('#tableData').DataTable().destroy();
			}
			
			$('#tableData').DataTable({
				data: array,
				columns: [
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
                     	 console.log(array);
                         return "<button type='button' class='btn-sm btn-danger' onclick=formPend.deleteData('" + data.id + "')> Delete Data</button>"    		
                     }
                               
                 }
				]
			});
			$('#modal-pend').modal('hide');
		}
	},
	saveData: function() {
	console.log(array);
	$.ajax({
		url: '/pendidikan?idperson='+ $('#idperson').val(),
		method: 'post',
		contentType: 'application/json',
		dataType: 'json',
		data: JSON.stringify(array),
		success: function (res,status,xhr){
			if(xhr.status == 200 || xhr.status == 201) {
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
			$('#tableData').DataTable().clear();
			$('#tableData').DataTable().destroy();
			} else {
			}
		},
		error: function(err) {
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
				  title: ' Error ' 
				})
			console.log(err);
		}
		});
	},	
	deleteData: function() {
		$('#tableData').DataTable().clear();
		$('#tableData').DataTable().destroy();
		$('input[name=idPerson]').val(' ');
		formPend.resetForm();
	}
}
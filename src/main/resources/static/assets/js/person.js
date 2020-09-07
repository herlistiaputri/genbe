var formBiodata = {
	resetForm: function() {
		$('#form-biodata')[0].reset();
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
				}else{
				$.ajax({
				url: '/person/input',
				method: 'post',
				contentType: 'application/json',
				dataType: 'json',
				data: JSON.stringify(dataResult),
				success: function (res, status, xhr){
					if(xhr.status == 200 || xhr.status == 201) {
						$('#modal-biodata').modal('hide')
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
	}
};
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
				$('#modal-pend').modal('hide')
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
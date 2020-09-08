var dataTabel = {
	create: function() {
	if($.fn.DataTable.isDataTable('#tableData')) {
			$('#tableData').DataTable().clear();
			$('#tableData').DataTable().destroy();
		}
		$.ajax({
            url: '/person/data',
            method: 'get',
            contentType: 'application/json',
            dataType: 'json',
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
								title : "Umur",
								data : "umur"
							},
							{
								title : "Pendidikan Terakhir",
								data : "pendTerakhir"
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
}

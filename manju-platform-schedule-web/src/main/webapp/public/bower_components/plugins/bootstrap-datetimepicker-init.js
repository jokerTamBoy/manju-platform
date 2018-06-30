$('.form_datetime').datetimepicker({
		    weekStart: 1,
		    todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			forceParse: 0,
		    showMeridian: 1
		});
		$('.form_date').datetimepicker({
		    weekStart: 1,
		    todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
		});
		$('.form_time').datetimepicker({
		    weekStart: 1,
		    todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 1,
			minView: 0,
			maxView: 1,
			forceParse: 0
		});
        // $(function () {
        //     $('#startTime').datetimepicker();
        // });
		 $(function() {
			    // Bootstrap DateTimePicker v3
			    $('#startTime').datetimepicker({
                    pickTime: false
			    });
			    // Bootstrap DateTimePicker v4
			    $('#endTime').datetimepicker({
                    pickTime: false
			    });
			  });
		
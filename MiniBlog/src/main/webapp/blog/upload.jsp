<%@ page contentType="text/html; charset=utf-8" %>
<form name='uploadform' action="upload" method="post" enctype="multipart/form-data" target='ifrmHidden' >
	<input type='file' name='filename'/>
</form>

<script>	
	$(function() {
		$("input[name='filename']").change(function() {
			uploadform.submit();
		});
	});
</script>
$(document).ready(function() {

	loadThumbnails();
});

$("input:file").change(function(objEvent) {
	
	var objFormData = new FormData();
    
	// GET FILE OBJECT 
    var objFile = $(this)[0].files[0];
    
    // APPEND FILE TO POST DATA
    objFormData.append('file', objFile);
    $.ajax({
    	
        url: 'gallery/pushToGallery',
        type: 'POST',
        contentType: false,
        data: objFormData,
        processData: false, //JQUERY CONVERT THE FILES ARRAYS INTO STRINGS. So processData:false
        success: function(data) { loadThumbnails(); }
    });
});

function loadThumbnails() {

    $.ajax({
    	
        url: "gallery/pullFromGallery"
    }).then(function(data) {
    	
    	$('.thumbnails').text('');
    	
    	$(data.images).each(function(index, image) {
    		
    		var div = document.createElement('div');
    		div.className = 'col-sm-4';
    		div.innerHTML = '<img src="' + image.thumbnailPath + '" title="' + image.thumbnailFileName + '" /><p>' + image.thumbnailFileName + '</p>';
    		
        	$('.thumbnails').append(div);
    	});
    });
}
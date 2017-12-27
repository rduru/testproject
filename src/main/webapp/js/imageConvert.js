$(function (){
	$(".upImg").val("");
	$("#detail").val("");
 
    $("body").on("change", ".upImg", function (){
        preview(this);
    })
    
    $("#pica_quality").numberspinner({
    	min:1,
    	max:100,
    	editable:true
    })
    
    $("#gs_smooth").numberspinner({
    	min:1,
    	editable:true
    })
    
    $("#blur_radis").numberspinner({
    	min:1,
    	editable:true
    })
    
    $("#sharp_radis").numberspinner({
//    	min:1,
    	editable:true
    })
    
    $("#jpeg_quality").numberspinner({
    	min:1,
    	max:100,
    	editable:true
    })
    
    $("#pica_quality").numberspinner("setValue", 90);
    $("#jpeg_quality").numberspinner("setValue", 85);
    $("#gs_smooth").numberspinner("setValue", 1);
    $("#scarl_quality").combobox("setValue", -1);
    
    $("#submit").click(function(){
    	$("#detail").val("");
    	
    	var imgBase64 = "";
    	var pica_quality = "";
    	var scarl_quality = "";
    	
    	var img_file = $(".upImg").prop('files')[0];
    	var width;
        var reader = new FileReader();
        reader.onload = function (e) {
	        var KB = format_float(e.total / 1024, 2);
	        outputLog("檔案大小 : " + KB + " KB");
	        

	        var image = new Image();
	        image.src = e.target.result;

	        image.onload = function() {
	            // access image size here 
//	           alert(this.width);

	           outputLog("圖片尺寸 : "+this.width+"x"+this.height);
	        	var options = {
	    				maxWidth: 2000,
	        			canvas: true,
	        			pixelRatio: window.devicePixelRatio,
//	        			pixelRatio:1,
	        			downsamplingRatio: 0.5,
	        			orientation: true
	        	};
	        	displayImage(img_file, options);
	        };
        }
        reader.readAsDataURL(img_file);
//    	console.log(img_file);
    })
})

function dropChangeHandler (e) {
	var target = e.dataTransfer || e.target;
	var file = target && target.files && target.files[0];
	var options = {
			maxWidth: $('#result').width(),
			canvas: true,
			pixelRatio: window.devicePixelRatio,
			downsamplingRatio: 0.5,
			orientation: true
	};
	if (!file) {
		return;
	}
	displayImage(file, options);
}

function updateResults (img, data) {
	
//	alert(supportsToDataURL());
//	uploadImage(img, data);
//	outputLog(img);
//	outputLog(data);
    outputLog("圖片尺寸 : "+img.width+"x"+img.height);
	//====================
	var pica_quality = $("#pica_quality").numberspinner("getValue");
	var width = img.width;
	var height = img.height;
	/*
	 * pica初始化
	 * resizer_mode 選擇要使用哪些功能模組
	 */
	var resizer_mode = {
			  js:   true,
			  wasm: true,
			  cib:  true,
			  ww:   true
			};
	var opts = [];
	Object.keys(resizer_mode).forEach(function (k) {
		if (resizer_mode[k]) opts.push(k);
	});
	//取得pica物件
	resizer = window.pica({ features: opts });
	
	var reader  = new FileReader();
	var base64 = "";
	/*
	 * 註冊FileReader 的 load事件
	 * 透過這個事件取得圖片的base64編碼
	 */
	reader.addEventListener("load", function (e) {
		base64 = reader.result;
//		base64 = img.toDataURL('image/jpeg', 0.92);
//		alert(base64);
//				window.open(base64);
	    var KB = format_float(e.total / 1024, 2);
	    outputLog("檔案大小 : " + KB + " KB");
	    
		var scarl_quality = $("#scarl_quality").combobox("getValue");
		var jpeg_quality = $("#jpeg_quality").numberspinner("getValue");
		var gs_smooth = $("#gs_smooth").numberspinner("getValue");
		var blur_radis = $("#blur_radis").numberspinner("getValue");
		var sharp_radis = $("#sharp_radis").numberspinner("getValue");
		var target_DPI = $("input[name=targetDPI]:checked").val();
		
		var param = {
				'img':base64,
				'scarl_quality':scarl_quality,
				'jpeg_quality':jpeg_quality,
				'target_DPI':target_DPI,
				'folderName':new Date(),
				'gs_smooth':gs_smooth,
				'blur_radis':blur_radis,
				'sharp_radis':sharp_radis
		}
		
		var xhr = new XMLHttpRequest();
		
	    xhr.upload.addEventListener('progress', function(e) {
	    	var progressMax = 90;//上傳條最大值
	        var done = e.position || e.loaded;
	        var total = e.totalSize || e.total;
	        outputLog('xhr progress: ' + Math.round(done/total*100) + '%');
	    });
	
	    /*
	     * loadstart event handler
	     * 初始化進度
	     */
	    xhr.upload.addEventListener('loadstart', function(e) {
	    	outputLog('upload loadstart')
	    });    
	
	    /*
	     * load event handler
	     * 上傳完成, 接收伺服器回應
	     */
	    xhr.addEventListener('load', function(e) {
	    	outputLog('xhr upload complete');
	    });
	    
	    /*
	     * error event handler
	     * 錯誤處理1
	     */
	    xhr.addEventListener('error', function(e) {
			outputLog("連線錯誤，請確認網路是否正常，或網路速度暢通");
	    });
	   
	    /*
	     * abort event handler
	     * 錯誤處理2
	     */
	    xhr.addEventListener('abort', function(e) {
			outputLog("上傳時產生異常，請重新上傳")
	    });
		    
	    /*
	     * timeout event handler
	     * 錯誤處理3
	     */
	    xhr.addEventListener('timeout', function(e) {
			outputLog("上傳時間過長，請確認網路是否正常，或網路速度暢通");
	    });
	    
	    /*
	     * readystatechange event handler
	     * 錯誤處理4
	     */
	    xhr.addEventListener('readystatechange', function(e) {
	        if (xhr.readyState == 4 ) { // readyState:4 表示此Request已完成操作
	        	if ( xhr.status == 200 ) {
	//				        		success(xhr.responseText); 
		        } else {
					if (xhr.status == 0) {
					} else if (xhr.status == 404) {
						outputLog("HTTP Status 404 - 請確認網路是否正常");
					} else if (xhr.status == 500) {
						outputLog("HTTP Status 500 - 上傳時產生異常，請重新上傳");
					} else {
						outputLog("無法處理的連線狀態  : "+xhr.status); 
					}
		        } 
	        } else if(xhr.readyState == 0){ // readyState:0 XMLHttpRequest 客戶端物件已被建立，但 open() 方法尚未被呼叫。
	        	outputLog('UNSENT', 'XMLHttpRequest 客戶端物件已被建立'); // readyState will be 0
	        } else if(xhr.readyState == 1){ // open() 方法已被呼叫。於此狀態時，可以使用 setRequestHeader() 方法設定請求標頭（request headers），並可呼叫 send() 方法來發送請求。
	        	outputLog('OPENED', ' open() 方法已被呼叫'); // readyState will be 0
	        } else if(xhr.readyState == 2){ // readyState:2 send() 方法已被呼叫，並且已接收到回應標頭（response header）。
	        	outputLog('LOADING', 'send() 方法已被呼叫'); // readyState will be 0
	        } else if(xhr.readyState == 3){ // 正在接收回應內容（response's body）。如 responseType 屬性為 "text" 或空字串，則 responseText 屬性將會在載入的過程中擁有已載入部分之回應（response）內容中的文字。
	        	outputLog('DONE', '正在接收回應內容'); // readyState will be 0
	        } else {
	        	outputLog("無法處理的連線階段  : "+xhr.readyState); 
	        }
	    });
//	    alert("ready to post");
	    xhr.open("POST", "../uploadImage.action", true);
	    xhr.timeout = 60000; // time in milliseconds
	    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
		xhr.send(JSON.stringify(param));
	  }, false);
	
	//check dpi
	var resX = 0;
	var resY = 0;
	var dpi = 0;
	/*
	 * 如果有EXIF資訊的話, 讀取出DPI資訊
	 */
//	alert(data.exif);
	if(data.exif) {
//		resX = data.exif.get('XResolution');
//	    resY = data.exif.get('YResolution');
//	    outputLog("EXIF資訊 : X_DPI:"+resX+", Y_DPI:"+resY);
	}
	/*
	 * 不管有沒有取得EXIF中的DPI資訊, 都透過公式驗算圖片尺寸的對應DPI
	 */
	dpi = Math.sqrt((width*width) + (height*height))/Math.sqrt((8.5*8.5)+(11*11));
	outputLog("xdpi:"+resX+", ydpi:"+resY+", dpi:"+dpi);

    var destCanvas = document.createElement('canvas')
    /*
     * 只要其中一個DPI超過200就將圖片縮放到A4大小
     */
    if(dpi>=200 || resX>=200) {
    	//resize to A4
    	var longRatio = 1;
    	var shortRatio = 1;
    	var a4LongSide = 1762;
        var a4ShortSide = 1041;

//    	var a4LongSide = 881;
//        var a4ShortSide = 520;
        if(width > height) {
        	longRatio = a4LongSide/width;
        	shortRatio =a4ShortSide /height;
        	if(longRatio > shortRatio) {
		        destCanvas.width  = width*shortRatio;
		        destCanvas.height = height*shortRatio;
        	} else {
		        destCanvas.width  = width*longRatio;
		        destCanvas.height = height*longRatio;
        	}
        } else {
        	longRatio = a4LongSide/height;
        	shortRatio =a4ShortSide/width;
        	if(longRatio > shortRatio) {
		        destCanvas.width  = width*shortRatio;
		        destCanvas.height = height*shortRatio;
        	} else {
		        destCanvas.width  = width*longRatio;
		        destCanvas.height = height*longRatio;
        	}
        }

        /*
         * pica resize (要縮放的圖片, 縮放結果(CANVAS))
         * 縮放完成轉出blob
         * 透過FileReader將blob再轉換成Base64
         */
        outputLog("壓縮後圖片尺寸 : "+destCanvas.width+"x"+destCanvas.height);
        resizer.resize(img, destCanvas)
        .then(result => resizer.toBlob(result, 'image/jpeg', pica_quality))
        .then(blob => reader.readAsDataURL(blob));
    } else {
    	/*
         * pica 轉出blob
         * 透過FileReader將blob再轉換成Base64
         */
    	resizer.toBlob(img, 'image/jpeg', pica_quality).then(function(value) {
//    		outputLog(value);
    		reader.readAsDataURL(value);
		 });
    }
}

function displayImage (file, options) {
	loadImage(file, updateResults, options);
}


function format_float(num, pos) {
    var size = Math.pow(10, pos);
    return Math.round(num * size) / size;
}
 
function preview(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('.preview').attr('src', e.target.result);
	        var KB = format_float(e.total / 1024, 2);
	        $('.size').text("檔案大小 : " + KB + " KB");
        }
        reader.readAsDataURL(input.files[0]);
    }
}

function outputLog(message) {
	var NowDate=new Date();
	var h=NowDate.getHours();
	var m=NowDate.getMinutes();
	var s=NowDate.getSeconds();
	
	$('#detail').val("["+(h<10?'0'+h:h)+":"+(m<10?'0'+m:m)+":"+(s<10?'0'+s:s)+"]  "+ message + "\r\n" + $('#detail').val());
}

function supportsToDataURL() {

	var c = document.createElement("canvas");
	var data = c.toDataURL("image/png");
	return (data.indexOf("data:image/png") == 0);	
}
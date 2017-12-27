<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="../css/easyui_themes/color.css"/>
	<link rel="stylesheet" type="text/css" href="../css/easyui_themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="../css/easyui_themes/mobile.css"/>
	<link rel="stylesheet" type="text/css" href="../css/easyui_themes/default/easyui.css"/>
	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../js/load_image/load-image.js"></script>
	<script type="text/javascript" src="../js/load_image/load-image-meta.js"></script>
	<script type="text/javascript" src="../js/load_image/load-image-exif.js"></script>
	<script type="text/javascript" src="../js/load_image/load-image-exif-map.js"></script>
	<script type="text/javascript" src="../js/load_image/load-image-orientation.js"></script>
	<script type="text/javascript" src="../js/pica/pica.js"></script>
	<script type="text/javascript" src="../js/megapix-image.js"></script>
	<script type="text/javascript" src="../js/imageConvert.js"></script>
<title></title>
</head>
<body>
    <h2>Image Convert Demo</h2>
    <p>==========================.</p>
    <div style="margin:20px 0;"></div>
    <div class="easyui-layout" style="width:100%;height:500px;">
        <div data-options="region:'west',split:true" title="上傳設定" style="width:40%;height: 100%">
        	<form action="">
        		<table id="uploadSetting"  style="width:100%" border="2">
        			<tr>
        				<td>上傳圖片</td>
        				<td>
        					<input type='file'  class="upImg">
						    <div>
						        <img class="preview" style="max-width: 150px; max-height: 150px;">
						        <div class="size"></div>
						    </div>
        				</td>
        			</tr>
        			<tr>
        				<td>壓縮品質(JS_pica)</td>
        				<td>
        					<input id="pica_quality" style="width:100px">
        				</td>
        			</tr>
        			<tr>
        				<td>縮放品質(JAVA_Scarl)</td>
        				<td>
        					<select id="scarl_quality"  class="easyui-combobox" style="width:150px">
							    <option value="-1">ALL</option>
							    <option value="0">AUTOMATIC</option>
							    <option value="1">BALANCED</option>
							    <option value="2">QUALITY</option>
							    <option value="3">SPEED</option>
							    <option value="4">ULTRA_QUALITY</option>
        					</select>
        				</td>
        			</tr>
        			<tr>
        				<td>壓縮品質(JAVA_jpeg)</td>
        				<td>
        					<input id="jpeg_quality" style="width:100px">
        				</td>
        			</tr>
        			<tr>
        				<td>高斯模糊</td>
        				<td>
        					<input id="gs_smooth" style="width:100px">
        				</td>
        			</tr>
        			<tr>
        				<td>高斯模糊</td>
        				<td>
        					<input id="blur_radis" style="width:100px">
        				</td>
        			</tr>
        			<tr>
        				<td>高斯模糊</td>
        				<td>
        					<input id="sharp_radis" style="width:100px">
        				</td>
        			</tr>
        			<tr>
        				<td>目標尺寸</td>
        				<td>
        					<input type="radio" id="dpi200" name="targetDPI" value="200" checked> <label for="dpi200" >200DPI</label>
        					<input type="radio" id="dpi300" name="targetDPI" value="300"> <label for="dpi300">300DPI</label>
        				</td>
        			</tr>
        			<tr>
        				<td colspan="2">
        					<input type="button"  id="submit" name="submit"  value="送出">
        				</td>
        			</tr>
        		</table>
        	</form>
        </div>
        <div id="result" class="result"></div>
        <div data-options="region:'center',split:true" title="訊息" style="width:60%;height: 100%">
        	<textarea id="detail" rows="21" cols="90"  style="resize:none;overflow-y: scroll;" readonly="readonly"></textarea>
        </div>
    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/client.min.js"></script>
<title>SysInfo</title>
</head>
<body>

</body>

<script type="text/javascript">

	$(function() {
		var client = new ClientJS();
		
        document.write("getFingerprint"+" : "+ client["getFingerprint"]()+'<br/>');
        document.write("getCustomFingerprint"+" : "+ client["getCustomFingerprint"]()+'<br/>');
        document.write("getUserAgent"+" : "+ client["getUserAgent"]()+'<br/>');
        document.write("getBrowser"+" : "+ client["getBrowser"]()+'<br/>');
        document.write("getBrowserVersion"+" : "+ client["getBrowserVersion"]()+'<br/>');
        document.write("isIE"+" : "+ client["isIE"]()+'<br/>');
        document.write("isChrome"+" : "+ client["isChrome"]()+'<br/>');
        document.write("isFirefox "+" : "+ client["isFirefox"]()+'<br/>');
        document.write("isSafari"+" : "+ client["isSafari"]()+'<br/>');
        document.write("isMobileSafari"+" : "+ client["isMobileSafari"]()+'<br/>');
        document.write("isOpera"+" : "+ client["isOpera"]()+'<br/>');
        document.write("getOS"+" : "+ client["getOS"]()+'<br/>');
        document.write("isWindows"+" : "+ client["isWindows"]()+'<br/>');
        document.write("isMac"+" : "+ client["isMac"]()+'<br/>');
        document.write("isLinux"+" : "+ client["isLinux"]()+'<br/>');
        document.write("isUbuntu"+" : "+ client["isUbuntu"]()+'<br/>');
        document.write("isSolaris"+" : "+ client["isSolaris"]()+'<br/>');
        document.write("getDevice"+" : "+ client["getDevice"]()+'<br/>');
        document.write("getDeviceType"+" : "+ client["getDeviceType"]()+'<br/>');
        document.write("getCPU"+" : "+ client["getCPU"]()+'<br/>');
        document.write("isMobile"+" : "+ client["isMobile"]()+'<br/>');
        document.write("isMobileMajor"+" : "+ client["isMobileMajor"]()+'<br/>');
        document.write("isMobileAndroid"+" : "+ client["isMobileAndroid"]()+'<br/>');
        document.write("isMobileOpera"+" : "+ client["isMobileOpera"]()+'<br/>');
        document.write("isMobileWindows"+" : "+ client["isMobileWindows"]()+'<br/>');
        document.write("isMobileBlackBerry"+" : "+ client["isMobileBlackBerry"]()+'<br/>');
        document.write("isMobileIOS"+" : "+ client["isMobileIOS"]()+'<br/>');
        document.write("isIphone"+" : "+ client["isIphone"]()+'<br/>');
        document.write("isIpad"+" : "+ client["isIpad"]()+'<br/>');
        document.write("isIpod"+" : "+ client["isIpod"]()+'<br/>');
        document.write("getCurrentResolution"+" : "+ client["getCurrentResolution"]()+'<br/>');
        document.write("getAvailableResolution"+" : "+ client["getAvailableResolution"]()+'<br/>');
        document.write("getDeviceXDPI"+" : "+ client["getDeviceXDPI"]()+'<br/>');
        document.write("getDeviceYDPI"+" : "+ client["getDeviceYDPI"]()+'<br/>');
        document.write("isJava"+" : "+ client["isJava"]()+'<br/>');
        document.write("getJavaVersion"+" : "+ client["getJavaVersion"]()+'<br/>');
        document.write("isFlash"+" : "+ client["isFlash"]()+'<br/>');
        document.write("getFlashVersion"+" : "+ client["getFlashVersion"]()+'<br/>');
        document.write("isSilverlight"+" : "+ client["isSilverlight"]()+'<br/>');
        document.write("getSilverlightVersion"+" : "+ client["getSilverlightVersion"]()+'<br/>');
	});
</script>
</html>
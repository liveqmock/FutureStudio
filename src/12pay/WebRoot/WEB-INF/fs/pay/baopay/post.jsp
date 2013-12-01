<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>正在为您跳转</title>
</head>
<body onload="pay.submit()">
<!--     <script>
    require( ["jquery","online/cash/pay/js/submit"],
    function($, Index) {
    new Index().render();
    }
    );
    </script> -->
<form method="post" name="pay" id="pay" action="${requestUrl }">
<TABLE>
<TR>
	<TD><input name='MerchantID' type='hidden' value= "${baoRequest.merchantID }"/>
	<input name='PayID' type='hidden' value= "${baoRequest.payID }"/>		
	<input name='TradeDate' type='hidden' value= "${baoRequest.tradeDate }" />
	<input name='TransID' type='hidden' value= "${baoRequest.transID }" />
	<input name='OrderMoney' type='hidden' value= "${baoRequest.orderMoney }"/>
	<input name='ProductName' type='hidden' value= "${baoRequest.productName }"/>
	<input name='Amount' type='hidden' value= "${baoRequest.amount }"/>
	<input name='ProductLogo' type='hidden' value= "${baoRequest.productLogo }"/>
	<input name='Username' type='hidden' value= "${baoRequest.username }"/>
	<input name='Email' type='hidden' value= "${baoRequest.email }"/>
	<input name='Mobile' type='hidden' value= "${baoRequest.mobile }"/>
	<input name='AdditionalInfo' type='hidden' value= "${baoRequest.additionalInfo }"/>
	<input name='Merchant_url' type='hidden' value= "${baoRequest.merchant_url }"/>
	<input name='Return_url' type='hidden' value= "${baoRequest.return_url }"/>	
	<input name= 'Md5Sign' type='hidden' value="${baoRequest.md5Sign }"/>
	<input name='NoticeType' type='hidden' value= "${baoRequest.noticeType }"/>
	</TD>
</TR>
</TABLE>
	
</form>	

</body>
</html>
<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

	<xsl:output
		method="html"
		encoding="UTF-8"
		omit-xml-declaration="yes"
		indent="no"
		media-type="text/html"
	/>

	<!--標頭之 meta-->
	<xsl:template name="meta">
		<META charset="UTF-8"/>
		<META http-equiv="X-UA-Compatible" content="IE=edge"/>
		<META name="viewport" content="width=device-width, initial-scale=1.0"/>
		<META name="keywords" content=""/>
		<META name="description" content=""/>
	</xsl:template>

	<!--標頭之 link-->
	<xsl:template name="stylesheet">
		<LINK href="https://fonts.googleapis.com/css?family=Fenix" rel="stylesheet" media="all" type="text/css"/>
		<LINK href="https://cdnjs.cloudflare.com/ajax/libs/open-iconic/1.1.1/font/css/open-iconic.min.css" rel="stylesheet" media="all" type="text/css"/>
		<LINK href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" rel="stylesheet" media="all" type="text/css"/>
		<LINK href="/STYLE/grid.css" rel="stylesheet" media="all" type="text/css"/>
		<!--<LINK href="/STYLE/page.css" rel="stylesheet" media="all" type="text/css"/>-->
		<LINK href="/favicon.ico" rel="shortcut icon"/>
	</xsl:template>

	<!--標頭之 script-->
	<xsl:template name="script">
		<SCRIPT src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"/>
		<SCRIPT src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"/>
		<SCRIPT src="/SCRIPT/vendors.js"/>
		<SCRIPT src="/SCRIPT/layoutcomm.js"/>
		<SCRIPT><![CDATA[var gb_approot = '/';]]></SCRIPT>
		<SCRIPT src="/SCRIPT/googleTagManager.js"/>
		<SCRIPT src="/SCRIPT/locales.js"/>
	</xsl:template>

	<!--Google Tag Manager-->
	<xsl:template name="googleTagManager">
		<NOSCRIPT>
			<IFRAME src="https://www.googletagmanager.com/ns.html?id=GTM-KT9VFT9" width="0" style="display:none;visibility:hidden" height="0"/>
		</NOSCRIPT>
	</xsl:template>

	<!--上方工具列-->
	<xsl:template name="header">
		<HEADER id="header">
			<H1 class="logo" title="回首頁" onclick="location.href = '/'" tabindex="-1">鐳揚創智</H1>
			<BUTTON class="mobile-toggle oi" data-glyph=""/>
			<DIV class="navbar">
				<NAV class="top-nav">
					<DIV class="user-nav mobile-nav">
						<xsl:choose>
							<xsl:when test="@customer">
								<xsl:choose>
									<xsl:when test="@administrator">
										<A class="welcome mobile-nav-hide" href="/dashboard/">Welcome, <xsl:value-of select="@fullname"/></A>
									</xsl:when>
									<xsl:otherwise>
										<SPAN class="welcome mobile-nav-hide">Welcome, <xsl:value-of select="@fullname"/></SPAN>
									</xsl:otherwise>
								</xsl:choose>
								<A class="root" href="/user.asp">會員中心</A>
								<A class="root" href="/history.asp">查看訂單</A>
								<A class="root" href="/logout.asp">登出</A>
							</xsl:when>
							<xsl:otherwise>
								<A class="root mobile-nav" href="/login.asp">會員登入</A>
							</xsl:otherwise>
						</xsl:choose>
					</DIV>

					<DIV class="shopping-cart">
						<BUTTON class="root cart-toggle" id="btnCart">購物車(<SPAN class="text-warning"><!--如果有加入購物車的話，就顯示已加入的產品數目--></SPAN>)</BUTTON>

						<!-- mini版購物籃 -->
						<DIV class="cart-content hidden">
							<UL class="cart-list" id="cart-list_info"/>
							<DIV class="cart-bar text-right">
								<SPAN class="float-l">小計</SPAN>
								<STRONG class="col-rest" id="sum_total"/>
								<A class="btn btn-cart" href="/checkout.asp">我要結帳</A>
							</DIV>
						</DIV>
					</DIV>

					<A class="root mobile-nav-hide" href="/contactUs.asp">聯絡我們</A>
					<A class="root mobile-nav-hide" href="/">回首頁</A>
					<xsl:apply-templates select="locales"/>
					<SPAN class="root mobile-nav-hide"></SPAN>
				</NAV>

				<A class="btn btn-buy" href="/products.asp">立即購買</A>

				<NAV id="menu">
					<A href="/announcement/">
						<xsl:if test="@current='announcement'">
							<xsl:attribute name="class">current</xsl:attribute>
						</xsl:if>
						<SPAN>最新消息</SPAN>
					</A>
					<A href="/products.asp">
						<xsl:if test="@current='products'">
							<xsl:attribute name="class">current</xsl:attribute>
						</xsl:if>
						<SPAN>產品資訊</SPAN>
					</A>
					<xsl:if test="not(@customer)and not(@fullname)">
						<A href="/supports.asp">
							<xsl:if test="@current='supports.asp'">
								<xsl:attribute name="class">current</xsl:attribute>
							</xsl:if>
							<SPAN>會員服務</SPAN>
						</A>
					</xsl:if>
					<A href="/download/">
						<xsl:if test="@current='download'">
							<xsl:attribute name="class">current</xsl:attribute>
						</xsl:if>
						<SPAN>支援與下載</SPAN>
					</A>
				</NAV>
			</DIV>
		</HEADER>
	</xsl:template>

	<!--上方工具列-->
	<xsl:template name="footer">
		<FOOTER id="footer">
			<NAV class="foot-nav">
				<A href="/about.asp">公司簡介</A>
				<xsl:if test="not(@customer)and not(@fullname)">
					<A href="/supports.asp">會員服務</A>
				</xsl:if>
				<A href="/proposal.asp">專案合作</A> 
				<A href="/contactUs.asp">聯絡我們</A>
			</NAV>
			<DIV class="copyright">
				<SPAN class="block">Copyright &#169; 2017 RAYALALA. All rights reserved.</SPAN>
				<SPAN>鐳揚創智科技股份有限公司</SPAN>
			</DIV>
		</FOOTER>
	</xsl:template>

	<!--下拉式選單群組-->
	<xsl:template match="optgroup">
		<OPTGROUP label="{@label}">
			<xsl:apply-templates/>
		</OPTGROUP>
	</xsl:template>

	<!--下拉式選單選項-->
	<xsl:template match="option">
		<OPTION value="{@value}">
			<xsl:if test="@selected">
				<xsl:attribute name="selected"/>
			</xsl:if>
			<xsl:value-of select="." disable-output-escaping="yes"/>
		</OPTION>
	</xsl:template>

	<!--語言環境-->
	<xsl:template match="locales">
		<SELECT class="root mobile-nav-hide" id="locales">
			<xsl:apply-templates select="*"/>
		</SELECT>
	</xsl:template>

</xsl:stylesheet>
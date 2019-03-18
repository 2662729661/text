<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

	<xsl:output
		method="html"
		encoding="UTF-8"
		omit-xml-declaration="yes"
		indent="yes"
		media-type="text/html"
	/>

	<xsl:include href="/include.xsl"/>

	<xsl:template match="/">
		<xsl:text disable-output-escaping="yes">&#60;!DOCTYPE HTML&#62;</xsl:text>
		<HTML dir="ltr" lang="{document/@language}-{document/@country}">
			<xsl:apply-templates/>
		</HTML>
	</xsl:template>

	<xsl:template match="document">
		<HEAD>
			<xsl:call-template name="meta"/>
			<xsl:call-template name="stylesheet"/>
			<LINK href="/STYLE/index.css" rel="stylesheet" media="all" type="text/css"/>
			<LINK href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.1.0/css/swiper.min.css" rel="stylesheet" media="all" type="text/css"/>
			<xsl:call-template name="script"/>
			<SCRIPT src="/SCRIPT/swiper.min.js"/>
			<SCRIPT src="/banner.js"/>
			<TITLE>WELCOME&#58;鐳揚創智</TITLE>
		</HEAD>
		<xsl:comment>
			<xsl:value-of select="system-property('xsl:version')"/>
		</xsl:comment>
		<BODY class="Index">
			<xsl:call-template name="googleTagManager"/>

			<DIV id="wrapper">
				<xsl:call-template name="header"/>

				<SECTION class="swiper-container" id="banner">
					<DIV class="swiper-wrapper" id="setBanner" style="height:452px"/>
					<ASIDE class="swiper-pager"/>
					<SPAN class="icon-prev banner-prev">&#10094;</SPAN>
					<SPAN class="icon-next banner-next">&#10095;</SPAN>
				</SECTION>

				<SECTION class="intro text-white m-y-4">
					<FIGURE class="float-l">
						<IMG alt="產品圖" src="/product.png"/>
						<A href="/products.asp"/>
						<FIGCAPTION>見證ARH PASS如何幫助你<BR/>整合密碼，讓生活更便利。</FIGCAPTION>
					</FIGURE>
					<ASIDE class="col-rest bg-success">
						<A class="youtube-open block" href="https://www.youtube.com/channel/UCeYU3FV6drie1rYR1714ndw" target="_blank">&#160;</A>
						<H4>Youtube 影片介紹</H4>
					</ASIDE>
				</SECTION>

				<DIV class="modal" id="youtube">
					<BUTTON class="modal-close" onclick="modalClose('youtube')">&#215;</BUTTON>
					<DIV class="modal-content animate-zoom">
						<IFRAME src="https://www.youtube.com/embed/YQHsXMglC9A" allowfullscreen="" frameborder="0"/>
					</DIV>
				</DIV>

				<SECTION class="carousel swiper-container">
					<NAV class="swiper-wrapper">
						<xsl:apply-templates select="products"/>
					</NAV>
					<SPAN class="icon-prev carousel-prev">&#10092;</SPAN>
					<SPAN class="icon-next carousel-next">&#10093;</SPAN>
				</SECTION>

				<SECTION class="download text-white row-a-0" id="DL">
					<MAIN>
						<NAV>
							<H2 class="font-xxxl">APP 程式下載</H2>
							<A class="apple" href="https://itunes.apple.com/us/app/alila-pass/id1190231743?mt=8" target="_blank">APP Store 下載</A>
							<A class="google" href="https://play.google.com/store/apps/details?id=tw.com.tytt.magicinputa" target="_blank">Google Play 下載</A>
							<A class="windows" href="" target="_blank">Windows 市集下載</A>
						</NAV>
					</MAIN>
					<A class="col-rest workflow" href="/workflow.asp">
						<STRONG>簡單方便的 ARH PASS APP使用流程</STRONG>
					</A>
				</SECTION>
			</DIV>

			<A class="goTop oi" title="TOP" href="#header" data-glyph="chevron-top"/>

			<xsl:call-template name="footer"/>

			<SCRIPT src="/index.js"/>
		</BODY>
	</xsl:template>

	<xsl:template match="products">
		<xsl:for-each select="product">
			<A class="swiper-slide" href="javascript:void(0)">
				<IMG alt="{.}" src="https://s3-ap-southeast-1.amazonaws.com/bf5334024406faf6374722781fda2ef4/a49d64f91e471bc4/{@imageFilename}.png"/>
				<SPAN>
					<xsl:value-of select="."/>
				</SPAN>
			</A>
		</xsl:for-each>
	</xsl:template>

</xsl:stylesheet>
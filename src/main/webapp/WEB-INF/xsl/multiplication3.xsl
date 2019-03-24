<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
        <xsl:output version="1.0" indent="yes" encoding="UTF-8" method="html"/>
        <xsl:template match="/">
                <html>
                        <body>
                                <h2>九九乘法表3</h2>
                                <table border="1">
                                        <tbody>
                                                <!--<?xml-stylesheet type="text/xsl" href="multiplication.xsl"?>
                                                -->
                                                <xsl:for-each select="document/mts">
                                                        <tr>
                                                                <td>
                                                                        <xsl:value-of select="mt0"/>&#215;
                                                                        <xsl:value-of select="mt10"/>
                                                                        <xsl:value-of select="mt20"/>
                                                                </td>
                                                                <td>
                                                                        <xsl:if test="mt1 != ''">
                                                                                <xsl:value-of select="mt1"/>&#215;
                                                                                <xsl:value-of select="mt11"/>
                                                                                <xsl:value-of select="mt21"/>
                                                                        </xsl:if>
                                                                </td>
                                                                <td>
                                                                        <xsl:if test="mt2 != ''">
                                                                                <xsl:value-of select="mt2"/>&#215;
                                                                                <xsl:value-of select="mt12"/>
                                                                                <xsl:value-of select="mt22"/>
                                                                        </xsl:if>
                                                                </td>
                                                                <td>
                                                                        <xsl:if test="mt3 != ''">
                                                                                <xsl:value-of select="mt3"/>&#215;
                                                                                <xsl:value-of select="mt13"/>
                                                                                <xsl:value-of select="mt23"/>
                                                                        </xsl:if>
                                                                </td>
                                                                <td>
                                                                        <xsl:if test="mt4 != ''">
                                                                                <xsl:value-of select="mt4"/>&#215;
                                                                                <xsl:value-of select="mt14"/>
                                                                                <xsl:value-of select="mt24"/>
                                                                        </xsl:if>
                                                                </td>
                                                                <td>
                                                                        <xsl:if test="mt5 != ''">
                                                                                <xsl:value-of select="mt5"/>&#215;
                                                                                <xsl:value-of select="mt15"/>
                                                                                <xsl:value-of select="mt25"/>
                                                                        </xsl:if>
                                                                </td>
                                                                <td>
                                                                        <xsl:if test="mt6 != ''">
                                                                                <xsl:value-of select="mt6"/>&#215;
                                                                                <xsl:value-of select="mt16"/>
                                                                                <xsl:value-of select="mt26"/>
                                                                        </xsl:if>
                                                                </td>
                                                                <td>
                                                                        <xsl:if test="mt7 != ''">
                                                                                <xsl:value-of select="mt7"/>&#215;
                                                                                <xsl:value-of select="mt17"/>
                                                                                <xsl:value-of select="mt27"/>
                                                                        </xsl:if>
                                                                </td>
                                                                <td>
                                                                        <xsl:if test="mt8 != ''">
                                                                                <xsl:value-of select="mt8"/>&#215;
                                                                                <xsl:value-of select="mt18"/>
                                                                                <xsl:value-of select="mt28"/>
                                                                        </xsl:if>
                                                                </td>
                                                        </tr>
                                                </xsl:for-each>

                                        </tbody>
                                </table>
                        </body>
                </html>
        </xsl:template>
</xsl:stylesheet>
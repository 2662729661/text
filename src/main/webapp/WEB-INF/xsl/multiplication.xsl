<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
        <xsl:output version="1.0" indent="yes" encoding="UTF-8" method="html"/>
        <xsl:template match="/">
                <html>
                        <body>
                                <h2>九九乘法表</h2>
                                <table border="1">
                                        <tbody>

                                                <xsl:for-each select="document/mts">
                                                        <tr>
                                                                <xsl:choose>
                                                                        <xsl:when test="mt30 = 2">
                                                                                <td>
                                                                                        <xsl:value-of select="mt10"/>&#215;
                                                                                        <xsl:value-of select="mt20"/>
                                                                                        <font color='red'>
                                                                                                <xsl:value-of select="mt30"/>
                                                                                        </font>
                                                                                </td>
                                                                        </xsl:when>
                                                                        <xsl:otherwise>
                                                                                <td>
                                                                                        <xsl:value-of select="mt10"/>&#215;
                                                                                        <xsl:value-of select="mt20"/>
                                                                                        <xsl:value-of select="mt30"/>
                                                                                </td>
                                                                        </xsl:otherwise>
                                                                </xsl:choose>

                                                                <xsl:choose>
                                                                        <xsl:when test="mt31 = 3">
                                                                                <td>
                                                                                        <xsl:value-of select="mt11"/>&#215;
                                                                                        <xsl:value-of select="mt21"/>
                                                                                        <font color='red'>
                                                                                                <xsl:value-of select="mt31"/>
                                                                                        </font>
                                                                                </td>
                                                                        </xsl:when>
                                                                        <xsl:when test="mt31 = 7">
                                                                                <td>
                                                                                        <xsl:value-of select="mt11"/>&#215;
                                                                                        <xsl:value-of select="mt21"/>
                                                                                        <font color='red'>
                                                                                                <xsl:value-of select="mt31"/>
                                                                                        </font>
                                                                                </td>
                                                                        </xsl:when>
                                                                        <xsl:otherwise>
                                                                                <td>
                                                                                        <xsl:value-of select="mt11"/>&#215;
                                                                                        <xsl:value-of select="mt21"/>
                                                                                        <xsl:value-of select="mt31"/>
                                                                                </td>
                                                                        </xsl:otherwise>
                                                                </xsl:choose>

                                                                <td>
                                                                        <xsl:value-of select="mt12"/>&#215;
                                                                        <xsl:value-of select="mt22"/>
                                                                        <xsl:value-of select="mt32"/>
                                                                </td>


                                                                <xsl:choose>
                                                                        <xsl:when test="mt33 = 5">
                                                                                <td>
                                                                                        <xsl:value-of select="mt13"/>&#215;
                                                                                        <xsl:value-of select="mt23"/>
                                                                                        <font color='red'>
                                                                                                <xsl:value-of select="mt33"/>
                                                                                        </font>
                                                                                </td>
                                                                        </xsl:when>
                                                                        <xsl:otherwise>
                                                                                <td>
                                                                                        <xsl:value-of select="mt13"/>&#215;
                                                                                        <xsl:value-of select="mt23"/>
                                                                                        <xsl:value-of select="mt33"/>
                                                                                </td>
                                                                        </xsl:otherwise>
                                                                </xsl:choose>
                                                        </tr>
                                                </xsl:for-each>

                                        </tbody>
                                </table>
                        </body>
                </html>
        </xsl:template>
</xsl:stylesheet>
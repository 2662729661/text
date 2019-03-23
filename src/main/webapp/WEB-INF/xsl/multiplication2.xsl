<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
        <xsl:output version="1.0" indent="yes" encoding="UTF-8" method="html"/>
        <xsl:template match="/">
                <html>
                        <body>
                                <h2>九九乘法表2</h2>
                                <table border="1">
                                        <tbody>
                                                <!--<?xml-stylesheet type="text/xsl" href="multiplication.xsl"?>
                                                -->
                                                <xsl:for-each select="document/mts">
                                                        <xsl:if test="mt0 = 0">
                                                                <tr bgcolor="#9acb32">
                                                                        <td>
                                                                                <xsl:value-of select="mt0"/>
                                                                        </td>
                                                                        <td>
                                                                                <xsl:value-of select="mt1"/>
                                                                        </td>
                                                                        <td>
                                                                                <xsl:value-of select="mt2"/>
                                                                        </td>
                                                                        <td>
                                                                                <xsl:value-of select="mt3"/>
                                                                        </td>
                                                                        <td>
                                                                                <xsl:value-of select="mt4"/>
                                                                        </td>
                                                                        <td>
                                                                                <xsl:value-of select="mt5"/>
                                                                        </td>
                                                                        <td>
                                                                                <xsl:value-of select="mt6"/>
                                                                        </td>
                                                                        <td>
                                                                                <xsl:value-of select="mt7"/>
                                                                        </td>
                                                                        <td>
                                                                                <xsl:value-of select="mt8"/>
                                                                        </td>
                                                                        <td>
                                                                                <xsl:value-of select="mt9"/>
                                                                        </td>
                                                                        <td>
                                                                                <xsl:value-of select="mt10"/>
                                                                        </td>
                                                                </tr>
                                                        </xsl:if>
                                                        <xsl:if test="mt0 != 0">
                                                                <tr >
                                                                        <td bgcolor="#9acb32">
                                                                                <xsl:value-of select="mt0"/>
                                                                        </td>
                                                                        <td>
                                                                                <xsl:value-of select="mt1"/>
                                                                        </td>
                                                                        <td>
                                                                                <xsl:value-of select="mt2"/>
                                                                        </td>
                                                                        <td>
                                                                                <xsl:value-of select="mt3"/>
                                                                        </td>
                                                                        <td>
                                                                                <xsl:value-of select="mt4"/>
                                                                        </td>
                                                                        <td>
                                                                                <xsl:value-of select="mt5"/>
                                                                        </td>
                                                                        <td>
                                                                                <xsl:value-of select="mt6"/>
                                                                        </td>
                                                                        <td>
                                                                                <xsl:value-of select="mt7"/>
                                                                        </td>
                                                                        <td>
                                                                                <xsl:value-of select="mt8"/>
                                                                        </td>
                                                                        <td>
                                                                                <xsl:value-of select="mt9"/>
                                                                        </td>
                                                                        <td>
                                                                                <xsl:value-of select="mt10"/>
                                                                        </td>
                                                                </tr>
                                                        </xsl:if>
                                                        
                                                </xsl:for-each>

                                        </tbody>
                                </table>
                        </body>
                </html>
        </xsl:template>
</xsl:stylesheet>
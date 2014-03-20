<?xml version='1.0'?>
<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fo="http://www.w3.org/1999/XSL/Format"
    version="1.0">
    <xsl:import href="urn:docbkx:stylesheet" />
    <xsl:template match="/book/chapter/para[@role = 'intro']">
        <fo:block border="0.5pt solid blue"
            padding="3pt"
            xsl:use-attribute-sets="normal.para.spacing">
            <xsl:apply-templates/>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>